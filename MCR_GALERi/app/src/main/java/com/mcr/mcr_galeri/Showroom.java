package com.mcr.mcr_galeri;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Showroom extends AppCompatActivity {
    ListView listview;
    List<Araba> arabalar=new ArrayList<>();
    List<Araba> bulunan_arabalar=new ArrayList<>();
    String _marka = "", _model = "", _fiyat_min = "",_fiyat_max="";
    VeriTabani kayitlar;

    private String[] sutunlar = {"tarih","marka", "model","yil","fiyat","aciklama","resim_yolu","ad_soyad","numara"};
    private void TumKayitlar() {
        SQLiteDatabase db = kayitlar.getReadableDatabase();
        Cursor cursorKayit = db.query("Kayitlar", sutunlar, null, null, null, null, null);
        while (cursorKayit.moveToNext()) {
            Araba bulunan = new Araba();

            bulunan.tarih = cursorKayit.getString(cursorKayit.getColumnIndex("tarih"));
            bulunan.marka = cursorKayit.getString(cursorKayit.getColumnIndex("marka"));
            bulunan.model = cursorKayit.getString(cursorKayit.getColumnIndex("model"));
            bulunan.yil = cursorKayit.getString(cursorKayit.getColumnIndex("yil"));
            bulunan.fiyat = cursorKayit.getInt(cursorKayit.getColumnIndex("fiyat"));
            bulunan.aciklama = cursorKayit.getString(cursorKayit.getColumnIndex("aciklama"));
            bulunan.resim = cursorKayit.getString(cursorKayit.getColumnIndex("resim_yolu"));
            bulunan.ad_soyad = cursorKayit.getString(cursorKayit.getColumnIndex("ad_soyad"));
            bulunan.numara = cursorKayit.getString(cursorKayit.getColumnIndex("numara"));

            arabalar.add(bulunan);
        }
        cursorKayit.close();
    }

    private void ArabaBul()
    {
        try {
            TumKayitlar();

            /*EditText txt = (EditText) findViewById(R.id.textView1);
            txt.setText(" ");*/
            bulunan_arabalar.clear();

            if(_marka.equals("boş")==false && _model.equals("boş")==true && _fiyat_min.equals("boş")==true)
            {//marka
                for (int i = 0; i < arabalar.size(); i++)
                {
                    if(arabalar.get(i).marka.equals(_marka))
                    {
                        bulunan_arabalar.add(arabalar.get(i));
                    }
                }
            }
            if(_marka.equals("boş")==true && _model.equals("boş")==false && _fiyat_min.equals("boş")==true)
            {//model
                for (int i = 0; i < arabalar.size(); i++)
                {
                    if(arabalar.get(i).model.equals(_model))
                    {
                        bulunan_arabalar.add(arabalar.get(i));
                    }
                }
            }
            if(_marka.equals("boş")==true && _model.equals("boş")==true && _fiyat_min.equals("boş")==false)
            {//fiyat
                for (int i = 0; i < arabalar.size(); i++)
                {
                    if(arabalar.get(i).fiyat>=Integer.parseInt(_fiyat_min) && arabalar.get(i).fiyat<=Integer.parseInt(_fiyat_max))
                    {
                        bulunan_arabalar.add(arabalar.get(i));
                    }
                }
            }
            if(_marka.equals("boş")==false && _model.equals("boş")==false && _fiyat_min.equals("boş")==true)
            {//marka,model
                for (int i = 0; i < arabalar.size(); i++)
                {
                    if(arabalar.get(i).marka.equals(_marka) && arabalar.get(i).model.equals(_model))
                    {
                        bulunan_arabalar.add(arabalar.get(i));
                    }
                }
            }
            if(_marka.equals("boş")==false && _model.equals("boş")==true && _fiyat_min.equals("boş")==false)
            {//marka,fiyat
                for (int i = 0; i < arabalar.size(); i++)
                {
                    if(arabalar.get(i).marka.equals(_marka) && arabalar.get(i).fiyat>=Integer.parseInt(_fiyat_min) && arabalar.get(i).fiyat<=Integer.parseInt(_fiyat_max))
                    {
                        bulunan_arabalar.add(arabalar.get(i));
                    }
                }
            }
            if(_marka.equals("boş")==true && _model.equals("boş")==false && _fiyat_min.equals("boş")==false)
            {//model,fiyat
                for (int i = 0; i < arabalar.size(); i++)
                {
                    if(arabalar.get(i).model.equals(_model) && arabalar.get(i).fiyat>=Integer.parseInt(_fiyat_min) && arabalar.get(i).fiyat<=Integer.parseInt(_fiyat_max))
                    {
                        bulunan_arabalar.add(arabalar.get(i));
                    }
                }
            }
        } catch (Exception h) {
            Toast.makeText(Showroom.this, h.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showroom);

        setTitle("BULUNAN ARABALAR");

        kayitlar=new VeriTabani(this);

        if(getIntent().getStringExtra("tum_kayitlar").toString().equals("false"))
        {
            if (getIntent().getStringExtra("marka").toString() != "boş") {
                _marka = getIntent().getStringExtra("marka").toString();
            }

            if (getIntent().getStringExtra("model").toString() != "boş") {
                _model = getIntent().getStringExtra("model").toString();
            }

            if (getIntent().getStringExtra("fiyat_min").toString() != "boş") {
                _fiyat_min = getIntent().getStringExtra("fiyat_min").toString();
                _fiyat_max = getIntent().getStringExtra("fiyat_max").toString();
            }


            try {
                try {
                    ArabaBul();
                    //img.setImageResource(R.drawable.arka_plan);
                /*Uri uriFromPath = Uri.fromFile(new File(bulunan_arabalar.get(0).resim));
                img.setImageURI(uriFromPath);*/

                    //img.setImageBitmap(BitmapFactory.decodeFile(bulunan_arabalar.get(0).resim));
                    listview = (ListView) findViewById(R.id.listView_kayitlar);

                    listview.setAdapter(new CustomAdapter(Showroom.this, bulunan_arabalar));
                }catch(Exception h)
                {
                    Toast.makeText(Showroom.this, h.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }catch (Exception hata)
            {
                Toast.makeText(Showroom.this, hata.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            try
            {
                TumKayitlar();
                listview = (ListView) findViewById(R.id.listView_kayitlar);

                listview.setAdapter(new CustomAdapter(Showroom.this, arabalar));
            }
            catch (Exception h)
            {
                Toast.makeText(Showroom.this, h.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent i = new Intent(getApplicationContext(), Araba_ara.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void show_image(String yol,String numara,String aciklama,String sahibi)
    {
        Intent i=new Intent(getApplicationContext(),Show_image.class);
        i.putExtra("yol",yol);
        i.putExtra("numara",numara);
        i.putExtra("aciklama",aciklama);
        i.putExtra("sahibi",sahibi);
        startActivity(i);
    }

    class CustomAdapter extends BaseAdapter {
        List<Araba> arabalar_result;
        Context context;
        private LayoutInflater inflater=null;
        public CustomAdapter(Showroom mainActivity,List<Araba> arabalar){
            this.arabalar_result=arabalar;
            context=mainActivity;
            inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return arabalar_result.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class Holder
        {
            TextView txt_bilgiler;
            ImageView resim;
        }
        Holder holder=new Holder();
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View rowView = inflater.inflate(R.layout.costum_image_list, null);
            try
            {
                holder.txt_bilgiler=(TextView) rowView.findViewById(R.id.txt_bilgiler);
                holder.txt_bilgiler.setText(arabalar_result.get(position).marka + " " + arabalar_result.get(position).model + "\n" + arabalar_result.get(position).fiyat + "TL" + "  YIL:" + arabalar_result.get(position).yil);
                holder.resim=(ImageView) rowView.findViewById(R.id.resim);
                //holder.resim.setImageBitmap(BitmapFactory.decodeFile(arabalar_result.get(position).resim));
                holder.resim.setImageResource(R.drawable.car_icon);
                //Toast.makeText(context, "hata yok", Toast.LENGTH_SHORT).show();
            }
            catch (Exception hata)
            {
                //Toast.makeText(context, hata.getMessage(), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertBuilder=new AlertDialog.Builder(context);
                alertBuilder.setMessage(hata.getMessage());
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.show();
            }
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                show_image(arabalar_result.get(position).resim,arabalar_result.get(position).numara,arabalar_result.get(position).aciklama,arabalar_result.get(position).ad_soyad);
                    //Toast.makeText(context, arabalar_result.get(position).aciklama, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(context, arabalar_result.get(position).marka + " " + arabalar_result.get(position).model+"\n"+arabalar_result.get(position).fiyat+"TL"+"  YIL:"+arabalar_result.get(position).yil+"\n"+arabalar_result.get(position).resim, Toast.LENGTH_LONG).show();
                    //Toast.makeText(context, arabalar_result.get(position).resim, Toast.LENGTH_SHORT).show();
                }
            });
            return rowView;
        }
    }
}





package com.mcr.mcr_galeri;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kayit_bilgileri_girisi extends AppCompatActivity {
    VeriTabani kayitlar;

    public void combo_yil_doldur() {
        try {
            int index=0;
            Time today = new Time(Time.getCurrentTimezone());
            today.setToNow();
            final String[] yillar=new String[today.year-1959];
            for (int i = today.year; i >= 1960; i--) {
                yillar[index]= String.valueOf(i);
                index++;
            }
            Spinner combo_yil = (Spinner) findViewById(R.id.spinner_comboYil);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                    R.layout.spinner_item, yillar);
            combo_yil.setAdapter(adapter);
        }catch(Exception h)
        {
            Toast.makeText(Kayit_bilgileri_girisi.this, h.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void combo_marka_doldur()
    {
        final String[] list_clear = new String[] {  };

        Spinner combo_marka=(Spinner)findViewById(R.id.spinner_comboMarka);
        String[] markalar = new String[] {"Alfa Romeo", "Aston Martin", "Audi", "Bentley", "BMW", "Chery", "Chevrolet", "Chrysler", "Citroen", "Dacia", "Daewoo", "Daihatsu", "Dodge", "Eagle", "Fiat", "Ford", "Honda", "Hyundai", "Infiniti", "Isuzu", "Kia", "Lada", "Mazda", "Mercedes", "Mitsubishi", "Nissan", "Opel", "Peugeot", "Pontiac", "Proton", "Renault", "Rover", "Seat", "Skoda", "Subaru", "Suzuki", "Tata", "Tofaş", "Toyota", "Volkswagen", "Volvo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinner_item, markalar);
        combo_marka.setAdapter(adapter);

        combo_marka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Spinner combo_model = (Spinner) findViewById(R.id.spinner_comboModel);
                    Modeller m=new Modeller();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner_item, m.list_modeller.get(position));
                    combo_model.setAdapter(adapter);
                    /*if (id == 1) {
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                R.layout.spinner_item, list_clear);
                        combo_model.setAdapter(adapter);

                    } else {
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                R.layout.spinner_item, list_clear);
                        combo_model.setAdapter(adapter);
                    }*/
                }
                catch (Exception h)
                {
                    Toast.makeText(Kayit_bilgileri_girisi.this, h.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public String resim_tasi()
    {
        String return_path="";
        if (getIntent().getStringExtra("tasi").equals("false"))
        {
            return_path=getIntent().getStringExtra("yol").toString();
        }
        else
        {
            String resim_yolu = getIntent().getStringExtra("yol").toString();
            String m_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();

            InputStream inStream = null;
            OutputStream outStream = null;
            File dosya = new File(m_path + "/MCR_GALERI");
            if (!dosya.exists()) {
                dosya.mkdir();
                Toast.makeText(Kayit_bilgileri_girisi.this, "klasör oluşturuldu\n" + m_path + "/MCR_GALERI", Toast.LENGTH_LONG).show();
            }

            String[] str = resim_yolu.split("/");
            File afile = new File(resim_yolu);
            File bfile = new File(m_path + "/MCR_GALERI/" + str[str.length - 1]);

            try {
                inStream = new FileInputStream(afile);
                outStream = new FileOutputStream(bfile);
                byte[] buffer = new byte[1024];
                int length;
                //copy the file content in bytes
                while ((length = inStream.read(buffer)) > 0) {
                    outStream.write(buffer, 0, length);
                }
                inStream.close();
                outStream.close();

                //delete the original file

                boolean sonuc = afile.delete();
                if (sonuc == true) {
                    Toast.makeText(Kayit_bilgileri_girisi.this, "Dosya Taşıma Başarılı", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Kayit_bilgileri_girisi.this, "Dosya Taşıma Başarısız", Toast.LENGTH_SHORT).show();
                }

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(Kayit_bilgileri_girisi.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            return_path = m_path + "/MCR_GALERI/" + str[str.length - 1];
        }
        return return_path;
    }

    private void kayıtEkleme(String marka,String model,String yil,String fiyat,String aciklama,String resim,String ad_soyad,String numara) {
        SQLiteDatabase db = kayitlar.getWritableDatabase();
        ContentValues veriler = new ContentValues();
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();

        veriler.put("tarih", today.monthDay + "." + today.month + "." + today.year);
        veriler.put("marka", marka);
        veriler.put("model", model);
        veriler.put("yil",yil);
        veriler.put("fiyat", fiyat);
        veriler.put("aciklama", aciklama);
        veriler.put("resim_yolu", resim);
        veriler.put("ad_soyad", ad_soyad);
        veriler.put("numara", numara);
        db.insertOrThrow("Kayitlar", null, veriler);
    }

    private String[] sutunlar = {"tarih","marka", "model","yil","fiyat","aciklama","resim_yolu","ad_soyad","numara"};
    private String Goster() {
        String str = "";
        SQLiteDatabase db = kayitlar.getReadableDatabase();
        Cursor cursorKayit = db.query("Kayitlar", sutunlar, null, null, null, null, null);
        while (cursorKayit.moveToNext()) {
            String _tarih=cursorKayit.getString(cursorKayit.getColumnIndex("tarih"));
            String _marka = cursorKayit.getString(cursorKayit.getColumnIndex("marka"));
            String _model = cursorKayit.getString(cursorKayit.getColumnIndex("model"));
            String _yil = cursorKayit.getString(cursorKayit.getColumnIndex("yil"));
            String _fiyat = cursorKayit.getString(cursorKayit.getColumnIndex("fiyat"));
            String _aciklama = cursorKayit.getString(cursorKayit.getColumnIndex("aciklama"));
            String _resim_yolu = cursorKayit.getString(cursorKayit.getColumnIndex("resim_yolu"));
            String _ad_soyad = cursorKayit.getString(cursorKayit.getColumnIndex("ad_soyad"));
            String _numara = cursorKayit.getString(cursorKayit.getColumnIndex("numara"));

            str = str + "\n" +_tarih+"_" +_marka + "_" + _model + "_" + _yil + "_" + _fiyat + "_" + _aciklama + "_" + _resim_yolu + "_" + _ad_soyad + "_" + _numara;
        }
        cursorKayit.close();
        return str;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001 && resultCode == RESULT_OK)
        {
            try
            {
                if (data != null) {
                    Uri uri = data.getData();

                    if (uri != null) {
                        Cursor c = null;
                        try {
                            c = getContentResolver().query(uri, new String[]{
                                            ContactsContract.CommonDataKinds.Phone.NUMBER,
                                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME },
                                    null, null, null);

                            if (c != null && c.moveToFirst()) {
                                String number = c.getString(0);
                                String name = c.getString(1);
                                String num = "";
                                for (int i = 0; i < number.split("-").length; i++) {
                                    num += number.split("-")[i];
                                }
                                //Toast.makeText(this, name + " " + num, Toast.LENGTH_LONG).show();
                                EditText txt_ad=(EditText)findViewById(R.id.editText_adSoyad);
                                EditText txt_numara=(EditText)findViewById(R.id.editText_numara);
                                txt_ad.setText(name);
                                txt_numara.setText(num);
                            }
                        } finally {
                            if (c != null) {
                                c.close();
                            }
                        }
                    }
                }
            }
            catch (Exception h)
            {
                Toast.makeText(Kayit_bilgileri_girisi.this, "Result\n"+h.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_bilgileri_girisi);

        setTitle("YENİ KAYIT");

        kayitlar=new VeriTabani(this);

        Button btn_kaydet=(Button)findViewById(R.id.btn_kaydet);
        Button btn_temizle=(Button)findViewById(R.id.btn_temizle);
        Button btn_rehber=(Button)findViewById(R.id.btn_rehber);
        ImageView resim=(ImageView)findViewById(R.id.imageView_resim);

        final Spinner combo_marka=(Spinner)findViewById(R.id.spinner_comboMarka);
        final Spinner combo_model=(Spinner)findViewById(R.id.spinner_comboModel);
        final Spinner combo_yil=(Spinner)findViewById(R.id.spinner_comboYil);
        final EditText txt_fiyat=(EditText)findViewById(R.id.editText_Fiyat);
        final EditText txt_aciklama = (EditText)findViewById(R.id.editText_aciklama);
        final EditText txt_adSoyad=(EditText)findViewById(R.id.editText_adSoyad);
        final EditText txt_numara=(EditText)findViewById(R.id.editText_numara);


        final String resim_yolu = resim_tasi();
        resim.setImageBitmap(BitmapFactory.decodeFile(resim_yolu));
        combo_yil_doldur();
        combo_marka_doldur();


        btn_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Spinner sp_degisen=(Spinner)findViewById(R.id.spinner_combo_degisen);
                    Spinner sp_boyali=(Spinner)findViewById(R.id.spinner_combo_boyali);
                    kayıtEkleme(combo_marka.getSelectedItem().toString(), combo_model.getSelectedItem().toString(), combo_yil.getSelectedItem().toString(),
                            txt_fiyat.getText().toString(), txt_aciklama.getText().toString()+" "+sp_degisen.getSelectedItem().toString()+" "+sp_boyali.getSelectedItem().toString(),
                            resim_yolu.toString(), txt_adSoyad.getText().toString(), txt_numara.getText().toString());
                    Toast.makeText(Kayit_bilgileri_girisi.this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }
                catch(Exception h)
                {
                    Toast.makeText(Kayit_bilgileri_girisi.this, h.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_temizle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combo_marka.setSelection(0);
                combo_model.setSelection(0);
                combo_yil.setSelection(0);
                txt_fiyat.setText("");
                //txt_aciklama.setText(Goster());
                txt_aciklama.setText("");
                txt_adSoyad.setText("");
                txt_numara.setText("");
            }
        });

        resim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Resim_secme.class);
                startActivity(i);
                finish();
            }
        });

        btn_rehber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                    startActivityForResult(intent, 1001);
                }
                catch (Exception h)
                {
                    Toast.makeText(Kayit_bilgileri_girisi.this, "onClick\n"+h.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}

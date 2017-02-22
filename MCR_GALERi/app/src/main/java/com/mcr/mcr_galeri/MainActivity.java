package com.mcr.mcr_galeri;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_ekle,btn_ara,btn_yedek,btn_iletisim;
    Veri_Tabani_Sifre kayit_sifre;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private String[] sutunlar = {"sifre"};
    public String Sifre()
    {
        String return_sifre = "bilmem";
        try {

            SQLiteDatabase db = kayit_sifre.getReadableDatabase();
            Cursor cursorKayit = db.query("Sifre", sutunlar, null, null, null, null, null);
            while (cursorKayit.moveToNext()) {
                return_sifre = cursorKayit.getString(cursorKayit.getColumnIndex("sifre"));
            }
            cursorKayit.close();
        }
        catch (Exception h)
        {
            Toast.makeText(MainActivity.this, h.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return return_sifre;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kayit_sifre=new Veri_Tabani_Sifre(this);

        if (Sifre().equals("true")==false)
        {
            Intent i=new Intent(getApplicationContext(),Sifre_giris.class);
            startActivity(i);
            finish();
        }

        btn_ekle=(Button)findViewById(R.id.btn_ekle);
        btn_ara=(Button)findViewById(R.id.btn_ara);
        btn_yedek=(Button)findViewById(R.id.btn_yedekle);
        btn_iletisim=(Button)findViewById(R.id.btn_iletisim);

        btn_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Resim_secme.class);
                startActivity(i);
                finish();
            }
        });

        btn_ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i=new Intent(getApplicationContext(),Kayit_bilgileri_girisi.class);
                Intent i=new Intent(getApplicationContext(),Araba_ara.class);
                //i.putExtra("yol", "deneme");
                startActivity(i);
                finish();
            }
        });

        btn_yedek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Yedekleme.class);
                startActivity(i);
                finish();
            }
        });

        btn_iletisim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertBuilder.setMessage("MUHAMMED ACAR\nTEL: 0534-636-33-42\nmail: muhammedacar1995@gmail.com\n\n\n© Copyright MCR\nTüm Hakları Saklıdır.");
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();
                }catch (Exception h)
                {
                    Toast.makeText(MainActivity.this, h.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    } //Setting butonu oluşturma

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            //Buraya verileri içeri/dışarı aktar kodları yazılacak
            btn_ara.setText("Basıldı");
            return true;
        }
        return super.onOptionsItemSelected(item);
    } //Setting butonuna basıldığında
}
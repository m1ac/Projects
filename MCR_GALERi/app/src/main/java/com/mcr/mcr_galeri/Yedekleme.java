package com.mcr.mcr_galeri;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Yedekleme extends AppCompatActivity {

    VeriTabani kayitlar;

    private void kayıtEkleme(String tarih,String marka,String model,String yil,String fiyat,String aciklama,String resim,String ad_soyad,String numara) {
        SQLiteDatabase db = kayitlar.getWritableDatabase();
        ContentValues veriler = new ContentValues();

        veriler.put("tarih", tarih);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yedekleme);

        kayitlar=new VeriTabani(this);

        Button btn_iceAktar=(Button)findViewById(R.id.btn_iceAktar);
        Button btn_disaAktar=(Button)findViewById(R.id.btn_disaAktar);

        btn_iceAktar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
                File file = new File(m_path + "/MCR_GALERI/yedek.txt");
                FileReader fileReader = null;
                try {
                    fileReader = new FileReader(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                String line;
                int count=0;
                BufferedReader br = new BufferedReader(fileReader);
                try
                {
                    while ((line = br.readLine()) != null)
                    {
                        String[] dizi=line.split(";");
                        kayıtEkleme(dizi[0],dizi[1],dizi[2],dizi[3],dizi[4],dizi[5],dizi[6],dizi[7],dizi[8]);
                        count++;
                    }
                    br.close();
                    Toast.makeText(Yedekleme.this, count+" ADET ARABA KAYDEDİLDİ", Toast.LENGTH_SHORT).show();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_disaAktar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();

                	File file = new File(m_path+"/MCR_GALERI/yedek.txt");
                	if (!file.exists()) {
                        try
                        {
                            file.createNewFile();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                FileWriter fileWriter = null;
                try
                {
                    fileWriter = new FileWriter(file, false);
                    BufferedWriter bWriter = new BufferedWriter(fileWriter);

                    String[] sutunlar = {"tarih","marka", "model","yil","fiyat","aciklama","resim_yolu","ad_soyad","numara"};
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

                        bWriter.write(bulunan.tarih+";"+bulunan.marka+";"+bulunan.model+";"+bulunan.yil+";"+
                        bulunan.fiyat+";"+bulunan.aciklama+";"+bulunan.resim+";"+bulunan.ad_soyad+";"+bulunan.numara);
                    }
                    cursorKayit.close();
                    bWriter.close();
                    Toast.makeText(Yedekleme.this, "Veriler "+m_path+"/MCR_GALERI/yedek.txt dosyasına kopyalandı", Toast.LENGTH_SHORT).show();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_yedekleme, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

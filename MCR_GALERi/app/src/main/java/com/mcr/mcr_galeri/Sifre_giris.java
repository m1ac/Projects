package com.mcr.mcr_galeri;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sifre_giris extends AppCompatActivity {

    Veri_Tabani_Sifre tablo_sifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre_giris);

        tablo_sifre=new Veri_Tabani_Sifre(this);

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        setTitle(today.hour + "-" + today.monthDay + "-" + (today.month + 1));

        Button btn_giris=(Button)findViewById(R.id.btn_giris);
        final EditText txt_sifre=(EditText)findViewById(R.id.editText_sifre);
        btn_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Time today = new Time(Time.getCurrentTimezone());
                today.setToNow();
                int sifre=(((today.monthDay*5)+((today.month+1)*5))*today.year)*today.hour;
                if(Integer.parseInt(txt_sifre.getText().toString())==sifre)
                {
                    Toast.makeText(Sifre_giris.this, "Şifre Doğru", Toast.LENGTH_SHORT).show();

                    SQLiteDatabase db = tablo_sifre.getWritableDatabase();
                    ContentValues veriler = new ContentValues();
                    veriler.put("sifre", "true");
                    db.insertOrThrow("Sifre", null, veriler);

                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(Sifre_giris.this, "Şifre Yanlış", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

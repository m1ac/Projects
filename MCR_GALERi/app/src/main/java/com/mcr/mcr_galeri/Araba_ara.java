package com.mcr.mcr_galeri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Araba_ara extends AppCompatActivity {

    String tum_kayitlar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_araba_ara);

        setTitle("ARABA ARA");

        final CheckBox cb_marka=(CheckBox)findViewById(R.id.checkBox_marka);
        final CheckBox cb_model=(CheckBox)findViewById(R.id.checkBox_model);
        final CheckBox cb_fiyat=(CheckBox)findViewById(R.id.checkBox_fiyat);

        final Spinner combo_ara_marka=(Spinner)findViewById(R.id.spinner_ara_marka);
        final Spinner combo_ara_model=(Spinner)findViewById(R.id.spinner_ara_model);
        final EditText txt_ara_fiyat_min=(EditText)findViewById(R.id.editText_ara_fiyat_min);
        final EditText txt_ara_fiyat_max=(EditText)findViewById(R.id.editText_ara_fiyat_max);

        String[] markalar = new String[] {"Alfa Romeo", "Aston Martin", "Audi", "Bentley", "BMW", "Chery", "Chevrolet", "Chrysler", "Citroen", "Dacia", "Daewoo", "Daihatsu", "Dodge", "Eagle", "Fiat", "Ford", "Honda", "Hyundai", "Infiniti", "Isuzu", "Kia", "Lada", "Mazda", "Mercedes", "Mitsubishi", "Nissan", "Opel", "Peugeot", "Pontiac", "Proton", "Renault", "Rover", "Seat", "Skoda", "Subaru", "Suzuki", "Tata", "Tofaş", "Toyota", "Volkswagen", "Volvo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinner_item, markalar);
        combo_ara_marka.setAdapter(adapter);

        final Button btn_ara_araba=(Button)findViewById(R.id.btn_ara);
        btn_ara_araba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_marka.isChecked() || cb_model.isChecked() || cb_fiyat.isChecked()) {
                    Intent i = new Intent(getApplicationContext(), Showroom.class);
                    if (cb_marka.isChecked()) {
                        i.putExtra("marka", combo_ara_marka.getSelectedItem().toString());
                    } else {
                        i.putExtra("marka", "boş");
                    }
                    if (cb_model.isChecked()) {
                        i.putExtra("model", combo_ara_model.getSelectedItem().toString());
                    } else {
                        i.putExtra("model", "boş");
                    }
                    if (cb_fiyat.isChecked()) {
                        i.putExtra("fiyat_min", txt_ara_fiyat_min.getText().toString());
                        i.putExtra("fiyat_max", txt_ara_fiyat_max.getText().toString());
                    } else {
                        i.putExtra("fiyat_min", "boş");
                        i.putExtra("fiyat_max", "boş");
                    }
                    i.putExtra("tum_kayitlar","false");
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(Araba_ara.this, "Arama Modu Seçilmedi!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btn_tumKayitlar=(Button)findViewById(R.id.btn_tumKayitlar);
        btn_tumKayitlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tum_kayitlar="true";
                Intent i = new Intent(getApplicationContext(), Showroom.class);
                i.putExtra("tum_kayitlar",tum_kayitlar);
                startActivity(i);
                finish();
            }
        });

        combo_ara_marka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Modeller m=new Modeller();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.spinner_item, m.list_modeller.get(position));
                combo_ara_model.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

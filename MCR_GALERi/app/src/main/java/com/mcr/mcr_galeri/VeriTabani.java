package com.mcr.mcr_galeri;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VeriTabani extends SQLiteOpenHelper {

    private static final String Veritabani_Adi = "Veritabani";
    private static final int Veritabani_Version = 1;
    public VeriTabani(Context context) {
        super(context, Veritabani_Adi, null, Veritabani_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE Kayitlar");
        db.execSQL("CREATE TABLE Kayitlar(tarih String, marka String , model String, yil String, fiyat String, aciklama String, resim_yolu String, ad_soyad String, numara String);");
    }

    public void Sil(String resim_yolu){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Kayitlar", "resim_yolu" + " = ?", new String[] { resim_yolu });
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST Kayitlar");
        onCreate(db);
    }
}
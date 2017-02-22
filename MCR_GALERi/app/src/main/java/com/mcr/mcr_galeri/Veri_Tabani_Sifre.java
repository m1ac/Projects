package com.mcr.mcr_galeri;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Veri_Tabani_Sifre extends SQLiteOpenHelper {

    private static final String Veritabani_Adi = "Veritabani_sifre";
    private static final int Veritabani_Version = 1;

    public Veri_Tabani_Sifre(Context context) {
        super(context, Veritabani_Adi, null, Veritabani_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Sifre(sifre String);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST Sifre");
        onCreate(db);
    }
}

package com.mcr.mcr_galeri;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import java.io.File;

public class Show_image extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        setTitle("AYRINTILAR");

        try {
            ImageView img = (ImageView) findViewById(R.id.imageView_res_goster);
            String yol = getIntent().getStringExtra("yol");

            img.setImageBitmap(BitmapFactory.decodeFile(yol));
        }catch (Exception hata)
        {
            Toast.makeText(Show_image.this, hata.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id==R.id.Sahibi)
        {
            String sahibi=getIntent().getStringExtra("sahibi");
            Toast.makeText(Show_image.this, sahibi, Toast.LENGTH_LONG).show();
        }

        if (id==R.id.Aciklama)
        {
            String aciklama=getIntent().getStringExtra("aciklama");
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Show_image.this);
            alertBuilder.setMessage(aciklama);
            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.show();
        }

        if (id == R.id.Paylas) {
            File myFile = new File(getIntent().getStringExtra("yol"));
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            String ext = myFile.getName().substring(myFile.getName().lastIndexOf(".") + 1);
            String type = mime.getMimeTypeFromExtension(ext);
            Intent sharingIntent = new Intent("android.intent.action.SEND");
            sharingIntent.setType(type);
            sharingIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(myFile));
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
            return true;
        }
        if (id == R.id.Ara) {
            String numara=getIntent().getStringExtra("numara");
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + numara));
            startActivity(call);
            return true;
        }
        if (id == R.id.Sil) {

            try {
                VeriTabani vt = new VeriTabani(getApplicationContext());
                vt.Sil(getIntent().getStringExtra("yol"));
                File f = new File(getIntent().getStringExtra("yol"));
                boolean b = f.delete();
                Toast.makeText(Show_image.this, "SİLME BAŞARILI!", Toast.LENGTH_SHORT).show();
                Toast.makeText(Show_image.this, "Silme işlemi gerçekleştiğinden aramayı yenilemelisiniz!", Toast.LENGTH_LONG).show();
                finish();
            }
            catch (Exception hata)
            {
                Toast.makeText(Show_image.this, hata.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.mcr.mcr_galeri;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Resim_secme extends AppCompatActivity {

    Button btn_kamera,btn_sdkart;
    String file;

    private String getLastImagePath() {
        final String[] imageColumns = {MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA};
        final String imageOrderBy = MediaStore.Images.Media._ID + " DESC";

        Cursor imageCursor = this.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageColumns,
                null, null, imageOrderBy);
        if (imageCursor.moveToFirst()) {
            // int id = imageCursor.getInt(imageCursor
            // .getColumnIndex(MediaStore.Images.Media._ID));
            String fullPath = imageCursor.getString(imageCursor
                    .getColumnIndex(MediaStore.Images.Media.DATA));
            return fullPath;
        } else {
            return "";
        }
    }

    public String getPath(Uri uri) {
        int column_index;
        String imagePath;
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        imagePath = cursor.getString(column_index);

        return cursor.getString(column_index);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1888 && resultCode == RESULT_OK) {
            try {
                Intent i=new Intent(getApplicationContext(),Kayit_bilgileri_girisi.class);
                //i.putExtra("yol", getLastImagePath());
                i.putExtra("yol", file);
                i.putExtra("tasi","false");
                startActivity(i);
                finish();
            }
            catch (Exception h)
            {
                Toast.makeText(getApplicationContext(),h.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode==100 && resultCode==RESULT_OK)
        {
            Uri selectedImage = data.getData();
            Intent i=new Intent(getApplicationContext(),Kayit_bilgileri_girisi.class);
            i.putExtra("yol",getPath(selectedImage));
            i.putExtra("tasi","true");
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resim_secme);

        setTitle("RESİM SEÇ");

        btn_kamera=(Button)findViewById(R.id.btn_kamera);
        btn_sdkart=(Button)findViewById(R.id.btn_sdkart);

        btn_kamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1888);*/

                final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/MCR_GALERI/";
                File newdir = new File(dir);
                newdir.mkdirs();

                Time now = new Time();
                now.setToNow();

                file = dir+String.valueOf(now.monthDay)+String.valueOf(now.month)+String.valueOf(now.year)+String.valueOf(now.hour)+String.valueOf(now.minute)+String.valueOf(now.second)+".jpg";
                File newfile = new File(file);
                try {
                    newfile.createNewFile();
                } catch (IOException e) {}

                Uri outputFileUri = Uri.fromFile(newfile);

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                startActivityForResult(cameraIntent, 1888);
            }
        });

        btn_sdkart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 100);
            }
        });
    }
}

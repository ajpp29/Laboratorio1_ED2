package com.example.angel.laboratorio1_ed2;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LZW extends AppCompatActivity {

    Button AbrirArchivo;
    TextView Ruta,Panel;
    Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lzw);

        AbrirArchivo = (Button) findViewById(R.id.LeerArchivo);
        Ruta =(TextView) findViewById(R.id.TituloLZW);
        Panel=(TextView) findViewById(R.id.PanelLZW);

        AbrirArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent()
                        .addCategory(Intent.CATEGORY_DEFAULT)
                        .setType("*/*")
                        .setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "OpenFile"),123);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==123&& resultCode==RESULT_OK){
            Uri selectedfile=data.getData();
            Toast.makeText(this,selectedfile.toString(),Toast.LENGTH_LONG).show();
            Toast.makeText(this,selectedfile.getPath(),Toast.LENGTH_LONG).show();

            try{
                Panel.setText(readTextFromUri(selectedfile));
            }catch (IOException e){
                Toast.makeText(this,"Hubo un error al obtener el texto del archivo",Toast.LENGTH_LONG).show();
            }

        }
    }

    private String readTextFromUri(Uri uri) throws IOException{
        InputStream inputStream=getContentResolver().openInputStream(uri);
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder stringBuilder=new StringBuilder();
        String linea;
        while((linea=reader.readLine())!=null){
            stringBuilder.append(linea);
        }
        inputStream.close();
        reader.close();
        return stringBuilder.toString();
    }



}

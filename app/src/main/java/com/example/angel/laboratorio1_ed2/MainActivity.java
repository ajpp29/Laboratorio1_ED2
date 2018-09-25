package com.example.angel.laboratorio1_ed2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;



public class MainActivity extends AppCompatActivity {

    Button btnHuffman;
    Button btnLZW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnHuffman=(Button) findViewById(R.id.btnHuffman);
        btnLZW=(Button) findViewById(R.id.btnLZW);

        btnHuffman.setOnClickListener(view ->{
            startActivity(new Intent(getApplicationContext(), Huffman.class));
        });


        btnLZW.setOnClickListener(view ->{
            startActivity(new Intent(getApplicationContext(), LZW.class));
        });


    }


}

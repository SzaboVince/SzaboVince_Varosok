package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private EditText nev;
    private EditText orszag;
    private EditText lakossag;
    private Button felvetel;
    private Button vissza;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();
        vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InsertActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        felvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=nev.getText().toString().trim();
                String o=orszag.getText().toString().trim();
                String l=lakossag.getText().toString().trim();
                if (n.isEmpty()||o.isEmpty()||l.isEmpty()){
                    Toast.makeText(InsertActivity.this,"Minden mező kitöltése kötelező",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (dbHelper.rogzites(n,o,l)){
                        Toast.makeText(InsertActivity.this,"Sikeres adatfelvétel",Toast.LENGTH_SHORT).show();
                        edittextreset();
                    }
                    else{
                        Toast.makeText(InsertActivity.this,"Sikertelen adatfelvétel",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void edittextreset(){
        nev.setText(null);
        orszag.setText(null);
        lakossag.setText(null);
    }

    public void init(){
        nev=findViewById(R.id.nevtext);
        orszag=findViewById(R.id.orszagtext2);
        lakossag=findViewById(R.id.lakossagtext);
        felvetel=findViewById(R.id.felvetelbtn2);
        vissza=findViewById(R.id.visszabtn2);
        dbHelper=new DBHelper(InsertActivity.this);
    }
}
package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SearchResultActivity extends AppCompatActivity {

    public TextView kerestext;
    private Button vissza;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        init();
        Intent intent=getIntent();
        String o=intent.getStringExtra("orszag");
        Cursor adatok=dbHelper.adatLekerdezes(o);
        StringBuilder builder=new StringBuilder();
        while(adatok.moveToNext()){
            builder.append("Név: ").append(adatok.getString(0)).append("\n")
                    .append("Lakosság: ").append(adatok.getString(1)).append("\n\n");
        }
        kerestext.setText(builder);
        vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchResultActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){
        kerestext=findViewById(R.id.kerestext);
        vissza=findViewById(R.id.visszabtn);
        dbHelper=new DBHelper(SearchResultActivity.this);
    }
}
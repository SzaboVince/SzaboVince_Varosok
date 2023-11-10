package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText orszag;
    private Button kereses;
    private Button ujfelvetele;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ujfelvetele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
                finish();
            }
        });
        kereses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String o=orszag.getText().toString();
                if (o.isEmpty()){
                    Toast.makeText(MainActivity.this,"Kérem adjon meg országot!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Cursor adatok=dbHelper.adatLekerdezes(o);
                    if(adatok.getCount()==0){
                        Toast.makeText(MainActivity.this,"Nem található ilyen adat!", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        Intent intent=new Intent(MainActivity.this, SearchResultActivity.class);
                        intent.putExtra("orszag",o);
                        startActivity(intent);

                    }
                }
            }
        });
    }

    public void init(){
        orszag=findViewById(R.id.orszagtext);
        kereses=findViewById(R.id.keresbtn);
        ujfelvetele=findViewById(R.id.felvetelbtn);
        dbHelper=new DBHelper(MainActivity.this);
    }
}
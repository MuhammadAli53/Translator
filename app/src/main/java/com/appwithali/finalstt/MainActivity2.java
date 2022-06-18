package com.appwithali.finalstt;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent; import android.os.Bundle;
import android.os.Handler;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(in);
                finish();
            }
        }, 3000);
    }
}
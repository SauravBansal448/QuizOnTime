package edu.kiet.quiz.quizontime;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Flash extends AppCompatActivity {
    private static  int flashtimeout=2200;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.iconq);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_flash);
        iv=(ImageView)findViewById(R.id.imageView3);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in=new Intent(Flash.this,Login.class);
                startActivity(in);
                finish();

            }
        },flashtimeout);


    }
}

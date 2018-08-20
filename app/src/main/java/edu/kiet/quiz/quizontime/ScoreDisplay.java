package edu.kiet.quiz.quizontime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreDisplay extends AppCompatActivity {
    TextView disScore;
    private Boolean exit = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.iconq);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_score_display);
        disScore=(TextView)findViewById(R.id.scoredisplay);
        Bundle b=getIntent().getExtras();
        int score =b.getInt("score");
        disScore.setText("Score:"+score);
    }
    @Override
    public void onBackPressed() {

        Intent intent=new Intent(ScoreDisplay.this,Tab.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}

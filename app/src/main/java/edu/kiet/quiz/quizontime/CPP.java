package edu.kiet.quiz.quizontime;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

public class CPP extends AppCompatActivity {

    int p=0;

    ArrayList<String> qulist = new ArrayList<String>();
    ArrayList<String> o1list = new ArrayList<String>();
    ArrayList<String> o2list = new ArrayList<String>();
    ArrayList<String> o3list = new ArrayList<String>();
    ArrayList<String> o4list = new ArrayList<String>();
    ArrayList<String> anslist = new ArrayList<String>();
    ArrayList<String> useranswer=new ArrayList<String>();

    private static final String TAG = "quizfire";
    public static final String QUESTION_KEY="Question1";
    public static final String OPTION1_KEY="Option1";
    public static final String OPTION2_KEY="Option2";
    public static final String OPTION3_KEY="Option3";
    public static final String OPTION4_KEY="Option4";
    public static final String ANSWER="Answer";


    TextView ques,textViewCountDowncp;
    RadioButton opt1cp,opt2cp,opt3cp,opt4cp;
    RadioGroup radioGroup;
    Button prevcp,nextcp,submitcp;

    FirebaseFirestore db;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.iconq);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        db = FirebaseFirestore.getInstance();
        ques = (TextView) findViewById(R.id.quescpp);
        opt1cp = (RadioButton) findViewById(R.id.option1cpp);
        opt2cp = (RadioButton) findViewById(R.id.option2cpp);
        opt3cp = (RadioButton) findViewById(R.id.option3cpp);
        opt4cp = (RadioButton) findViewById(R.id.option4cpp);
        radioGroup = (RadioGroup) findViewById(R.id.rg5);

        prevcp=(Button)findViewById(R.id.Prevcpp);
        nextcp=(Button)findViewById(R.id.nextcpp);
        submitcp=(Button)findViewById(R.id.sub);
        textViewCountDowncp=(TextView)findViewById(R.id.textViewcountdowncpp);
        new CountDownTimer(1200000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                long sec=millisUntilFinished/1000;
                long min=sec/60;
                long rem=sec-min*60;
                textViewCountDowncp.setText("Time Remaining: " + min + ":"+ rem);
            }
            @Override
            public void onFinish() {

                for(int i=0;i<useranswer.size();i++){
                    if(useranswer.get(i).equalsIgnoreCase(anslist.get(i))){
                        count++;

                    }else{

                    }
                }
                Intent submit= new Intent(CPP.this,ScoreDisplay.class);
                submit.putExtra("score",+count);

                startActivity(submit);
            }
        }.start();
        nextcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(v);

            }
        });
        prevcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View p) {
                prev(p);
            }
        });
        submitcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opt1cp.isChecked())
                {
                    answer("Option1");
                }
                if(opt2cp.isChecked())
                {
                    answer("Option2");
                }
                if(opt3cp.isChecked())
                {
                    answer("Option3");
                }
                if(opt4cp.isChecked())
                {
                    answer("Option4");
                }


                for(int i=0;i<useranswer.size();i++){
                    if(useranswer.get(i).equalsIgnoreCase(anslist.get(i))){
                        count++;

                    }else{

                    }
                }
                Intent submit= new Intent(CPP.this,ScoreDisplay.class);
                submit.putExtra("score",+count);

                startActivity(submit);
                finish();
            }
        });
        Bundle noofques=getIntent().getExtras();
        int Cpp=noofques.getInt("NOQ");

        db.collection("C++").limit(Cpp)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful() ) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String Question1 = document.getString(QUESTION_KEY);
                                qulist.add(document.getString(QUESTION_KEY));

                                String Option1 = document.getString(OPTION1_KEY);
                                o1list.add(document.getString(OPTION1_KEY));

                                String Option2 = document.getString(OPTION2_KEY);
                                o2list.add(document.getString(OPTION2_KEY));

                                String Option3 = document.getString(OPTION3_KEY);
                                o3list.add(document.getString(OPTION3_KEY));

                                String Option4 = document.getString(OPTION4_KEY);
                                o4list.add(document.getString(OPTION4_KEY));

                                String Answer=document.getString(ANSWER);
                                anslist.add(document.getString(ANSWER));

                            }
                            ques.setText(qulist.get(p));
                            opt1cp.setText(o1list.get(p));
                            opt2cp.setText(o2list.get(p));
                            opt3cp.setText(o3list.get(p));
                            opt4cp.setText(o4list.get(p));
                            p++;


                        } else {

                            Toast.makeText(CPP.this,"Error getting documents",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void answer(String s){
        useranswer.add(s);

    }
    private void prev(View v) {
        db.collection("C++")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            radioGroup.clearCheck();
                            if(p>0) {

                                ques.setText(qulist.get(p));
                                opt1cp.setText(o1list.get(p));
                                opt2cp.setText(o2list.get(p));
                                opt3cp.setText(o3list.get(p));
                                opt4cp.setText(o4list.get(p));
                                p--;
                            }
                            else {
                                for (int i = 0; i < useranswer.size(); i++) {
                                    if (useranswer.get(i).equalsIgnoreCase(anslist.get(i))) {
                                        count++;

                                    } else {

                                    }
                                }
                                Intent submit = new Intent(CPP.this, ScoreDisplay.class);
                                submit.putExtra("score", +count);
                                startActivity(submit);


                            }


                        } else {

                            Toast.makeText(CPP.this,"Error getting documents",Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }

    public void next(View view ){
        if(opt1cp.isChecked())
        {
            answer("Option1");

        }
        if(opt2cp.isChecked())
        {
            answer("Option2");
        }
        if(opt3cp.isChecked())
        {
            answer("Option3");
        }
        if(opt4cp.isChecked())
        {
            answer("Option4");
        }



        opt1cp.setChecked(false);
        opt2cp.setChecked(false);
        opt3cp.setChecked(false);
        opt4cp.setChecked(false);

        db.collection("C++")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            radioGroup.clearCheck();
                            if (p < qulist.size()) {
                                ques.setText(qulist.get(p));
                                opt1cp.setText(o1list.get(p));
                                opt2cp.setText(o2list.get(p));
                                opt3cp.setText(o3list.get(p));
                                opt4cp.setText(o4list.get(p));
                                p++;
                            }else {
                                for (int i = 0; i < useranswer.size(); i++) {
                                    if (useranswer.get(i).equalsIgnoreCase(anslist.get(i))) {
                                        count++;

                                    } else {

                                    }
                                }
                                Intent submit = new Intent(CPP.this, ScoreDisplay.class);
                                submit.putExtra("score", +count);
                                startActivity(submit);
                            }
                        }
                        else {

                            Toast.makeText(CPP.this,"Error getting documents",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    }


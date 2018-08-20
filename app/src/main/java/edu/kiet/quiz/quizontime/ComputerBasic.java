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

public class ComputerBasic extends AppCompatActivity {

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


    TextView ques,textViewCountDowncb;
    RadioButton opt1cb,opt2cb,opt3cb,opt4cb;
    RadioGroup radioGroup;
    Button prevcb,nextcb,submitcb;

    FirebaseFirestore db;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_basic);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.iconq);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        db = FirebaseFirestore.getInstance();
        ques = (TextView) findViewById(R.id.quescb);
        opt1cb = (RadioButton) findViewById(R.id.option1cb);
        opt2cb = (RadioButton) findViewById(R.id.option2cb);
        opt3cb = (RadioButton) findViewById(R.id.option3cb);
        opt4cb = (RadioButton) findViewById(R.id.option4cb);
        radioGroup = (RadioGroup) findViewById(R.id.rg7);

        prevcb=(Button)findViewById(R.id.Prevcb);
        nextcb=(Button)findViewById(R.id.nextcb);
        submitcb=(Button)findViewById(R.id.submitcb);
        textViewCountDowncb=(TextView)findViewById(R.id.textViewcountdowncb);
        new CountDownTimer(1200000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                long sec=millisUntilFinished/1000;
                long min=sec/60;
                long rem=sec-min*60;
                textViewCountDowncb.setText("Time Remaining: " + min + ":"+ rem);
            }

            @Override
            public void onFinish() {

                for(int i=0;i<useranswer.size();i++){
                    if(useranswer.get(i).equalsIgnoreCase(anslist.get(i))){
                        count++;

                    }else{

                    }
                }
                Intent submit= new Intent(ComputerBasic.this,ScoreDisplay.class);
                submit.putExtra("score",+count);

                startActivity(submit);
            }
        }.start();
        nextcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(v);

            }
        });
        prevcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View p) {
                prev(p);
            }
        });
        submitcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opt1cb.isChecked())
                {
                    answer("Option1");
                }
                if(opt2cb.isChecked())
                {
                    answer("Option2");
                }
                if(opt3cb.isChecked())
                {
                    answer("Option3");
                }
                if(opt4cb.isChecked())
                {
                    answer("Option4");
                }


                for(int i=0;i<useranswer.size();i++){
                    if(useranswer.get(i).equalsIgnoreCase(anslist.get(i))){
                        count++;

                    }else{

                    }
                }
                Intent submit= new Intent(ComputerBasic.this,ScoreDisplay.class);
                submit.putExtra("score",+count);

                startActivity(submit);
                finish();
            }
        });
        Bundle noofques=getIntent().getExtras();
        int cb=noofques.getInt("NOQ");

        db.collection("Computer Fundamentals").limit(cb)
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
                            opt1cb.setText(o1list.get(p));
                            opt2cb.setText(o2list.get(p));
                            opt3cb.setText(o3list.get(p));
                            opt4cb.setText(o4list.get(p));
                            p++;


                        } else {

                            Toast.makeText(ComputerBasic.this,"Error getting documents",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void answer(String s){
        useranswer.add(s);

    }
    private void prev(View v) {
        db.collection("Computer Fundamentals")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            radioGroup.clearCheck();
                            if(p>0) {
                                ques.setText(qulist.get(p));
                                opt1cb.setText(o1list.get(p));
                                opt2cb.setText(o2list.get(p));
                                opt3cb.setText(o3list.get(p));
                                opt4cb.setText(o4list.get(p));
                                p--;
                            }
                            else {
                                for (int i = 0; i < useranswer.size(); i++) {
                                    if (useranswer.get(i).equalsIgnoreCase(anslist.get(i))) {
                                        count++;

                                    } else {

                                    }
                                }
                                Intent submit = new Intent(ComputerBasic.this, ScoreDisplay.class);
                                submit.putExtra("score", +count);
                                startActivity(submit);


                            }



                        } else {

                            Toast.makeText(ComputerBasic.this,"Error getting documents",Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }

    public void next(View view ){
        if(opt1cb.isChecked())
        {
            answer("Option1");

        }
        if(opt2cb.isChecked())
        {
            answer("Option2");
        }
        if(opt3cb.isChecked())
        {
            answer("Option3");
        }
        if(opt4cb.isChecked())
        {
            answer("Option4");
        }



        opt1cb.setChecked(false);
        opt2cb.setChecked(false);
        opt3cb.setChecked(false);
        opt4cb.setChecked(false);

        db.collection("Computer Fundamentals")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            radioGroup.clearCheck();
                            if (p < qulist.size()) {

                                ques.setText(qulist.get(p));
                                opt1cb.setText(o1list.get(p));
                                opt2cb.setText(o2list.get(p));
                                opt3cb.setText(o3list.get(p));
                                opt4cb.setText(o4list.get(p));
                                p++;
                            }else {
                                for (int i = 0; i < useranswer.size(); i++) {
                                    if (useranswer.get(i).equalsIgnoreCase(anslist.get(i))) {
                                        count++;

                                    } else {

                                    }
                                }
                                Intent submit = new Intent(ComputerBasic.this, ScoreDisplay.class);
                                submit.putExtra("score", +count);
                                startActivity(submit);
                                }

                        }else {

                            Toast.makeText(ComputerBasic.this,"Error getting documents",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                }
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    }


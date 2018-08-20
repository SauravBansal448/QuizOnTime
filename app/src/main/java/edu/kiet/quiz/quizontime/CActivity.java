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

public class CActivity extends AppCompatActivity {
    int p = 0;

    ArrayList<String> qulist = new ArrayList<String>();
    ArrayList<String> o1list = new ArrayList<String>();
    ArrayList<String> o2list = new ArrayList<String>();
    ArrayList<String> o3list = new ArrayList<String>();
    ArrayList<String> o4list = new ArrayList<String>();
    ArrayList<String> anslist = new ArrayList<String>();
    ArrayList<String> useranswer = new ArrayList<String>();

    private static final String TAG = "quizfire";
    public static final String QUESTION_KEY = "Question1";
    public static final String OPTION1_KEY = "Option1";
    public static final String OPTION2_KEY = "Option2";
    public static final String OPTION3_KEY = "Option3";
    public static final String OPTION4_KEY = "Option4";
    public static final String ANSWER = "Answer";


    TextView quesc,textViewCountDown2;
    RadioButton opt1c, opt2c, opt3c, opt4c;
    Button prev, next, submit;
    RadioGroup radioGroup;

    FirebaseFirestore db;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        db = FirebaseFirestore.getInstance();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.iconq);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
            quesc = (TextView) findViewById(R.id.quesc);
            opt1c = (RadioButton) findViewById(R.id.option1c);
            opt2c = (RadioButton) findViewById(R.id.option2c);
            opt3c = (RadioButton) findViewById(R.id.option3c);
            opt4c = (RadioButton) findViewById(R.id.option4c);
             radioGroup = (RadioGroup) findViewById(R.id.rg2);
            prev = (Button) findViewById(R.id.Prevc);
            next = (Button) findViewById(R.id.nextc);
            submit = (Button) findViewById(R.id.submitc);
        textViewCountDown2=(TextView)findViewById(R.id.textViewcountdown2);
        new CountDownTimer(1200000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                long sec=millisUntilFinished/1000;
                long min=sec/60;
                long rem=sec-min*60;
                textViewCountDown2.setText("Time Remaining: " + min + ":"+ rem);
            }
            @Override
            public void onFinish() {

                for(int i=0;i<useranswer.size();i++){
                    if(useranswer.get(i).equalsIgnoreCase(anslist.get(i))){
                        count++;

                    }else{

                    }
                }
                Intent submit= new Intent(CActivity.this,ScoreDisplay.class);
                submit.putExtra("score",+count);

                startActivity(submit);
            }
        }.start();
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    next(v);

                }
            });
            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View p) {
                    prev(p);
                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (opt1c.isChecked()) {
                        answer("Option1");
                    }
                    if (opt2c.isChecked()) {
                        answer("Option2");
                    }
                    if (opt3c.isChecked()) {
                        answer("Option3");
                    }
                    if (opt4c.isChecked()) {
                        answer("Option4");
                    }


                    for (int i = 0; i < useranswer.size(); i++) {
                        if (useranswer.get(i).equalsIgnoreCase(anslist.get(i))) {
                            count++;

                        } else {

                        }
                    }
                    Intent submit= new Intent(CActivity.this,ScoreDisplay.class);
                    submit.putExtra("score",+count);

                    startActivity(submit);
                    finish();
                }
            });
       Bundle noofques=getIntent().getExtras();
        int c=noofques.getInt("NOQ");


            db.collection("C Language").limit(c)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {

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

                                    String Answer = document.getString(ANSWER);
                                    anslist.add(document.getString(ANSWER));

                                }
                                quesc.setText(qulist.get(p));
                                opt1c.setText(o1list.get(p));
                                opt2c.setText(o2list.get(p));
                                opt3c.setText(o3list.get(p));
                                opt4c.setText(o4list.get(p));
                                p++;


                            } else {

                                Toast.makeText(CActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }


        private void answer(String s) {
            useranswer.add(s);

        }

        private void prev(View v) {
            db.collection("C Language")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {
                                if(p>0) {

                                    radioGroup.clearCheck();
                                    quesc.setText(qulist.get(p));
                                    opt1c.setText(o1list.get(p));
                                    opt2c.setText(o2list.get(p));
                                    opt3c.setText(o3list.get(p));
                                    opt4c.setText(o4list.get(p));
                                    p--;
                                }else {
                                    for (int i = 0; i < useranswer.size(); i++) {
                                        if (useranswer.get(i).equalsIgnoreCase(anslist.get(i))) {
                                            count++;

                                        } else {

                                        }
                                    }
                                    Intent submit = new Intent(CActivity.this, ScoreDisplay.class);
                                    submit.putExtra("score", +count);
                                    startActivity(submit);


                                }



                            } else {

                                Toast.makeText(CActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



        }

        public void next(View view) {
            if (opt1c.isChecked()) {
                answer("Option1");
            }
            if (opt2c.isChecked()) {
                answer("Option2");
            }
            if (opt3c.isChecked()) {
                answer("Option3");
            }
            if (opt4c.isChecked()) {
                answer("Option4");
            }


            opt1c.setChecked(false);
            opt2c.setChecked(false);
            opt3c.setChecked(false);
            opt4c.setChecked(false);

            db.collection("C Language")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {
                                radioGroup.clearCheck();
                                if(p<qulist.size()) {

                                    quesc.setText(qulist.get(p));
                                    opt1c.setText(o1list.get(p));
                                    opt2c.setText(o2list.get(p));
                                    opt3c.setText(o3list.get(p));
                                    opt4c.setText(o4list.get(p));
                                    p++;
                                }
                                else {
                                    for (int i = 0; i < useranswer.size(); i++) {
                                        if (useranswer.get(i).equalsIgnoreCase(anslist.get(i))) {
                                            count++;

                                        } else {

                                        }
                                    }
                                    Intent submit = new Intent(CActivity.this, ScoreDisplay.class);
                                    submit.putExtra("score", +count);
                                    startActivity(submit);


                                }
                            } else {

                                Toast.makeText(CActivity.this,"Error getting documents",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



        }
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

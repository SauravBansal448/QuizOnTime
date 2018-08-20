package edu.kiet.quiz.quizontime;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;


public class Tab extends AppCompatActivity implements Dialog.DialogListener{
    private FirebaseAuth mAuth;
    CardView javadd,clang,python,apti,cpplus,lrg,javasc,combas;
    TextView javad,clang1,pyth,aptit,cpluslus,lorg,javascript,compubas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        javadd=(CardView)findViewById(R.id.javadd);
        javad=(TextView)findViewById(R.id.javad);
        clang=(CardView)findViewById(R.id.Clang);
        clang1=(TextView)findViewById(R.id.clang1);
        python=(CardView)findViewById(R.id.pyth);
        pyth=(TextView)findViewById(R.id.pyth1);
        apti=(CardView)findViewById(R.id.apti);
        aptit=(TextView)findViewById(R.id.aptit);
        cpplus=(CardView)findViewById(R.id.cpp);
        cpluslus=(TextView)findViewById(R.id.cpp1);
        lrg=(CardView)findViewById(R.id.lr);
        lorg=(TextView)findViewById(R.id.lr1);
        javasc=(CardView)findViewById(R.id.js);
        javascript=(TextView)findViewById(R.id.js1);
        combas=(CardView)findViewById(R.id.cf);
        compubas=(TextView)findViewById(R.id.cf11);

        javad.setTextColor(Color.parseColor("#000000"));
        clang1.setTextColor(Color.parseColor("#000000"));
        pyth.setTextColor(Color.parseColor("#000000"));
        aptit.setTextColor(Color.parseColor("#000000"));
        cpluslus.setTextColor(Color.parseColor("#000000"));
        lorg.setTextColor(Color.parseColor("#000000"));
        javascript.setTextColor(Color.parseColor("#000000"));
        compubas.setTextColor(Color.parseColor("#000000"));

        mAuth=FirebaseAuth.getInstance();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.iconq);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        javad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("Java");
            }
        });

        javadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("Java");
            }
        });
        clang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("Clang");
            }
        });

        clang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("Clang");
            }
        });
        apti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("aptitude");
            }
        });

        aptit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("aptitude");
            }
        });
        python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("python");
            }
        });
        pyth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("python");
            }
        });

        cpplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("c++");
            }
        });
        cpluslus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("c++");
            }
        });
        lrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("lr");
            }
        });
        lorg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("lr");
            }
        });
        javasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("js");
            }
        });
        javascript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("js");
            }
        });
        combas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("cb");
            }
        });
        compubas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog("cb");
            }
        });

        }
        public void openDialog(String choice){
        Dialog dialog=new Dialog();
        Bundle b = new Bundle();
        b.putString("choice",choice);
        dialog.setArguments(b);
        dialog.show(getSupportFragmentManager(),choice);
        }

    @Override
    public void applyTexts(String noofques) {


        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tabbbb,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.logout){
            mAuth.signOut();
            Intent logout=new Intent(this,Login.class);
            logout.putExtra("finish", true);
            logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logout);
            return true;
        }

        if(item.getItemId()==R.id.about){
            Intent about=new Intent(Tab.this,About.class);
            startActivity(about);
            return  true;
        }
         return true;
        }


    public void java(View view) {
        Intent java=new Intent(this,MainActivity.class);
        startActivity(java);

    }
    public void java11(View view) {
        Intent java1=new Intent(this,MainActivity.class);
        startActivity(java1);
    }

    public void Clanguage(View view) {
        Intent clanguage=new Intent(this,CActivity.class);
        startActivity(clanguage);
    }
    public void C11(View view) {
        Intent python=new Intent(this,CActivity.class);
        startActivity(python);
    }

    public void LR(View view) {
        Intent LR=new Intent(this,LRActivity.class);
        startActivity(LR);
    }
    public void LR11(View view) {
        Intent python=new Intent(this,LRActivity.class);
        startActivity(python);
    }

    public void Aptitude(View view) {
        Intent Aptitude=new Intent(this,Aptitude.class);
        startActivity(Aptitude);

    }
    public void Aptitude11(View view) {
        Intent python=new Intent(this,Aptitude.class);
        startActivity(python);
    }

    public void CPP(View view) {
        Intent CPP=new Intent(this,CPP.class);
        startActivity(CPP);
    }
    public void cpp11(View view) {
        Intent CPP=new Intent(this,CPP.class);
        startActivity(CPP);
    }


    public void python(View view) {
        Intent python=new Intent(this,Python.class);
        startActivity(python);
    }
    public void python11(View view) {
        Intent python=new Intent(this,Python.class);
        startActivity(python);
    }

    public void js(View view) {
        Intent js=new Intent(this,JavaScript.class);
        startActivity(js);
    }
    public void js11(View view) {
        Intent js=new Intent(this,JavaScript.class);
        startActivity(js);
    }

    public void cf(View view) {
        Intent cf=new Intent(this,ComputerBasic.class);
        startActivity(cf);
    }
    public void cf11(View view) {
        Intent cf=new Intent(this,ComputerBasic.class);
        startActivity(cf);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(a);
        finish();
        System.exit(0);
    }
}

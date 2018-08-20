package edu.kiet.quiz.quizontime;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;



public class Login extends AppCompatActivity {
    EditText email, pass;
    Button login, registration;
    Boolean exit=true;
    TextView textViewSignin;


    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    //private SignInButton mGooglebtn;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "Login_Activity";
    private final static int RC_SIGN_IN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.iconq);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        textViewSignin = (TextView) findViewById(R.id.textViewSignUp);
       // mGooglebtn = (SignInButton) findViewById(R.id.googlesign);
        login = (Button) findViewById(R.id.login);
        registration = (Button) findViewById(R.id.signup);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(Login.this, Tab.class));

                }


            }
        };
        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
       /* mGooglebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });*/


    }


   /* private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        progressDialog = progressDialog.show(Login.this, "Processing...", "Please Wait...", true);

    }*/

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthwithGoogle(account);
            } else {
                Toast.makeText(Login.this, "Auth went wrong", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();


            }
        }
    }*/

  /*  private void firebaseAuthwithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {

                            Toast.makeText(Login.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn() {

        String emails = email.getText().toString().trim();
        String password = pass.getText().toString().trim();

        progressDialog = progressDialog.show(Login.this, "Processing...", "Please Wait...", true);
        if (TextUtils.isEmpty(emails)) {
            Toast.makeText(Login.this, "Please enter the Email_Id & Password", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(Login.this, "Please enter the Password", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();

        }
        else {
            mAuth.signInWithEmailAndPassword(emails, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Login Successfully.", Toast.LENGTH_SHORT).show();

                    }
                        else{
                        Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }

                }


            });
        }

    }

    public void register() {
        final String emailId = email.getText().toString().trim();
        final String passwordd = pass.getText().toString().trim();

        progressDialog = progressDialog.show(Login.this, "Processing...", "Please Wait...", true);
        if (TextUtils.isEmpty(emailId) || TextUtils.isEmpty(passwordd)) {
            Toast.makeText(Login.this, "Please enter the Email_Id & Password", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();

        } else {
            (mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();


                            if (task.isSuccessful()) {
                                Toast.makeText(Login.this, "Registration Successfully.", Toast.LENGTH_SHORT).show();
                            } else {

                                Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        }
                    });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(a);
        finish();
        System.exit(0);


    }
}

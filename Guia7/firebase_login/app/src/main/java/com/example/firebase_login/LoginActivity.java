package com.example.firebase_login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import java.util.Arrays;
public class LoginActivity extends AppCompatActivity {

    private EditText emailTV, passwordTV;
    private Button loginBtn, btnGoogle;
    private Button loginButton;
    private CallbackManager callbackManager;
    private ProgressBar progressBar;
    //Constante
    int RC_SIGN_IN = 1;
    String TAG = "GoogleSignIn";
    private FirebaseAuth mAuth;
    private FirebaseAuth mAut;
    private GoogleSignInClient mGoogleSignInClient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeUI();
        mAuth = FirebaseAuth.getInstance();
        //Facebook_________
        mAut=FirebaseAuth.getInstance();
        callbackManager=CallbackManager.Factory.create();
        loginButton=findViewById(R.id.btnFacebook);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this,Arrays.asList("email","public_profile"));
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFcebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
            }
        });
// Configurar Google Sign In
GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R.string.default_web_client_id))
        .requestEmail()
        .build();
// Crear un GoogleSignInClient con las opciones especificadas por gso.
mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) {
            signInGoogle(); } });
    }

    private void loginUserAccount() {
        progressBar.setVisibility(View.VISIBLE);

        String email, password;
        email = emailTV.getText().toString();
        password = passwordTV.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void initializeUI() {
        emailTV = findViewById(R.id.email);
        passwordTV = findViewById(R.id.password);
        btnGoogle = findViewById(R.id.btnGoogle);
        loginBtn = findViewById(R.id.login);
        progressBar = findViewById(R.id.progressBar);
    }
    private void signInGoogle() { Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_SIGN_IN); }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Resultado devuelto al iniciar el Intent de GoogleSignInApi.getSignInIntent (...);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
         if (requestCode == RC_SIGN_IN) { Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data); if(task.isSuccessful()){ try {
             // Google Sign In was successful, authenticate with Firebase
             GoogleSignInAccount account = task.getResult(ApiException.class);
             Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
             firebaseAuthWithGoogle(account.getIdToken());
         } catch (ApiException e) {
             // Google Sign In fallido, actualizar GUI
             Log.w(TAG, "Google sign in failed", e);
         } }else{ Log.d(TAG, "Error, login no exitoso:" + task.getException().toString());
         Toast.makeText(this, "Ocurrio un error. "+task.getException().toString(), Toast.LENGTH_LONG).show();
         }
         }

    }
    private void firebaseAuthWithGoogle(String idToken) { AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
    mAuth.signInWithCredential(credential) .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information Log.d(TAG, "signInWithCredential:success");
                FirebaseUser user = mAuth.getCurrentUser();
                //Iniciar DASHBOARD u otra actividad luego del SigIn Exitoso
                Intent dashboardActivity = new Intent(LoginActivity.this, DashboardGoogle.class);
                startActivity(dashboardActivity); LoginActivity.this.finish();
                } else {
                // If sign in fails, display a message to the user.
                 Log.w(TAG, "signInWithCredential:failure", task.getException());
            } } });
    }
    @Override protected void onStart() { FirebaseUser user = mAuth.getCurrentUser();
    if(user!=null){
        //si no es null el usuario ya esta logueado
        // mover al usuario al dashboard
        Intent dashboardActivity = new Intent(LoginActivity.this, DashboardGoogle.class);
        startActivity(dashboardActivity);
    } super.onStart();
        FirebaseUser user2=mAut.getCurrentUser();
        if (user2!=null){
            ingresado();
        }
    }



    private void handleFcebookAccessToken(AccessToken token) {

        AuthCredential credential= FacebookAuthProvider.getCredential(token.getToken());
        mAut.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            ingresado();
                            Toast.makeText(LoginActivity.this,"Ingreso Correctamente",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginActivity.this,"No puede ingresar con este cuenta",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public  void ingresado(){

        Intent intent=new Intent(getApplicationContext(),DashboardFacebook.class);
        startActivity(intent);
        finish();
    }
            }



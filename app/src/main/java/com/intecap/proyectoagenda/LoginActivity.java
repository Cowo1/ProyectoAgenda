package com.intecap.proyectoagenda;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN =123;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInCLient;

    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_login);

       mAuth = FirebaseAuth.getInstance();


    }
}

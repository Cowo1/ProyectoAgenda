package com.intecap.proyectoagenda;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

   protected void onCreate(Bundle savedIstanceState) {
       super.onCreate(savedIstanceState);
       setContentView(R.layout.activity_splash);

       mAuth = FirebaseAuth.getInstance();

       new Handler().postDelayed(()->{
           FirebaseUser currentUser = mAuth.getCurrentUser();
           if(currentUser != null){
               startActivity(new Intent(SplashActivity.this, MainActivity.class));
           }else {
              startActivity(new Intent(SplashActivity.this, LoginActivity.class));
           }
           finish();
       },2000);

   }
}

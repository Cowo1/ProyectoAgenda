package com.intecap.proyectoagenda;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.protobuf.Api;



public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN =123;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInCLient;

    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_login);

       mAuth = FirebaseAuth.getInstance();
       GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
               .requestIdToken(getString(com.firebase.ui.auth.R.string.default_web_client_id))
               .requestEmail()
               .build();

       mGoogleSignInCLient = GoogleSignIn.getClient(this, gso);

       findViewById(R.id.btn_google).setOnClickListener(v -> signInWithGoogle());

    }

    private void signInWithGoogle() {
        Intent signIntent = mGoogleSignInCLient.getSignInIntent();
        startActivityForResult(signIntent, RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleGoogleSignInResult(task);

        }
    }
    private void handleGoogleSignInResult(Task<GoogleSignInAccount> task){
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            if(account != null){
                firebaseAuthWithGoogle(account.getIdToken());

            }
        }catch(ApiException e){
            Toast.makeText(this, "Error en Google Sign-in" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this,task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));
                        finish();

                    }else{
                        Toast.makeText(this, "Error: "+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

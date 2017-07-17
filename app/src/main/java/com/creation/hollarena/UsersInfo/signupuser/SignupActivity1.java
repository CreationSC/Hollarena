package com.creation.hollarena.UsersInfo.signupuser;

/**
 * Created by MMenem on 7/16/2017.
 */


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.creation.hollarena.R;
import com.creation.hollarena.UsersInfo.Magazine.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity1 extends AppCompatActivity {
    private static final String TAG = "SignupActivity1";
    public Button next;
    private EditText etEmail, etpin;


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);
        next = (Button) findViewById(R.id.btnNext);
        etEmail = (EditText) findViewById(R.id.etemail);
        etpin = (EditText) findViewById(R.id.etpin);
        firebaseAuth = FirebaseAuth.getInstance();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerUser(etEmail.getText().toString(), etpin.getText().toString());


            }
        });

    }

    private void registerUser(String s, String s1) {
        firebaseAuth.createUserWithEmailAndPassword(s, s1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Intent i = new Intent(SignupActivity1.this, SignupActivity2.class);
                        startActivity(i);

                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignupActivity1.this, "Failed", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


}










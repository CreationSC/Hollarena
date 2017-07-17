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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.creation.hollarena.R;
import com.creation.hollarena.UsersInfo.Magazine.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "SignupActivity2";
    public Spinner spinner;
    private TextView TV_sex;
    private String name, phone, age, gender, userID;
    private EditText etEmail, etName, etPhone, etAge, etUserName;
    private Button btnSignUp;
    View focusView = null;


    //adding Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        TV_sex = (TextView) findViewById(R.id.TV_sex);
        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etphone);
        etAge = (EditText) findViewById(R.id.etage);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        gender = TV_sex.getText().toString();


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Sex, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //  Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                //    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
                // ...
            }
        };
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d(TAG, "onDataChange: Added information to database: \n" +
                        dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: Submit pressed.");
                name = etName.getText().toString();
                phone = etPhone.getText().toString();
                gender = TV_sex.getText().toString();
                age = etAge.getText().toString();

                Log.d(TAG, "onClick: Attempting to submit to database: \n" +
                        "name: " + name + "\n" +
                        "phone number: " + phone + "\n"
                );

                //handle the exception if the EditText fields are null
                if (!name.equals("") && !phone.equals("") && !age.equals("") && !gender.equals("")) {
                    UserInformation userInformation = new UserInformation(name, phone, age, gender);
                    myRef.child("users").child(userID).setValue(userInformation);
                   // toastMessage("New Information has been saved.");
                    Intent i = new Intent(SignupActivity2.this, HomeActivity.class);
                    startActivity(i);

                } else {
                    toastMessage("Fill out all the fields!");
                }
            }
        });
        ;


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = spinner.getSelectedItem().toString();
        TV_sex.setText(text);
        TV_sex.getText();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}

package com.creation.hollarena.UsersInfo.Magazine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.creation.hollarena.R;
import com.creation.hollarena.UsersInfo.signupuser.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
//    FirebaseDatabase mFirebaseDatabase;
//    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();


        myRef= FirebaseDatabase.getInstance().getReference().child("users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                UserInformation user = dataSnapshot.getValue(UserInformation.class);
                Toast.makeText(HomeActivity.this, "Welcome " + user.getName(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(HomeActivity.this, "check connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

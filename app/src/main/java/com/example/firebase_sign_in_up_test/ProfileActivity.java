package com.example.firebase_sign_in_up_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase database;
    private FirebaseUser firebaseUser;
    private User user;

    @BindView(R.id.user_name_txt)
    TextView user_name_txt;
    @BindView(R.id.user_email_txt)
    TextView user_email_txt;
    @BindView(R.id.user_phone_txt)
    TextView user_phone_txt;
    @BindView(R.id.user_address_txt)
    TextView user_address_txt;
    @BindView(R.id.user_gender_txt)
    TextView user_gender_txt;
    @BindView(R.id.user_edit_btn)
    Button user_edit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        database = FirebaseDatabase.getInstance();
        //get firebase database instance and reference
        mDatabaseReference= database.getReference().child("Users");
        //get firebase authentication instance
        mAuth=FirebaseAuth.getInstance();
        user=new User();
        ButterKnife.bind(this);
        readDataFromDatabase();
    }

    private void readDataFromDatabase(){
        if(mAuth!=null){
            String userId=mAuth.getCurrentUser().getUid();
            Query specific_user=mDatabaseReference.child(userId);
            // Attach a listener to read the data for specific user
            specific_user.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    user = dataSnapshot.getValue(User.class);
                    Log.d("User:",user.getUsername());
                    if(user!=null){
                        setDataOnField(user);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(ProfileActivity.this, "The read failed: " + databaseError.getCode(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setDataOnField(User user){
        user_name_txt.setText(user.getUsername());
        user_email_txt.setText(user.getEmail());
        user_phone_txt.setText(user.getPhone());
        user_address_txt.setText(user.getAddress());
        user_gender_txt.setText(user.getGender());
    }
}

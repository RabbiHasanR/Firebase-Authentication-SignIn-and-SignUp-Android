package com.example.firebase_sign_in_up_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPasswordActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;

    @BindView(R.id.email_address)
    EditText emailAddressEtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @OnClick(R.id.btn_send_email)
    void onSubmitClick() {
        //check validation
        if (!validate()) {
            sentEmailFailed();
            return;
        }
        final ProgressDialog progressDialog = new ProgressDialog(ResetPasswordActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait..");
        progressDialog.show();
        mFirebaseAuth.sendPasswordResetEmail(emailAddressEtx.getText().toString().trim())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(ResetPasswordActivity.this, "An email has been sent to you.", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(ResetPasswordActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * show login failed message
     */
    private void sentEmailFailed() {
        Toast.makeText(getBaseContext(), "Sent email failed.", Toast.LENGTH_LONG).show();
    }

    /**
     * validate every input field
     * @return
     */
    private boolean validate() {
        boolean valid = true;

        String email = emailAddressEtx.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailAddressEtx.setError("enter a valid email address");
            valid = false;
        } else {
            emailAddressEtx.setError(null);
        }

        return valid;
    }

}

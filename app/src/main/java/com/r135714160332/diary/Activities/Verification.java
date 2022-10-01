package com.r135714160332.diary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;
import com.r135714160332.diary.R;

import java.util.concurrent.TimeUnit;

public class Verification extends AppCompatActivity {

    Button button;
    EditText otp;
    ProgressBar progressBar;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        button = findViewById(R.id.register_button);
        otp = findViewById(R.id.otp);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        String phoneNumber = getIntent().getStringExtra("phone");
        sendVerificationCode(phoneNumber);

    }

    private void sendVerificationCode(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            progressBar.setVisibility(View.VISIBLE);
            super.onCodeSent(s, forceResendingToken);
            verificationCode = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code != null){
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(Verification.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };

    public void manualVerification(View view){
        String code = otp.getText().toString();
        if(code.length() != 6){
            Toast.makeText(Verification.this,"Invalid OTP", Toast.LENGTH_SHORT).show();
        }else
            progressBar.setVisibility(View.VISIBLE);
            verifyCode(code);
    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode,code);
        signInUserByCredentials(credential);
    }

    private void signInUserByCredentials(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(Verification.this, task -> {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(Verification.this, DashBoard.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        progressBar.setVisibility(View.GONE);
                    }else{
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(Verification.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
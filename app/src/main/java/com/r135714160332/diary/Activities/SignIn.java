package com.r135714160332.diary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;
import com.r135714160332.diary.R;

public class SignIn extends AppCompatActivity {

    Button button;
    EditText phone;
    CountryCodePicker codePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        button = findViewById(R.id.button);
        phone = findViewById(R.id.editTextPhone);
        codePicker = findViewById(R.id.code);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = phone.getText().toString();
                String code = codePicker.getSelectedCountryCode();
                if(phoneNumber.length() != 10){
                    Toast.makeText(SignIn.this,"Enter Valid Phone Number",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(SignIn.this, Verification.class);
                    intent.putExtra("phone", "+"+code+phoneNumber);
                    startActivity(intent);
                }
            }
        });
    }

}
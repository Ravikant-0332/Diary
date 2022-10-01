package com.r135714160332.diary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.r135714160332.diary.R;

public class DiaryPage extends AppCompatActivity {

    TextView pageTimeStamp, pageContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        pageTimeStamp = findViewById(R.id.page_time_stamp);
        pageContent = findViewById(R.id.page_content);
        pageTimeStamp.setText(intent.getStringExtra("time"));
        pageContent.setText(intent.getStringExtra("content"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
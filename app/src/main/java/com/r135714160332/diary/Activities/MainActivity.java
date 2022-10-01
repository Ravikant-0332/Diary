package com.r135714160332.diary.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.r135714160332.diary.R;

public class MainActivity extends AppCompatActivity {

    Animation bounce_in, circle_animation, fade_in, right_in, left_in;
    TextView sp_d, sp_i, sp_a, sp_r, sp_y, sp_slogan, sp_slogan_fake;
    ImageView sp_circle_im;
    ConstraintLayout sp_constraintLayout;
    LinearLayout sp_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sp_d = findViewById(R.id.sp_d);
        sp_i = findViewById(R.id.sp_i);
        sp_a = findViewById(R.id.sp_a);
        sp_r = findViewById(R.id.sp_r);
        sp_y = findViewById(R.id.sp_y);
        sp_slogan = findViewById(R.id.sp_slogan);
        sp_slogan_fake = findViewById(R.id.sp_slogan_fake);
        sp_circle_im = findViewById(R.id.sp_circle_im);
        sp_linearLayout = findViewById(R.id.sp_linearLayout);
        sp_constraintLayout = findViewById(R.id.sp_constraint_layout);

        fade_in = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.fade_in);
        right_in = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.right_in);
        left_in = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.left_in);
        circle_animation = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.circle_anim);

        sp_circle_im.setAnimation(circle_animation);
        sp_d.setAnimation(left_in);
        sp_i.setAnimation(left_in);
        sp_a.setAnimation(fade_in);
        sp_r.setAnimation(right_in);
        sp_y.setAnimation(right_in);
        sp_slogan.setAnimation(fade_in);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sp_constraintLayout.setBackgroundColor(getColor(R.color.orange));
                new Handler().postDelayed(() -> {
                    Intent intent = new Intent(MainActivity.this, DashBoard.class);
                    startActivity(intent);
                },500);
            }
        },1500);
    }
}
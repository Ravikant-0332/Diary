package com.r135714160332.diary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.r135714160332.diary.Fragments.HomeFragment;
import com.r135714160332.diary.Fragments.ProfileFragment;
import com.r135714160332.diary.Fragments.WriteFragment;
import com.r135714160332.diary.R;

public class DashBoard extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    BottomNavigationView navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        navBar = findViewById(R.id.bottomNavigationView);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if(user == null){
            Intent intent = new Intent(this,SignIn.class);
            startActivity(intent);
            finish();
        }else {
            navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();
                    switch (id) {
                        case R.id.home: {
                            loadFragmentR(new HomeFragment(), true);
                            break;
                        }

                        case R.id.write: {
                            loadFragmentR(new WriteFragment(), false);
                            break;
                        }

                        case R.id.profile: {
                            loadFragmentR(new ProfileFragment(), false);
                            break;
                        }
                    }
                    return true;
                }
            });
            navBar.setSelectedItemId(R.id.home);
        }
    }

    private void loadFragmentR(Fragment fragment, boolean flag){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(flag)
            transaction.add(R.id.fragment,fragment);
        else
            transaction.replace(R.id.fragment,fragment);
        transaction.commit();
    }

    public void logout(View view){
        mAuth.signOut();
        Intent intent = new Intent(DashBoard.this, SignIn.class);
        startActivity(intent);
    }
}
package com.r135714160332.diary.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.r135714160332.diary.Activities.DashBoard;
import com.r135714160332.diary.Models.Entry;
import com.r135714160332.diary.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 *
 */
public class WriteFragment extends Fragment {

    EditText content;
    TextView timeStamp;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference(mAuth.getCurrentUser().getUid());

    public WriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_write, container, false);
        timeStamp = view.findViewById(R.id.write_time_stamp);
        content = view.findViewById(R.id.write_content);
        timeStamp.setText(currentTime());

        view.findViewById(R.id.write_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = content.getText().toString();
                if(!text.trim().isEmpty()){
                    String time = timeStamp.getText().toString();
                    reference.child("entries").push().setValue(new Entry(time,text));
                    Toast.makeText(getActivity(), "Page Added", Toast.LENGTH_SHORT).show();
                    timeStamp.setText(currentTime());
                    content.setText("");
                }
                else{
                    Toast.makeText(getActivity(), "Write Something to Add", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    private String currentTime(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd MMMM yyyy, [HH:mm:ss]");
        return formatter.format(new Date());
    }
}
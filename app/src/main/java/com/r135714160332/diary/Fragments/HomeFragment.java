package com.r135714160332.diary.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.r135714160332.diary.Adapters.DashBoardAdapter;
import com.r135714160332.diary.Models.Entry;
import com.r135714160332.diary.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Entry> entries = new ArrayList<>();
    ProgressBar progressBar;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference(mAuth.getCurrentUser().getUid());

    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        progressBar = view.findViewById(R.id.home_progressbar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = view.findViewById(R.id.home_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateEntries();
        return view;
    }

    private void updateEntries(){
        reference.child("entries").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        entries.add(snapshot1.getValue(Entry.class));
                    }
                    DashBoardAdapter adapter = new DashBoardAdapter(getActivity(), entries);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setAdapter(adapter);
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(),"Nothing in Diary, Start new journey by writing your first page",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}
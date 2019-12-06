package com.example.scrumproject.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.scrumproject.Database.Groups;
import com.example.scrumproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CreateGroupFragment extends Fragment {


    EditText name;
    Button btn;
    FirebaseDatabase database;
    DatabaseReference ref;
    Groups groups;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View FragmentUI = inflater.inflate(R.layout.fragment_create_group, container, false);

        name = FragmentUI.findViewById(R.id.editText);
        btn = FragmentUI.findViewById(R.id.button);
        ref = database.getReference("groups");
       // groups = new Groups();
        return FragmentUI;
    }

}

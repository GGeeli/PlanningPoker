package com.example.scrumproject.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.scrumproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateGroupFragment extends Fragment {


    EditText name;
    Button btn;
    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View FragmentUI = inflater.inflate(R.layout.fragment_create_group, container, false);


        return FragmentUI;
    }

}

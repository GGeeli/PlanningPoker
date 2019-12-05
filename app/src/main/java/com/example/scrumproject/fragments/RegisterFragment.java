package com.example.scrumproject.fragments;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scrumproject.R;
import com.example.scrumproject.Users;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterFragment extends Fragment {

    EditText username;
    EditText email;
    EditText password;
    Button btn;

    DatabaseReference databaseUsers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //FirebaseApp.initializeApp(getActivity());
        View FragmentUI = inflater.inflate(R.layout.fragment_register, container, false);

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        username = FragmentUI.findViewById(R.id.username);
        email = FragmentUI.findViewById(R.id.email);
        password = FragmentUI.findViewById(R.id.password);
        btn = FragmentUI.findViewById(R.id.btn_create);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                addUser();
            }
        });

        return FragmentUI;
    }

    private void addUser(){
        String s_name = username.getText().toString().trim();
        String s_email = email.getText().toString().trim();
        String s_pass = password.getText().toString().trim();

        if( (!TextUtils.isEmpty(s_name)) ||(!TextUtils.isEmpty(s_email)) || !TextUtils.isEmpty(s_pass) )
        {
            String id = databaseUsers.push().getKey();
            Users user = new Users(id,s_name,s_email,s_pass);
            databaseUsers.child(id).setValue(user);
            Toast.makeText(getActivity(), "User created!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity(), "Fill all the inputs", Toast.LENGTH_SHORT).show();
        }
    }

}

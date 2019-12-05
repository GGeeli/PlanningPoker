package com.example.scrumproject.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scrumproject.Groups;
import com.example.scrumproject.R;
import com.example.scrumproject.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class CreateGroupFragment extends Fragment {


    EditText name;
    Button btn;
    DatabaseReference databaseGroups;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View FragmentUI = inflater.inflate(R.layout.fragment_create_group, container, false);

        databaseGroups = FirebaseDatabase.getInstance().getReference("groups");
        name = FragmentUI.findViewById(R.id.editText);
        btn = FragmentUI.findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGroup();
            }
        });
        return FragmentUI;
    }

    private void addGroup()
    {
        String s_name = name.getText().toString().trim();

        if(!TextUtils.isEmpty(s_name)){
            String id = databaseGroups.push().getKey();
            Users A=new Users();
            Groups group = new Groups(id,s_name,A);
            databaseGroups.child(id).setValue(group);
            Toast.makeText(getActivity(), "Group created!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "Fill all the inputs", Toast.LENGTH_SHORT).show();
        }
    }

}

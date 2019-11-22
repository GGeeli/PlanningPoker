package com.example.scrumproject;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Register extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         View view = inflater.inflate(R.layout.fragment_register, container, false);

        Button createAcc = view.findViewById(R.id.button);

        createAcc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Login());
                fr.commit();
            }
        });

         return view;
    }

}

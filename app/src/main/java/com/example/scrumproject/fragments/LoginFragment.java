package com.example.scrumproject.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scrumproject.MainActivity;
import com.example.scrumproject.NavigationDrawerActivity;
import com.example.scrumproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    EditText email;
    EditText password;
    Button btn,btn_reg;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View FragmentUI = inflater.inflate(R.layout.fragment_login, container, false);
        email = FragmentUI.findViewById(R.id.edit_name);
        password = FragmentUI.findViewById(R.id.edit_password);
        btn = FragmentUI.findViewById(R.id.btn_login);
        btn_reg = FragmentUI.findViewById(R.id.button2);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(getContext(), "You are logged in!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "Please login!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new RegisterFragment());
                fragmentTransaction.commit();

            }
        });

        return FragmentUI;
    }

    private void Login(){
        String s_email = email.getText().toString();
        String s_pass = password.getText().toString();


        if (s_email.isEmpty()){
            email.setError("Enter email!");
            email.requestFocus();
        }else if(s_pass.isEmpty() )
        {
            password.setError("Enter password");
            password.requestFocus();
        }
        else if(!(email.getText().toString().equals("") && password.getText().toString().equals("") ))
        {
            mFirebaseAuth.signInWithEmailAndPassword(s_email,s_pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(getActivity(), "Login Error!", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        Toast.makeText(getActivity(), "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(),NavigationDrawerActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }

    }

}

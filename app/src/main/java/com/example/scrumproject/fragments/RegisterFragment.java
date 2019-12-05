package com.example.scrumproject.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.scrumproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment {


    EditText email;
    EditText password;
    Button btn;
    FirebaseAuth mFirebaseAuth;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mFirebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(getActivity());

        View FragmentUI = inflater.inflate(R.layout.fragment_register, container, false);
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
        progressDialog.setMessage("Registering user");

        return FragmentUI;
    }

    private void addUser(){
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
        else if(s_email.isEmpty() && s_pass.isEmpty())
        {
            Toast.makeText(getActivity(), "Field are empty!", Toast.LENGTH_SHORT).show();
        }
        else if(!(s_email.isEmpty() && s_pass.isEmpty() ) )
        {
            mFirebaseAuth.createUserWithEmailAndPassword(s_email,s_pass)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(getActivity(), "You need a valid email address!", Toast.LENGTH_SHORT).show();
                            }else
                            {
                                Toast.makeText(getActivity(), "User created!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

}

package com.example.scrumproject.fragments;

import android.app.ProgressDialog;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import static android.content.ContentValues.TAG;

public class RegisterFragment extends Fragment {


    EditText email,password,username;
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
        username = FragmentUI.findViewById(R.id.username);
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
        final String s_username = username.getText().toString();
        if (s_email.isEmpty()){
            email.setError("Enter email!");
            email.requestFocus();
        }else if(s_pass.isEmpty() )
        {
            password.setError("Enter password");
            password.requestFocus();
        }else if (s_username.isEmpty())
        {
            username.setError("Enter username");
            username.requestFocus();

        }
        else if(!(email.getText().toString().equals("") && password.getText().toString().equals("") && username.getText().toString().equals("")))
        {
            mFirebaseAuth.createUserWithEmailAndPassword(s_email,s_pass)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(getActivity(), "Error creating user!", Toast.LENGTH_SHORT).show();
                            }else
                            {

                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(s_username).build();
                                user.updateProfile(profileChangeRequest)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Log.d(TAG,"Username updated");
                                            }
                                        });

                                Toast.makeText(getActivity(), "User created!", Toast.LENGTH_SHORT).show();
                                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.fragment_container,new LoginFragment());
                                fragmentTransaction.commit();
                            }
                        }
                    });
        }
    }

}

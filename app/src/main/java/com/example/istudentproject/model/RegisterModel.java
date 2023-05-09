package com.example.istudentproject.model;

import android.text.TextUtils;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterModel {
    private FirebaseAuth firebaseAuth;

    public RegisterModel() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void register(String email, String password, OnRegistrationListener listener) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listener.onRegistrationSuccess();
                    } else {
                        listener.onRegistrationFailure(task.getException().getMessage());
                    }
                });
    }

    public interface OnRegistrationListener {
        void onRegistrationSuccess();
        void onRegistrationFailure(String errorMessage);
    }
}



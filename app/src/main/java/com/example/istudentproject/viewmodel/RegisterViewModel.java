package com.example.istudentproject.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<Boolean> registrationSuccess = new MutableLiveData<>();
    private MutableLiveData<String> registrationErrorMessage = new MutableLiveData<>();

    public void registerUser(String email, String password) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        registrationSuccess.postValue(true);
                    } else {
                        registrationSuccess.postValue(false);
                        registrationErrorMessage.postValue(task.getException().getMessage());
                    }
                });
    }

    public LiveData<Boolean> getRegistrationSuccess() {
        return registrationSuccess;
    }

    public LiveData<String> getRegistrationErrorMessage() {
        return registrationErrorMessage;
    }
}
package com.example.istudentproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends ViewModel {

    private FirebaseAuth mAuth;

    private MutableLiveData<FirebaseUser> userLiveData;

    public LoginViewModel() {
        mAuth = FirebaseAuth.getInstance();
        userLiveData = new MutableLiveData<>();
    }

    public LiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        userLiveData.postValue(user);
                    } else {
                        userLiveData.postValue(null);
                    }
                });
    }
}

package com.example.istudentproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.istudentproject.model.LoginModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<Boolean> loginSuccessful = new MutableLiveData<>(false);
    private FirebaseAuth mAuth;

    public LoginViewModel() {
        mAuth = FirebaseAuth.getInstance();
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getLoginSuccessful() {
        return loginSuccessful;
    }

    public void login(LoginModel loginModel) {
        isLoading.setValue(true);

        mAuth.signInWithEmailAndPassword(loginModel.getEmail(), loginModel.getPassword())
                .addOnCompleteListener(task -> {
                    isLoading.setValue(false);

                    if (task.isSuccessful()) {
                        loginSuccessful.setValue(true);
                    } else {
                        errorMessage.setValue("Authentication failed.");
                    }
                });
    }
}


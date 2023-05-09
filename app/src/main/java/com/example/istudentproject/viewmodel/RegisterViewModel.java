package com.example.istudentproject.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.istudentproject.model.RegisterModel;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private RegisterModel registerModel = new RegisterModel();

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void register(String email, String password, RegisterModel.OnRegistrationListener listener) {
        isLoading.setValue(true);
        registerModel.register(email, password, new RegisterModel.OnRegistrationListener() {
            @Override
            public void onRegistrationSuccess() {
                isLoading.postValue(false);
                listener.onRegistrationSuccess();
            }

            @Override
            public void onRegistrationFailure(String errorMessage) {
                isLoading.postValue(false);
                listener.onRegistrationFailure(errorMessage);
            }
        });
    }
}

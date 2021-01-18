package com.android.pickjobsapp.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.android.pickjobsapp.network.AuthService;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {
    private final AuthService authService;
    private final MutableLiveData<FirebaseUser> userMutableLiveData;
    private final MutableLiveData<Boolean> failedLoginMutableLiveData;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        authService = new AuthService(application);
        userMutableLiveData = authService.getUserMutableLiveData();
        failedLoginMutableLiveData = authService.getFailedLoginMutableLiveData();
    }

    public void login(String email, String password) {
        authService.login(email, password);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getFailedLogin() {
        return failedLoginMutableLiveData;
    }

}

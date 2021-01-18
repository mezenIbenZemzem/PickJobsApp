package com.android.pickjobsapp.network;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthService {
    private Application application;
    private FirebaseAuth mAuth;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> failedLoginMutableLiveData;

    public AuthService(Application application) {
        this.application = application;
        mAuth = FirebaseAuth.getInstance();
        failedLoginMutableLiveData = new MutableLiveData<>();
        userMutableLiveData = new MutableLiveData<>();
    }

    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(application.getMainExecutor(), task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        userMutableLiveData.setValue(user);
                    } else {
                       failedLoginMutableLiveData.setValue(false);
                    }
                });
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getFailedLoginMutableLiveData() {
        return failedLoginMutableLiveData;
    }
}

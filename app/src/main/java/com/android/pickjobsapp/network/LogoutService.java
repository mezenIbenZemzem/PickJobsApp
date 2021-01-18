package com.android.pickjobsapp.network;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;

public class LogoutService {
    private MutableLiveData<Boolean> succesLogoutMutableLiveData = new MutableLiveData<>();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public MutableLiveData<Boolean> getSuccesLogoutMutableLiveData() {
        return succesLogoutMutableLiveData;
    }

    public LogoutService () {
    }

    public void logout() {
        mAuth.signOut();
    }

   public void checkLogout() {
       if (mAuth.getCurrentUser() == null) {
           succesLogoutMutableLiveData.setValue(true);
       }
   }
}

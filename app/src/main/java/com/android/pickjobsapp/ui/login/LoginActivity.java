package com.android.pickjobsapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.android.pickjobsapp.common.Preferences;
import com.android.pickjobsapp.ui.home.HomeActivity;
import com.android.pickjobsapp.R;
import com.android.pickjobsapp.common.HelpFunctions;
import com.android.pickjobsapp.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

   ActivityLoginBinding activityLoginBinding;
   private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this , R.layout.activity_login);
        loginViewModel =  new ViewModelProvider(this).get(LoginViewModel.class);
        activityLoginBinding.loginBt.setOnClickListener(this);
        loginListner();
    }

    @Override
    protected void onStart() {
        super.onStart();
        autoLogin();
    }

    public void autoLogin() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(LoginActivity.this , HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void loginListner() {
        loginViewModel.getUserMutableLiveData().observe(this, firebaseUser -> {
            activityLoginBinding.setIsLoading(false);
            if (firebaseUser != null) {
                firebaseUser.getIdToken(true).addOnSuccessListener(getTokenResult -> {
                    Preferences preferences = new Preferences(LoginActivity.this);
                    preferences.setToken(getTokenResult.getToken());
                    Intent intent = new Intent(LoginActivity.this , HomeActivity.class);
                    startActivity(intent);
                    finish();
                });
            }
        });

        loginViewModel.getFailedLogin().observe(this, status -> {
            activityLoginBinding.setIsLoading(false);
            if (!status)  HelpFunctions.showSnackbar(activityLoginBinding.getRoot(), getString(R.string.something_went_wrong) , LoginActivity.this);
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login_bt) {
            activityLoginBinding.setIsLoading(true);
            String email = Objects.requireNonNull(activityLoginBinding.emailEd.getText()).toString();
            String password = Objects.requireNonNull(activityLoginBinding.passwordEd.getText()).toString();
            if (HelpFunctions.isValidLogin(email, password)) {
                loginViewModel.login(email,password);
            } else {
                activityLoginBinding.setIsLoading(false);
                HelpFunctions.showSnackbar(activityLoginBinding.getRoot(), getString(R.string.required_fields) , LoginActivity.this);
            }
        }
    }
}
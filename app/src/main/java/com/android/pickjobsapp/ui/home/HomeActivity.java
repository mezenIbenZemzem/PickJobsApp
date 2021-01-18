package com.android.pickjobsapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.android.pickjobsapp.R;
import com.android.pickjobsapp.common.HelpFunctions;
import com.android.pickjobsapp.common.Preferences;
import com.android.pickjobsapp.databinding.ActivityHomeBinding;
import com.android.pickjobsapp.listner.PickJobsListListner;
import com.android.pickjobsapp.model.PatchApiRequestBody;
import com.android.pickjobsapp.model.PickJob;
import com.android.pickjobsapp.ui.login.LoginActivity;


public class HomeActivity extends AppCompatActivity implements PickJobsListListner, View.OnClickListener {
    ActivityHomeBinding activityHomeBinding;
    HomeViewModel homeViewModel;
    private PickJobAdapter pickJobAdapter;
    private PickJobsListListner pickJobsListListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        pickJobsListListner = this;
        activityHomeBinding.logoutBt.setOnClickListener(this);
        getPickjobs();
        updatePickjobsList();
    }

    public void getPickjobs() {
        activityHomeBinding.setIsLoading(true);
        homeViewModel.getListMutableLiveData().observe(this, pickJobList -> {
            activityHomeBinding.setIsLoading(false);
            if (pickJobList != null) {
                pickJobAdapter = new PickJobAdapter(pickJobList, HomeActivity.this, pickJobsListListner);
                activityHomeBinding.pickjobsListRv.setAdapter(pickJobAdapter);
            } else {
                HelpFunctions.showSnackbar(activityHomeBinding.getRoot(), getString(R.string.something_went_wrong), HomeActivity.this);
            }
        });
    }

    public void updatePickjobsList() {
        homeViewModel.getEditResponseStatus().observe(this, status -> {
            if (status) {
                homeViewModel.getUpdatedPickJobs();
            } else {
                activityHomeBinding.setIsLoading(false);
                HelpFunctions.showSnackbar(activityHomeBinding.getRoot(), getString(R.string.something_went_wrong), HomeActivity.this);
            }
        });
    }

    public void checkLogout(){
        homeViewModel.getCheckLogoutMutableLiveData().observe(this, logoutStatus -> {
            if (logoutStatus) {
                Intent intent = new Intent(HomeActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                HelpFunctions.showSnackbar(activityHomeBinding.getRoot(), getString(R.string.something_went_wrong), HomeActivity.this);
            }
        });
    }

    @Override
    public void onPickJobClick(PickJob pickJob) {
        activityHomeBinding.setIsLoading(true);
        Preferences preferences = new Preferences(this);
        String id = pickJob.getId();
        String token = preferences.getToken();
        PatchApiRequestBody body = HelpFunctions.setPatchApiRequestBody(pickJob);
        homeViewModel.editStatus(id, body, token);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.logout_bt) {
            homeViewModel.logout();
            checkLogout();
        }
    }
}
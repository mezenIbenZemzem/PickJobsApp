package com.android.pickjobsapp.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.pickjobsapp.listner.OnFirestoreTaskComplete;
import com.android.pickjobsapp.model.PatchApiRequestBody;
import com.android.pickjobsapp.model.PickJob;
import com.android.pickjobsapp.network.FirestoreService;
import com.android.pickjobsapp.network.LogoutService;
import com.android.pickjobsapp.network.RetrofitInstance;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel implements OnFirestoreTaskComplete {
    private FirestoreService firestoreService = new FirestoreService(this);
    private MutableLiveData<List<PickJob>> listMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> editResponseStatusMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> checkLogoutMutableLiveData;
    private LogoutService logoutService = new LogoutService();

    public LiveData<List<PickJob>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public LiveData<Boolean> getEditResponseStatus() {
        return editResponseStatusMutableLiveData;
    }

    public MutableLiveData<Boolean> getCheckLogoutMutableLiveData() {
        return checkLogoutMutableLiveData;
    }

    public HomeViewModel() {
        firestoreService.getPickJobs();
        checkLogoutMutableLiveData = logoutService.getSuccesLogoutMutableLiveData();
    }

    @Override
    public void onPickjobsAdded(List<PickJob> pickJobList) {
        listMutableLiveData.setValue(pickJobList);
    }

    @Override
    public void onError(Exception exception) {
        listMutableLiveData.setValue(null);
    }

    public void editStatus(String id, PatchApiRequestBody body, String token) {
        Call<ResponseBody> call = RetrofitInstance.getRetrofitInstance(token).editPickJobStatus(id, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                editResponseStatusMutableLiveData.setValue(response.isSuccessful());
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                editResponseStatusMutableLiveData.setValue(false);
            }
        });
    }

    public void getUpdatedPickJobs() {
        firestoreService.getPickJobsUpdated();
    }

    public void logout() {
        logoutService.logout();
        checkLogout();
    }

    public void checkLogout() {
        logoutService.checkLogout();
    }

}

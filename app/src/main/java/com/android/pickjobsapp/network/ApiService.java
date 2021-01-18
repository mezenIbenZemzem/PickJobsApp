package com.android.pickjobsapp.network;

import com.android.pickjobsapp.model.PatchApiRequestBody;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface ApiService {
    @PATCH("api/pickjobs/{pickJobId}")
    Call<ResponseBody> patchPickjob(@Path("pickJobId") String id, @Body PatchApiRequestBody body);
}

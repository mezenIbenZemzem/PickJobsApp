package com.android.pickjobsapp.network;

import com.android.pickjobsapp.common.Constants;
import com.android.pickjobsapp.model.PatchApiRequestBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public ApiService apiService;
    public static RetrofitInstance retrofitInstance;
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private RetrofitInstance (String token) {
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("Authorization", "Bearer "+token)
                    .build();
            return chain.proceed(request);
        });
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static RetrofitInstance getRetrofitInstance(String token){
        if (retrofitInstance == null) {
            retrofitInstance = new RetrofitInstance(token);
        }
        return retrofitInstance;
    }

    public Call<ResponseBody> editPickJobStatus(String id , PatchApiRequestBody body){
        return apiService.patchPickjob(id, body);
    }
}

package com.softwarepintar.almuna_trans.ui.TiketSaya.sewa;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SewaViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<SewaResultItem>> result;

    public LiveData<List<SewaResultItem>> getSewa() {
        if (result == null) {
            result = new MutableLiveData<>();
            load();
        }
        return result;
    }

    private void load() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<SewaResponse> call = apiService.list_sewa(Utils.user_id);
        call.enqueue(new Callback<SewaResponse>() {
            @Override
            public void onResponse(Call<SewaResponse> call, Response<SewaResponse> response) {
                result.setValue(response.body().getResult());
            }

            @Override
            public void onFailure(Call<SewaResponse> call, Throwable t) {
                Log.e("Gagal", t.getMessage());
            }
        });
    }
}

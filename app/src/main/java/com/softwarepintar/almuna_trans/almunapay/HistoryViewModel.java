package com.softwarepintar.almuna_trans.almunapay;

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

public class HistoryViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<RiwayatResultItem>> result;

    public LiveData<List<RiwayatResultItem>> getKelas(){
        if (result==null){
            result=new MutableLiveData<>();
            load();
        }
        return result;
    }

    private void load() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService=retrofit.create(APIService.class);
        Call<RiwayatResponse> call=apiService.his_almunapay(Utils.user_id);
        call.enqueue(new Callback<RiwayatResponse>() {
            @Override
            public void onResponse(Call<RiwayatResponse> call, Response<RiwayatResponse> response) {
                result.setValue(response.body().getResult());
            }

            @Override
            public void onFailure(Call<RiwayatResponse> call, Throwable t) {
                Log.e("Gagal",t.getMessage());
            }
        });
    }
}

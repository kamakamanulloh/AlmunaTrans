package com.softwarepintar.almuna_trans.kota;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.ui.home.Response_Kota;
import com.softwarepintar.almuna_trans.ui.home.ResultasalItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class KotaViewModel extends ViewModel {
    private MutableLiveData<List<ResultasalItem>>results;

    public LiveData<List<ResultasalItem>>getKota(){
        if (results==null){
            results=new MutableLiveData<>();
            load();
        }
        return results;
    }

    private void load() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService=retrofit.create(APIService.class);
        Call<Response_Kota>response_kotaCall=apiService.list_kota();
        response_kotaCall.enqueue(new Callback<Response_Kota>() {
            @Override
            public void onResponse(Call<Response_Kota> call, Response<Response_Kota> response) {
                results.setValue(response.body().getResultasal());
            }

            @Override
            public void onFailure(Call<Response_Kota> call, Throwable t) {
                Log.e("Gagal",t.getMessage());
            }
        });
    }


}

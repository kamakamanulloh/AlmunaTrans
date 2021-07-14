package com.softwarepintar.almuna_trans.ui.TiketSaya.tiketaktif;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.ui.TiketSaya.TiketResponse;
import com.softwarepintar.almuna_trans.ui.TiketSaya.TiketResultItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TiketAktifViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<TiketResultItem>> result;

    public LiveData<List<TiketResultItem>> getTiket(){
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
        Call<TiketResponse> call=apiService.tiket(Utils.user_id);
        call.enqueue(new Callback<TiketResponse>() {
            @Override
            public void onResponse(Call<TiketResponse> call, Response<TiketResponse> response) {
                result.setValue(response.body().getResult());
            }

            @Override
            public void onFailure(Call<TiketResponse> call, Throwable t) {
                Log.e("Gagal",t.getMessage());
            }
        });
    }

}

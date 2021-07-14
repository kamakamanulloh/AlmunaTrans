package com.softwarepintar.almuna_trans.Info;

import android.util.Log;
import android.widget.BaseAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.ui.home.InfoResponse;
import com.softwarepintar.almuna_trans.ui.home.InfoResultItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<InfoResultItem>>results;

    public LiveData<List<InfoResultItem>>getResult(){
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
        APIService baseAdapter=retrofit.create(APIService.class);
        Call<InfoResponse>call=baseAdapter.all_info();
        call.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {
                results.setValue(response.body().getResult());
            }


            @Override
            public void onFailure(Call<InfoResponse> call, Throwable throwable) {
                Log.d("Gagal", throwable.getMessage());
            }
        });
    }
}

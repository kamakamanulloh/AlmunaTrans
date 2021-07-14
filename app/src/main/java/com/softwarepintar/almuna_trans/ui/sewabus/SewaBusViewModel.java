package com.softwarepintar.almuna_trans.ui.sewabus;

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

public class SewaBusViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<KelasResultItem>>kelas_result;

    public LiveData<List<KelasResultItem>>getKelas(){
        if (kelas_result==null){
            kelas_result=new MutableLiveData<>();
            loadkelas();
        }
        return kelas_result;
    }

    private void loadkelas() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService=retrofit.create(APIService.class);
        Call<KelasResponse>call=apiService.list_bus();
        call.enqueue(new Callback<KelasResponse>() {
            @Override
            public void onResponse(Call<KelasResponse> call, Response<KelasResponse> response) {
                kelas_result.setValue(response.body().getResult());
            }

            @Override
            public void onFailure(Call<KelasResponse> call, Throwable t) {
                Log.e("Gagal",t.getMessage());
            }
        });
    }
}

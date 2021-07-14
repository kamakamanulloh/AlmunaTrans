package com.softwarepintar.almuna_trans.Jadwal;

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

public class JadwalViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<Jadwal_ResultItem>>jadwal_result;
    public LiveData<List<Jadwal_ResultItem>>getJadwal(String ruteasal,String rutetujuan,String rutetanggal){
        if (jadwal_result==null){
            jadwal_result=new MutableLiveData<>();
            loadjadwal(ruteasal,rutetujuan,rutetanggal);
        }
        return jadwal_result;

    }


    private void loadjadwal(String ruteasal, String rutetujuan, String rutetanggal) {

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService=retrofit.create(APIService.class);
        Call<Jadwal_Response> call=apiService.list_jadwal(ruteasal,rutetujuan,rutetanggal);
        call.enqueue(new Callback<Jadwal_Response>() {
            @Override
            public void onResponse(Call<Jadwal_Response> call, Response<Jadwal_Response> response) {
                int Response_Item = response.body().getValue();

                if (Response_Item == 1) {
                    jadwal_result.setValue(response.body().getResult());

                }

            }

            @Override
            public void onFailure(Call<Jadwal_Response> call, Throwable t) {
                Log.d("Gagal",t.getMessage());
            }
        });

    }
}

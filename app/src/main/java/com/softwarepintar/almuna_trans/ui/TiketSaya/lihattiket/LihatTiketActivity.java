package com.softwarepintar.almuna_trans.ui.TiketSaya.lihattiket;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.MultiFormatWriter;
import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.ui.TiketSaya.PenumpangResponse;
import com.softwarepintar.almuna_trans.ui.TiketSaya.PenumpangResultItem;
import com.softwarepintar.almuna_trans.ui.TiketSaya.TiketResultItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LihatTiketActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvtiket)
    RecyclerView rvtiket;
    private LihatAdapter penumpangAdapter;

    private List<PenumpangResultItem> penumpangResultItemList = new ArrayList<>();
    TiketResultItem tiketResultItem;
    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_tiket);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Tiket ");

        penumpangAdapter = new LihatAdapter(this, penumpangResultItemList);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        ((LinearLayoutManager) linearLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        rvtiket.setLayoutManager(linearLayoutManager);

        rvtiket.setItemAnimator(new DefaultItemAnimator());
        showpenumpang();


    }

    private void showpenumpang() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<PenumpangResponse> call = apiService.penumpang(getIntent().getStringExtra("kode"));
        call.enqueue(new Callback<PenumpangResponse>() {
            @Override
            public void onResponse(Call<PenumpangResponse> call, Response<PenumpangResponse> response) {
                int Response_Item = response.body().getValue();
                rvtiket.setVisibility(View.VISIBLE);
                if (Response_Item == 1) {

                    penumpangResultItemList = response.body().getResult();
                    penumpangAdapter = new LihatAdapter(LihatTiketActivity.this, penumpangResultItemList);

                    rvtiket.setAdapter(penumpangAdapter);
                } else {

                    Toast.makeText(LihatTiketActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PenumpangResponse> call, Throwable t) {

                Toast.makeText(LihatTiketActivity.this, "Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

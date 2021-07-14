package com.softwarepintar.almuna_trans.almunapay;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.payment.BankResponse;
import com.softwarepintar.almuna_trans.payment.BankResultItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransferActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    private List<BankResultItem> bankResultItems = new ArrayList<>();

    private BankTopupAdapter bankTopupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        bankTopupAdapter = new BankTopupAdapter(getApplicationContext(), bankResultItems);
        rv.setLayoutManager(linearLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(bankTopupAdapter);
        Utils.total=getIntent().getStringExtra("nominal");
        showbank();

    }

    private void showbank() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<BankResponse> call = apiService.list_bank();
        call.enqueue(new Callback<BankResponse>() {
            @Override
            public void onResponse(Call<BankResponse> call, Response<BankResponse> response) {
                int Response_Item = response.body().getValue();
                rv.setVisibility(View.VISIBLE);
                if (Response_Item == 1) {

                    bankResultItems = response.body().getResult();
                    bankTopupAdapter = new BankTopupAdapter(getApplicationContext(), bankResultItems);

                    rv.setAdapter(bankTopupAdapter);
                } else {

                    Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BankResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

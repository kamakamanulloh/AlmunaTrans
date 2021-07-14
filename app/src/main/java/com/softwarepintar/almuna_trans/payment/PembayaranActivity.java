package com.softwarepintar.almuna_trans.payment;

import android.app.ProgressDialog;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.softwarepintar.almuna_trans.API.Utils.detail_order;
import static com.softwarepintar.almuna_trans.API.Utils.saldo;

public class PembayaranActivity extends AppCompatActivity {


    @BindView(R.id.rvbank)
    RecyclerView rvbank;
    ProgressDialog progress;
    private BankAdapter bankAdapter;
    public static String user_id;

    private List<BankResultItem> bankResultItems = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        bankAdapter = new BankAdapter(this, bankResultItems);
        rvbank.setLayoutManager(linearLayoutManager);
        rvbank.setItemAnimator(new DefaultItemAnimator());
        saldo=getIntent().getStringExtra("saldo");
        Utils.qty=Integer.parseInt(getIntent().getStringExtra("qty"));
        Utils.detail_order=getIntent().getStringExtra("name");
        user_id=getIntent().getStringExtra(Utils.TAG_user_id);


//        Toast.makeText(this, batas_bayar+" "+kdbayar+" "+total, Toast.LENGTH_SHORT).show();

        rvbank.setAdapter(bankAdapter);
        showbank();






    }

    private void showbank() {
        progress = new ProgressDialog(PembayaranActivity.this);
        progress.setCancelable(true);
        progress.setMessage("Sedang Proses");
        progress.show();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService=retrofit.create(APIService.class);
        Call<BankResponse>call=apiService.list_bank();
        call.enqueue(new Callback<BankResponse>() {
            @Override
            public void onResponse(Call<BankResponse> call, Response<BankResponse> response) {
                int Response_Item = response.body().getValue();
                rvbank.setVisibility(View.VISIBLE);
                progress.dismiss();
                if (Response_Item==1){
                    bankResultItems = response.body().getResult();
                    bankAdapter = new BankAdapter(PembayaranActivity.this, bankResultItems);

                    rvbank.setAdapter(bankAdapter);
                }
                else{
                    Toast.makeText(PembayaranActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BankResponse> call, Throwable t) {
                progress.dismiss();

                Toast.makeText(PembayaranActivity.this, "Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }


}

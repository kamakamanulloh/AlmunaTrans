package com.softwarepintar.almuna_trans.ui.sewabus.pembayaran;

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

import static com.softwarepintar.almuna_trans.API.Utils.saldo;

public class PembayaranSewaActivity extends AppCompatActivity {

    @BindView(R.id.rvbank)
    RecyclerView rvbank;
    private BankAdapterSewa bankAdapterSewa;

    private List<BankResultItem> bankResultItems = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran2);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        bankAdapterSewa = new BankAdapterSewa(this, bankResultItems);
        rvbank.setLayoutManager(linearLayoutManager);
        rvbank.setItemAnimator(new DefaultItemAnimator());

//        Toast.makeText(this, batas_bayar+" "+kdbayar+" "+total, Toast.LENGTH_SHORT).show();

        rvbank.setAdapter(bankAdapterSewa);
        saldo=getIntent().getStringExtra("saldo");

        showbank();






    }

    private void showbank() {
      ProgressDialog  progress = new ProgressDialog(PembayaranSewaActivity.this);
        progress.setCancelable(true);
        progress.setMessage("Sedang Proses");
        progress.show();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService=retrofit.create(APIService.class);
        Call<BankResponse> call=apiService.list_bank();
        call.enqueue(new Callback<BankResponse>() {
            @Override
            public void onResponse(Call<BankResponse> call, Response<BankResponse> response) {
                int Response_Item = response.body().getValue();
                rvbank.setVisibility(View.VISIBLE);
                if (Response_Item==1){
                    progress.dismiss();
                    bankResultItems = response.body().getResult();
                    bankAdapterSewa = new BankAdapterSewa(PembayaranSewaActivity.this, bankResultItems);

                    rvbank.setAdapter(bankAdapterSewa);
                }
                else{
                    progress.dismiss();
                    Toast.makeText(PembayaranSewaActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BankResponse> call, Throwable t) {
                progress.dismiss();

                Toast.makeText(PembayaranSewaActivity.this, "Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }

}

package com.softwarepintar.almuna_trans.ui.sewabus.paketsewa;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.ui.sewabus.KelasResultItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaketSewaActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {

    @BindView(R.id.tv_jmlbus)
    TextView tvJmlbus;
    @BindView(R.id.rvpaket)
    RecyclerView rvpaket;
   ProgressDialog progress;
    PaketSewaAdapter paketSewaAdapter;
    private List<PaketSewaResultItem>paketSewaResultItems=new ArrayList<>();
    KelasResultItem kelasResultItem;
    public static String jml_bus;
    public static String kapasitas;
    public static String kelas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket_sewa);
        ButterKnife.bind(this);

        kelasResultItem= getIntent().getParcelableExtra(Utils.EXTRA_NEWS);
        kapasitas=kelasResultItem.getKapasitas();
        kelas=kelasResultItem.getNama();
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvpaket.setLayoutManager(linearLayoutManager);
        rvpaket.setItemAnimator(new DefaultItemAnimator());

        loadpaket();

    }

    private void loadpaket() {
//        Toast.makeText(this, kelasResultItem.getId(), Toast.LENGTH_SHORT).show();
        progress = new ProgressDialog(this);
        progress.setCancelable(true);
        progress.setMessage("Sedang Proses");
        progress.show();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService=retrofit.create(APIService.class);
        Call<PaketSewaResponse> paketSewaResponseCall=apiService.list_paket();
        paketSewaResponseCall.enqueue(new Callback<PaketSewaResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<PaketSewaResponse> call, @NonNull Response<PaketSewaResponse> response) {
                int Value= Objects.requireNonNull(response.body()).getValue();
                progress.dismiss();
                rvpaket.setVisibility(View.VISIBLE);
                if (Value==1){
                    paketSewaResultItems=response.body().getResult();
                    paketSewaAdapter=new PaketSewaAdapter(getBaseContext(),paketSewaResultItems);
                    rvpaket.setAdapter(paketSewaAdapter);
//                    tvJmlbus.setText("Sisa Bus "+response.body().getJumlah());
                    tvJmlbus.setText(" ");
                }
                else {
                    Toast.makeText(PaketSewaActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PaketSewaResponse> call, @NonNull Throwable t) {
                Toast.makeText(PaketSewaActivity.this, "Failure", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Mau Kemana ?");
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        progress = new ProgressDialog(this);
        progress.setCancelable(true);
        progress.setMessage("Sedang Proses");
        progress.show();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService=retrofit.create(APIService.class);
        Call<PaketSewaResponse> paketSewaResponseCall=apiService.list_sewa_paket_cari(newText);
        paketSewaResponseCall.enqueue(new Callback<PaketSewaResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<PaketSewaResponse> call, @NonNull Response<PaketSewaResponse> response) {
                int Value= Objects.requireNonNull(response.body()).getValue();
                progress.dismiss();
                rvpaket.setVisibility(View.VISIBLE);
                if (Value==1){
                    paketSewaResultItems=response.body().getResult();
                    paketSewaAdapter=new PaketSewaAdapter(getBaseContext(),paketSewaResultItems);
                    rvpaket.setAdapter(paketSewaAdapter);
                    jml_bus=response.body().getJumlah();
                    tvJmlbus.setText("Sisa Bus "+jml_bus);
                }
                else {
                    Toast.makeText(PaketSewaActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PaketSewaResponse> call, @NonNull Throwable t) {
                Toast.makeText(PaketSewaActivity.this, "Failure", Toast.LENGTH_SHORT).show();

            }
        });


        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

package com.softwarepintar.almuna_trans.ui.TiketSaya.tiketaktif;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.ui.TiketSaya.PenumpangAdapter;
import com.softwarepintar.almuna_trans.ui.TiketSaya.PenumpangResponse;
import com.softwarepintar.almuna_trans.ui.TiketSaya.PenumpangResultItem;
import com.softwarepintar.almuna_trans.ui.TiketSaya.TiketResultItem;
import com.softwarepintar.almuna_trans.ui.TiketSaya.lihattiket.LihatTiketActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailTiketActivity extends AppCompatActivity {
    @BindView(R.id.tvkd)
    TextView tvkd;
    @BindView(R.id.tvkode)
    TextView tvkode;
    @BindView(R.id.imgqr)
    ImageView imgqr;
    @BindView(R.id.garis)
    View garis;
    @BindView(R.id.petunjuk)
    TextView petunjuk;
    @BindView(R.id.header)
    FrameLayout header;
    @BindView(R.id.tv_trayek)
    TextView tvTrayek;
    @BindView(R.id.tv_armada)
    TextView tvArmada;
    @BindView(R.id.garis2)
    View garis2;
    @BindView(R.id.tv_berangkat)
    TextView tvBerangkat;

    @BindView(R.id.tv_estimasi)
    TextView tvEstimasi;
    @BindView(R.id.tv_kelas)
    TextView tvKelas;
    @BindView(R.id.tv_harga)
    TextView tvHarga;
    @BindView(R.id.cardview)
    CardView cardview;
    @BindView(R.id.detail)
    TextView detail;
    @BindView(R.id.tv_nmarmada)
    TextView tvNmarmada;
    @BindView(R.id.tv_harga2)
    TextView tvHarga2;
    @BindView(R.id.garis3)
    View garis3;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.cardview3)
    CardView cardview3;
    @BindView(R.id.tvdetail)
    TextView tvdetail;
    @BindView(R.id.rvpenumpang)
    RecyclerView rvpenumpang;
    @BindView(R.id.btntiket)
    Button btntiket;
    private PenumpangAdapter penumpangAdapter;

    private List<PenumpangResultItem> penumpangResultItemList = new ArrayList<>();
    TiketResultItem tiketResultItem;
    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tiket);
        ButterKnife.bind(this);

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        penumpangAdapter = new PenumpangAdapter(this, penumpangResultItemList);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvpenumpang.setLayoutManager(linearLayoutManager);
        rvpenumpang.setItemAnimator(new DefaultItemAnimator());

        tiketResultItem = getIntent().getParcelableExtra(Utils.EXTRA_NEWS);
        tvkode.setText(tiketResultItem.getKodeTiket());
        tvHarga.setText(formatRupiah.format(Double.parseDouble(String.valueOf(tiketResultItem.getNominal()))));

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(tiketResultItem.getKodeTiket(), BarcodeFormat.CODE_128, 400, 250);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(bitMatrix);
            imgqr.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
        tvTrayek.setText(tiketResultItem.getRuteAsal() + " - " + tiketResultItem.getRuteTujuan());
        tvArmada.setText(tiketResultItem.getArmada_nama());
        tvBerangkat.setText(tiketResultItem.getBerangkat());

        tvKelas.setText(tiketResultItem.getKelas());
        tvNmarmada.setText(tiketResultItem.getArmada_nama());
        int nominal = Integer.parseInt(tiketResultItem.getNominal());
        int jumlah = Integer.parseInt(tiketResultItem.getJumlah());
        int harga = nominal / jumlah;
        tvHarga2.setText(formatRupiah.format(harga) + " x " + tiketResultItem.getJumlah());

        tvTotal.setText(formatRupiah.format(nominal));
        tvEstimasi.setText(tiketResultItem.getEstimasi() + " Jam");


//        Toast.makeText(this, batas_bayar+" "+kdbayar+" "+total, Toast.LENGTH_SHORT).show();

        rvpenumpang.setAdapter(penumpangAdapter);

        showpenumpang();
    }

    private void showpenumpang() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<PenumpangResponse> call = apiService.penumpang(tiketResultItem.getKodeTiket());
        call.enqueue(new Callback<PenumpangResponse>() {
            @Override
            public void onResponse(Call<PenumpangResponse> call, Response<PenumpangResponse> response) {
                int Response_Item = response.body().getValue();
                rvpenumpang.setVisibility(View.VISIBLE);
                if (Response_Item == 1) {

                    penumpangResultItemList = response.body().getResult();
                    penumpangAdapter = new PenumpangAdapter(DetailTiketActivity.this, penumpangResultItemList);

                    rvpenumpang.setAdapter(penumpangAdapter);
                } else {

                    Toast.makeText(DetailTiketActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PenumpangResponse> call, Throwable t) {

                Toast.makeText(DetailTiketActivity.this, "Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @OnClick(R.id.btntiket)
    public void onViewClicked() {
        Intent intent=new Intent(DetailTiketActivity.this, LihatTiketActivity.class);
        intent.putExtra("kode",tiketResultItem.getKodeTiket());
        startActivity(intent);
    }
}

package com.softwarepintar.almuna_trans.ui.TiketSaya.sewa;

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
import com.softwarepintar.almuna_trans.ui.sewabus.pembayaran.PembayaranSewaActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.softwarepintar.almuna_trans.API.Utils.batas_bayar;
import static com.softwarepintar.almuna_trans.API.Utils.bayardp;
import static com.softwarepintar.almuna_trans.API.Utils.kurang;
import static com.softwarepintar.almuna_trans.API.Utils.statussewa;
import static com.softwarepintar.almuna_trans.API.Utils.total;
import static com.softwarepintar.almuna_trans.ui.sewabus.FormSewaActivity.totharga;

public class DetaillSewaActivity extends AppCompatActivity {

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
    @BindView(R.id.cardview)
    CardView cardview;
    @BindView(R.id.detail)
    TextView detail;
    @BindView(R.id.tv_nmarmada)
    TextView tvNmarmada;

    @BindView(R.id.cardview3)
    CardView cardview3;
    @BindView(R.id.tvdetail)
    TextView tvdetail;
    @BindView(R.id.rvpenumpang)
    RecyclerView rvpenumpang;
    @BindView(R.id.btntiket)
    Button btntiket;

    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
    SewaResultItem sewaResultItem;
    @BindView(R.id.tv_harga2)
    TextView tvHarga2;
    @BindView(R.id.tv_statusbayar)
    TextView tvStatusbayar;

    private PelunasanAdapter pelunasanAdapter;
    private List<PelunasanResultItem> pelunasanResultItems = new ArrayList<>();


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaill_sewa);
        ButterKnife.bind(this);
        sewaResultItem = getIntent().getParcelableExtra(Utils.EXTRA_NEWS);
        tvkode.setText(sewaResultItem.getKode());
        tvTrayek.setText(sewaResultItem.getTujuan());
        tvArmada.setText(sewaResultItem.getDurasi());
        tvBerangkat.setText("Berangkat : " + sewaResultItem.getBerangkat() + "\n"
                + "Kembali : " + sewaResultItem.getKembali());
        tvEstimasi.setText(sewaResultItem.getPenjemputan());
        tvNmarmada.setText(sewaResultItem.getNama_kelas());
        tvHarga2.setText(sewaResultItem.getJumlah_bus() + " unit");
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvpenumpang.setLayoutManager(linearLayoutManager);
        rvpenumpang.setItemAnimator(new DefaultItemAnimator());
        showpelunasan();
        tvStatusbayar.setText(getIntent().getStringExtra("status"));


        if (sewaResultItem.getStatus().equalsIgnoreCase("LUNAS")) {
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(sewaResultItem.getKode(), BarcodeFormat.CODE_128, 400, 250);
                BarcodeEncoder encoder = new BarcodeEncoder();
                Bitmap bitmap = encoder.createBitmap(bitMatrix);
                imgqr.setImageBitmap(bitmap);

            } catch (WriterException e) {
                e.printStackTrace();
            }


        }
        else if (sewaResultItem.getStatus().equalsIgnoreCase("DP") || sewaResultItem.getStatus().equalsIgnoreCase("BELUM")){
            btntiket.setText("Pelunasan");
            btntiket.setBackgroundColor(R.color.red);
            petunjuk.setText("Segera lakukan pelunasan sebelum tanggal "+sewaResultItem.getBatasBayar());

        }


    }

    private void showpelunasan() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<PelunasanResponse> call = apiService.pelunasan(sewaResultItem.getKode());
        call.enqueue(new Callback<PelunasanResponse>() {
            @Override
            public void onResponse(Call<PelunasanResponse> call, Response<PelunasanResponse> response) {
                int Response_Item = response.body().getValue();
                rvpenumpang.setVisibility(View.VISIBLE);
                if (Response_Item == 1) {

                    pelunasanResultItems = response.body().getResult();
                    pelunasanAdapter = new PelunasanAdapter(DetaillSewaActivity.this, pelunasanResultItems);

                    rvpenumpang.setAdapter(pelunasanAdapter);
                } else {

                    Toast.makeText(DetaillSewaActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PelunasanResponse> call, Throwable t) {

                Toast.makeText(DetaillSewaActivity.this, "Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @OnClick(R.id.btntiket)
    public void onViewClicked() {

        switch (sewaResultItem.getStatus()){
            case "LUNAS":

                Intent intent = new Intent(DetaillSewaActivity.this, TiketSewaActivity.class);
                intent.putExtra("kode", sewaResultItem.getKode());
                intent.putExtra("berangkat", sewaResultItem.getBerangkat());
                intent.putExtra("tujuan", sewaResultItem.getTujuan());
                intent.putExtra("kelas", sewaResultItem.getNama_kelas());
                startActivity(intent);
                break;
            case "PENDING":
                Toast.makeText(this, "Mohon maaf pembayaran anda sedang proses verifikasi harap tunggu beberapa saat lagi", Toast.LENGTH_SHORT).show();

                break;
            case "DP":
            case "BELUM":
                intent = new Intent(DetaillSewaActivity.this, PembayaranSewaActivity.class);
                totharga= Integer.parseInt(sewaResultItem.getTotal());
                int npersen=totharga/100;
                int npotongan=70*npersen;
                long persen=70*totharga;

                Utils.bayardp=totharga-npotongan;
                Utils.kdbayar= sewaResultItem.getKode();
                statussewa= sewaResultItem.getStatus();
                kurang=totharga-bayardp;
                total= String.valueOf(totharga);
                Utils.jumlah=sewaResultItem.getJumlah_bus();
                batas_bayar=sewaResultItem.getBatasBayar();
//                Toast.makeText(this, bayardp+" ", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;


        }
    }
}

package com.softwarepintar.almuna_trans.DetailBus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.Booking.Booking2Activity;
import com.softwarepintar.almuna_trans.Booking.Booking3Activity;
import com.softwarepintar.almuna_trans.Booking.Booking4Activity;
import com.softwarepintar.almuna_trans.Booking.BookingSatuActivity;
import com.softwarepintar.almuna_trans.Fasilitas.FasilitasAdapter;
import com.softwarepintar.almuna_trans.Fasilitas.FasilitasResponse;
import com.softwarepintar.almuna_trans.Fasilitas.FasilitasResultItem;
import com.softwarepintar.almuna_trans.Jadwal.JadwalFragment;
import com.softwarepintar.almuna_trans.Jadwal.Jadwal_ResultItem;
import com.softwarepintar.almuna_trans.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailBusActivity extends AppCompatActivity {

    @BindView(R.id.spek)
    TextView spek;
    @BindView(R.id.relative)
    RelativeLayout relative;
    @BindView(R.id.tv_kursi)
    TextView tvKursi;
    @BindView(R.id.tv_jmlkursi)
    TextView tvJmlkursi;
    @BindView(R.id.tv_jmlkursi_kembali)
    TextView tv_jmlkursi_kembali;

    @BindView(R.id.tv_kapasitas)
    TextView tvKapasitas;
    @BindView(R.id.tv_jmlkapasitas)
    TextView tvJmlkapasitas;
    @BindView(R.id.tv_jmlkapasitas_kembali)
    TextView tv_jmlkapasitas_kembali;
    @BindView(R.id.card1)
    CardView card1;
    @BindView(R.id.rv_fasilitas)
    RecyclerView rvFasilitas;
    @BindView(R.id.rv_fasilitas_kembali)
    RecyclerView rvFasilitas_kembali;
    @BindView(R.id.rv_fasilitas_jalan)
    RecyclerView rvFasilitas_jalan;
    @BindView(R.id.rv_fasilitas_jalan_kembali)
    RecyclerView rvFasilitas_jalan_kembali;
    @BindView(R.id.card2)
    CardView card2;
    @BindView(R.id.tv_trayek)
    TextView tvTrayek;
    @BindView(R.id.tv_trayek_kembali)
    TextView tv_trayek_kembali;
    @BindView(R.id.tv_berangkat)
    TextView tvBerangkat;
    @BindView(R.id.tv_berangkat_kembali)
    TextView tv_berangkat_kembali;
    @BindView(R.id.card3)
    CardView card3;
    @BindView(R.id.card4)
    CardView card4;
    @BindView(R.id.btnpesan)
    Button btnpesan;
    Jadwal_ResultItem jadwal_resultItem;
    @BindView(R.id.tv_bus)
    TextView tv_bus;
    @BindView(R.id.tv_bus_kembali)
    TextView tv_bus_kembali;

    Intent intent;
    private FasilitasAdapter fasilitasAdapter;
    private List<FasilitasResultItem>fasilitasResultItems=new ArrayList<>();

    String kapasitas,armada,armada_id,waktu,estimasi,asal,tujuan,jadwal_id,jam_berangkat,tanggal,
            kelas,harga,tanggal_view,jumlah,agen_asal,agen_tujuan,bayi,kelas_nama;
    String kapasitas_kembali,armada_kembali,armada_id_kembali,waktu_kembali,estimasi_kembali,asal_kembali,
            tujuan_kembali,jadwal_id_kembali,jam_berangkat_kembali,tanggal_kembali,
            kelas_kembali,harga_kembali,tanggal_view_kembali;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bus);
        ButterKnife.bind(this);
        Locale localeID = new Locale("in", "ID");

        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        if (getIntent().getStringExtra("is_pulangpergi").equalsIgnoreCase("y")){
            jadwal_resultItem = getIntent().getParcelableExtra(Utils.EXTRA_NEWS);
            relative.setVisibility(View.VISIBLE);
            kapasitas_kembali=jadwal_resultItem.getKapasitas();
            armada_kembali=jadwal_resultItem.getArmadaNama();
            waktu_kembali=jadwal_resultItem.getMulaiWaktu();
            estimasi_kembali=jadwal_resultItem.getEstimasi();
            asal_kembali=jadwal_resultItem.getMulai();
            tujuan_kembali=jadwal_resultItem.getAkhir();
            jadwal_id_kembali=jadwal_resultItem.getJadwalId();
            jam_berangkat_kembali=jadwal_resultItem.getMulaiWaktu();
            tanggal_kembali=jadwal_resultItem.getJadwalTanggal();
            kelas_kembali=jadwal_resultItem.getKelas_nama();
            harga_kembali=jadwal_resultItem.getHarga();
            armada_id_kembali=jadwal_resultItem.getArmada_id();
            tanggal_view_kembali=getIntent().getStringExtra("tanggal_view_pergi");
            tanggal_kembali=getIntent().getStringExtra("tanggal_pergi");

            jadwal_id=getIntent().getStringExtra("jadwal_id");
            tanggal=getIntent().getStringExtra("tanggal");
            Toast.makeText(DetailBusActivity.this,tanggal,Toast.LENGTH_LONG).show();

            asal=getIntent().getStringExtra("asal");
            tujuan=getIntent().getStringExtra("tujuan");

            kapasitas=getIntent().getStringExtra("kapasitas");
            armada=getIntent().getStringExtra("armada");
            armada_id=getIntent().getStringExtra("armada_id");
            waktu=getIntent().getStringExtra("waktu");
            tanggal_view=getIntent().getStringExtra("tanggal_view");
            jumlah=getIntent().getStringExtra("penumpang");
            agen_asal=getIntent().getStringExtra("agen_asal");
            agen_tujuan=getIntent().getStringExtra("agen_tujuan");
            bayi=getIntent().getStringExtra("bayi");
            jam_berangkat=getIntent().getStringExtra("waktu_berangkat");
            harga=getIntent().getStringExtra("harga");
            kelas=getIntent().getStringExtra("kelas_id");
            kelas_nama=getIntent().getStringExtra("kelas_nama");
            estimasi=getIntent().getStringExtra("estimasi");
            tv_bus.setText(armada+" ( "+formatRupiah.format(Double.parseDouble(harga))+"/kursi )");
            tv_bus_kembali.setText(armada_kembali+" ( "+formatRupiah.format(Double.parseDouble(harga_kembali))+"/kursi )");

            tvJmlkursi.setText(kapasitas+" Kursi");
            tvJmlkapasitas.setText("2 - 2");
            tvBerangkat.setText(waktu+ " (Durasi : "+estimasi+" Jam )");

            tvTrayek.setText(asal+ " - " + tujuan);

            Toast.makeText(this,jadwal_id+jadwal_id_kembali,Toast.LENGTH_LONG).show();



            tv_jmlkursi_kembali.setText(jadwal_resultItem.getKapasitas()+" Kursi");
            tv_jmlkapasitas_kembali.setText("2 - 2");
            tv_berangkat_kembali.setText(jadwal_resultItem.getMulaiWaktu()+ " (Durasi : "+jadwal_resultItem.getEstimasi()+" Jam )");
//            tv_harga_kembali.setText(formatRupiah.format(Double.parseDouble(jadwal_resultItem.getHarga()))+"/kursi");
            tv_trayek_kembali.setText(jadwal_resultItem.getMulai() + " - " + jadwal_resultItem.getAkhir());

            Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
            Objects.requireNonNull(getSupportActionBar()).setTitle("Info Bus ");;

        }
        else{
            jadwal_resultItem = getIntent().getParcelableExtra(Utils.EXTRA_NEWS);

            kapasitas=jadwal_resultItem.getKapasitas();
            armada=jadwal_resultItem.getArmadaNama();
            waktu=jadwal_resultItem.getMulaiWaktu();
            estimasi=jadwal_resultItem.getEstimasi();
            asal=jadwal_resultItem.getMulai();
            tujuan=jadwal_resultItem.getAkhir();
            jadwal_id=jadwal_resultItem.getJadwalId();
            jam_berangkat=jadwal_resultItem.getMulaiWaktu();
            tanggal=jadwal_resultItem.getJadwalTanggal();
            kelas=jadwal_resultItem.getKelas_nama();
            harga=jadwal_resultItem.getHarga();
            tanggal_view=getIntent().getStringExtra("tanggal_view_pergi");
            tanggal=getIntent().getStringExtra("tanggal");
            armada_id=jadwal_resultItem.getArmada_id();

            tvJmlkursi.setText(kapasitas+" Kursi");
            tv_bus.setText(jadwal_resultItem.getArmadaNama()+" ( "+formatRupiah.format(Double.parseDouble(harga))+"/kursi )");
            tvJmlkapasitas.setText("2 - 2");
            tvBerangkat.setText(waktu+ " (Durasi : "+estimasi+" Jam )");
//            tvHarga.setText(formatRupiah.format(Double.parseDouble(harga))+"/kursi");
            tvTrayek.setText(asal + " - " + tujuan);

            Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
            Objects.requireNonNull(getSupportActionBar()).setTitle("Info Bus ");;


        }


        fasilitas();
        fasilitas_berangkat(jadwal_id);
        fasilitas_kembali(jadwal_id_kembali);
//        Toast.makeText(this, jadwal_resultItem.getKelas_id(), Toast.LENGTH_SHORT).show();
    }

    private void fasilitas_kembali(String jadwal_id){
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        rvFasilitas_jalan_kembali.setLayoutManager(layoutManager);
        rvFasilitas_jalan_kembali.setHasFixedSize(true);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService=retrofit.create(APIService.class);
        Call<FasilitasResponse>fasilitasResponseCall=apiService.fasiltas_jalan(jadwal_id);
        fasilitasResponseCall.enqueue(new Callback<FasilitasResponse>() {
            @Override
            public void onResponse(Call<FasilitasResponse> call, Response<FasilitasResponse> response) {
                int responseItem = response.body().getValue();
                if (responseItem==1){
                    fasilitasResultItems=response.body().getResult();
                    fasilitasAdapter=new FasilitasAdapter(getBaseContext(),fasilitasResultItems);
                    rvFasilitas_jalan_kembali.setAdapter(fasilitasAdapter);



                }
                else{
                    Toast.makeText(DetailBusActivity.this, "gagal", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<FasilitasResponse> call, Throwable t) {
                Toast.makeText(DetailBusActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void fasilitas_berangkat(String jadwal_id){
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        rvFasilitas_jalan.setLayoutManager(layoutManager);
        rvFasilitas_jalan.setHasFixedSize(true);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService=retrofit.create(APIService.class);
        Call<FasilitasResponse>fasilitasResponseCall=apiService.fasiltas_jalan(jadwal_id);
        fasilitasResponseCall.enqueue(new Callback<FasilitasResponse>() {
            @Override
            public void onResponse(Call<FasilitasResponse> call, Response<FasilitasResponse> response) {
                int responseItem = response.body().getValue();
                if (responseItem==1){
                    fasilitasResultItems=response.body().getResult();
                    fasilitasAdapter=new FasilitasAdapter(getBaseContext(),fasilitasResultItems);
                    rvFasilitas_jalan.setAdapter(fasilitasAdapter);



                }
                else{
                    Toast.makeText(DetailBusActivity.this, "gagal", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<FasilitasResponse> call, Throwable t) {
                Toast.makeText(DetailBusActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void fasilitas() {

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        rvFasilitas.setLayoutManager(layoutManager);
        rvFasilitas.setHasFixedSize(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService api = retrofit.create(APIService.class);
        Call <FasilitasResponse> call=api.fasiltas(jadwal_id);
        call.enqueue(new Callback<FasilitasResponse>() {
            @Override
            public void onResponse(Call<FasilitasResponse> call, Response<FasilitasResponse> response) {
                int Response_Item = response.body().getValue();

                if (Response_Item==1){
                    fasilitasResultItems=response.body().getResult();
                    fasilitasAdapter=new FasilitasAdapter(getBaseContext(),fasilitasResultItems);
                    rvFasilitas.setAdapter(fasilitasAdapter);
                    rvFasilitas_kembali.setAdapter(fasilitasAdapter);
                }
            }

            @Override
            public void onFailure(Call<FasilitasResponse> call, Throwable t) {
                Toast.makeText(DetailBusActivity.this, "Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @OnClick(R.id.btnpesan)
    public void onViewClicked() {
        if (JadwalFragment.jml_penumpang.equals("1 Dewasa")){
            intent = new Intent(DetailBusActivity.this,BookingSatuActivity.class);
            intent.putExtra("jml_penumpang",JadwalFragment.jml_penumpang);
            intent.putExtra("jumlah","1");
            intent.putExtra("tanggal",tanggal);
            intent.putExtra("asal",asal);
            intent.putExtra("tujuan",tujuan);
            intent.putExtra("profil_id", Utils.user_id);
            intent.putExtra("jadwal_id",jadwal_id);
            intent.putExtra("jam_berangkat",jam_berangkat);
            intent.putExtra("armada_nama",armada);
            intent.putExtra("estimasi",estimasi);
            intent.putExtra("kelas",kelas_nama);
            intent.putExtra("harga",harga);
            intent.putExtra("tanggal_view",tanggal_view);
            intent.putExtra("armada_id",armada_id);
            intent.putExtra("is_pulangpergi",getIntent().getStringExtra("is_pulangpergi"));
            intent.putExtra("tanggal_kembali",tanggal_kembali);
            intent.putExtra("asal_kembali",asal_kembali);
            intent.putExtra("tujuan_kembali",tujuan_kembali);
            intent.putExtra("jadwal_id_kembali",jadwal_id_kembali);
            intent.putExtra("jam_berangkat_kembali",jam_berangkat_kembali);
            intent.putExtra("armada_nama_kembali",armada_kembali);
            intent.putExtra("estimasi_kembali",estimasi_kembali);
            intent.putExtra("kelas_kembali",kelas_kembali);
            intent.putExtra("harga_kembali",harga_kembali);
            intent.putExtra("tanggal_view_kembali",tanggal_view_kembali);
            intent.putExtra("armada_id_kembali",armada_id_kembali);
            intent.putExtra("agen_asal",JadwalFragment.agen_asal);
            intent.putExtra("agen_tujuan",JadwalFragment.agen_tujuan);



            startActivity(intent);
        }
        else if  (JadwalFragment.jml_penumpang.equals("2 Dewasa")){
            intent = new Intent(DetailBusActivity.this, Booking2Activity.class);
            intent.putExtra("jml_penumpang",JadwalFragment.jml_penumpang);
            intent.putExtra("jumlah","2");
            intent.putExtra("tanggal",tanggal);
            intent.putExtra("asal",asal);
            intent.putExtra("tujuan",tujuan);
            intent.putExtra("profil_id", Utils.user_id);
            intent.putExtra("jadwal_id",jadwal_id);
            intent.putExtra("jam_berangkat",jam_berangkat);
            intent.putExtra("armada_nama",armada);
            intent.putExtra("estimasi",estimasi);
            intent.putExtra("kelas",kelas_nama);
            intent.putExtra("harga",harga);
            intent.putExtra("tanggal_view",tanggal_view);
            intent.putExtra("armada_id",armada_id);
            intent.putExtra("is_pulangpergi",getIntent().getStringExtra("is_pulangpergi"));
            intent.putExtra("tanggal_kembali",tanggal_kembali);
            intent.putExtra("asal_kembali",asal_kembali);
            intent.putExtra("tujuan_kembali",tujuan_kembali);
            intent.putExtra("jadwal_id_kembali",jadwal_id_kembali);
            intent.putExtra("jam_berangkat_kembali",jam_berangkat_kembali);
            intent.putExtra("armada_nama_kembali",armada_kembali);
            intent.putExtra("estimasi_kembali",estimasi_kembali);
            intent.putExtra("kelas_kembali",kelas_kembali);
            intent.putExtra("harga_kembali",harga_kembali);
            intent.putExtra("tanggal_view_kembali",tanggal_view_kembali);
            intent.putExtra("armada_id_kembali",armada_id_kembali);
            intent.putExtra("agen_asal",JadwalFragment.agen_asal);
            intent.putExtra("agen_tujuan",JadwalFragment.agen_tujuan);


            startActivity(intent);

        }
        else if  (JadwalFragment.jml_penumpang.equals("3 Dewasa")){
            intent = new Intent(DetailBusActivity.this, Booking3Activity.class);
            intent.putExtra("jml_penumpang",JadwalFragment.jml_penumpang);
            intent.putExtra("jumlah","3");
            intent.putExtra("tanggal",tanggal);
            intent.putExtra("asal",asal);
            intent.putExtra("tujuan",tujuan);
            intent.putExtra("profil_id", Utils.user_id);
            intent.putExtra("jadwal_id",jadwal_id);
            intent.putExtra("jam_berangkat",jam_berangkat);
            intent.putExtra("armada_nama",armada);
            intent.putExtra("estimasi",estimasi);
            intent.putExtra("kelas",kelas_nama);
            intent.putExtra("harga",harga);
            intent.putExtra("tanggal_view",tanggal_view);
            intent.putExtra("armada_id",armada_id);
            intent.putExtra("is_pulangpergi",getIntent().getStringExtra("is_pulangpergi"));
            intent.putExtra("tanggal_kembali",tanggal_kembali);
            intent.putExtra("asal_kembali",asal_kembali);
            intent.putExtra("tujuan_kembali",tujuan_kembali);
            intent.putExtra("jadwal_id_kembali",jadwal_id_kembali);
            intent.putExtra("jam_berangkat_kembali",jam_berangkat_kembali);
            intent.putExtra("armada_nama_kembali",armada_kembali);
            intent.putExtra("estimasi_kembali",estimasi_kembali);
            intent.putExtra("kelas_kembali",kelas_kembali);
            intent.putExtra("harga_kembali",harga_kembali);
            intent.putExtra("tanggal_view_kembali",tanggal_view_kembali);
            intent.putExtra("armada_id_kembali",armada_id_kembali);
            intent.putExtra("agen_asal",JadwalFragment.agen_asal);
            intent.putExtra("agen_tujuan",JadwalFragment.agen_tujuan);



            startActivity(intent);

        }
        else if  (JadwalFragment.jml_penumpang.equals("4 Dewasa")){
            intent = new Intent(DetailBusActivity.this, Booking4Activity.class);
            intent.putExtra("jml_penumpang",JadwalFragment.jml_penumpang);
            intent.putExtra("jumlah","4");
            intent.putExtra("tanggal",tanggal);
            intent.putExtra("asal",asal);
            intent.putExtra("tujuan",tujuan);
            intent.putExtra("profil_id", Utils.user_id);
            intent.putExtra("jadwal_id",jadwal_id);
            intent.putExtra("jam_berangkat",jam_berangkat);
            intent.putExtra("armada_nama",armada);
            intent.putExtra("estimasi",estimasi);
            intent.putExtra("kelas",kelas_nama);
            intent.putExtra("harga",harga);
            intent.putExtra("tanggal_view",tanggal_view);
            intent.putExtra("armada_id",armada_id);
            intent.putExtra("is_pulangpergi",getIntent().getStringExtra("is_pulangpergi"));
            intent.putExtra("tanggal_kembali",tanggal_kembali);
            intent.putExtra("asal_kembali",asal_kembali);
            intent.putExtra("tujuan_kembali",tujuan_kembali);
            intent.putExtra("jadwal_id_kembali",jadwal_id_kembali);
            intent.putExtra("jam_berangkat_kembali",jam_berangkat_kembali);
            intent.putExtra("armada_nama_kembali",armada_kembali);
            intent.putExtra("estimasi_kembali",estimasi_kembali);
            intent.putExtra("kelas_kembali",kelas_kembali);
            intent.putExtra("harga_kembali",harga_kembali);
            intent.putExtra("tanggal_view_kembali",tanggal_view_kembali);
            intent.putExtra("armada_id_kembali",armada_id_kembali);
            intent.putExtra("agen_asal",JadwalFragment.agen_asal);
            intent.putExtra("agen_tujuan",JadwalFragment.agen_tujuan);



            startActivity(intent);

        }
        else{
            Toast.makeText(this, "Pilih Penumpang", Toast.LENGTH_SHORT).show();
        }

    }
}

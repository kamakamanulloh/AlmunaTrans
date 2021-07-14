package com.softwarepintar.almuna_trans.seats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.Booking.BookingResponse;
import com.softwarepintar.almuna_trans.MainActivity;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.payment.PembayaranActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;

import static com.softwarepintar.almuna_trans.API.Utils.batas_bayar;
import static com.softwarepintar.almuna_trans.API.Utils.harga;
import static com.softwarepintar.almuna_trans.API.Utils.kdbayar;
import static com.softwarepintar.almuna_trans.API.Utils.tempString;
import static com.softwarepintar.almuna_trans.API.Utils.tempString2;
import static com.softwarepintar.almuna_trans.API.Utils.total;

public class PilihKursiKembaliActivity extends AppCompatActivity {

    @BindView(R.id.rv_a)
    RecyclerView rv_a;

    @BindView(R.id.rv_b)
    RecyclerView rv_b;

    @BindView(R.id.rv_c)
    RecyclerView rv_c;

    @BindView(R.id.rv_d)
    RecyclerView rv_d;

    @BindView(R.id.tv_waktu)
    TextView tv_waktu;

    @BindView(R.id.btnlanjut)
    Button btnlanjut;

    private SeatsAdapterKembali seatsAdapter;
    private SeatAdapterBKembali seatAdapterB;
    private SeatAdapterCKembali seatAdapterC;
    private SeatAdapterDKembali seatAdapterD;

    private List<ResultAItem> resultAItemsList=new ArrayList<>();
    private List<ResultBItem> resultBItemList=new ArrayList<>();
    private List<ResultCItem> resultCItemList=new ArrayList<>();
    private List<ResultDItem> resultDItemList=new ArrayList<>();
    private TimerClass timerClass;

    public static String jumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_kursi_kembali);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager linearLayoutManagerb = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager linearLayoutManagerc = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager linearLayoutManagerd = new LinearLayoutManager(getApplicationContext());
        seatsAdapter=new SeatsAdapterKembali(this,resultAItemsList);
        seatAdapterB=new SeatAdapterBKembali(this,resultBItemList);
        seatAdapterC=new SeatAdapterCKembali(this,resultCItemList);
        seatAdapterD=new SeatAdapterDKembali(this,resultDItemList);

        rv_a.setAdapter(seatsAdapter);
        rv_b.setAdapter(seatAdapterB);
        rv_c.setAdapter(seatAdapterC);
        rv_d.setAdapter(seatAdapterD);

        rv_a.setLayoutManager(linearLayoutManager);
        rv_a.setItemAnimator(new DefaultItemAnimator());

        rv_b.setLayoutManager(linearLayoutManagerb);
        rv_b.setItemAnimator(new DefaultItemAnimator());

        rv_c.setLayoutManager(linearLayoutManagerc);
        rv_c.setItemAnimator(new DefaultItemAnimator());

        rv_d.setLayoutManager(linearLayoutManagerd);
        rv_d.setItemAnimator(new DefaultItemAnimator());

        list();
        Toast.makeText(getApplicationContext(),
                Utils.user_id,
                Toast.LENGTH_LONG).show();

        timerClass=new TimerClass(300000,1000);
        timerClass.start();
        jumlah=getIntent().getStringExtra("qty");
        btnlanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerClass.cancel();
               updateKursi();
            }
        });



    }
    class TimerClass extends CountDownTimer {

        TimerClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //Method ini berjalan saat waktu/timer berubah
        @Override
        public void onTick(long millisUntilFinished) {
            //Kenfigurasi Format Waktu yang digunakan
            @SuppressLint("DefaultLocale") String waktu = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

            //Menampilkannya pada TexView
            tv_waktu.setText("Waktu " +waktu);
        }

        @Override
        public void onFinish() {
            ///Berjalan saat waktu telah selesai atau berhenti
            Toast.makeText(getApplicationContext(),
                    "Waktu Telah Berakhir",
                    Toast.LENGTH_LONG).show();
            Intent intent=new Intent(new Intent(PilihKursiKembaliActivity.this,MainActivity.class));
            intent.putExtra(Utils.TAG_user_id,getIntent().getStringExtra("user_id"));
            finish();
            startActivity(intent);
        }
    }

    private void updateKursi(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<BookingResponse>call=apiService.add_kursi( getIntent().getStringExtra("qty"),
                getIntent().getStringExtra("kode"),String.valueOf(Utils.seat_selected),
                String.valueOf(Utils.seat_selected2),getIntent().getStringExtra("asal"),
                getIntent().getStringExtra("tujuan"),"y");
        call.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                int value = response.body().getValue();

                if (value == 1) {
                    Toast.makeText(PilihKursiKembaliActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PilihKursiKembaliActivity.this, PembayaranActivity.class);
                    batas_bayar = response.body().getBatas_bayar();
                    String detail=getIntent().getStringExtra("armada_nama")+
                            " Tujuan "+ getIntent().getStringExtra("asal")+" - "+ getIntent().getStringExtra("tujuan");
                    total = getIntent().getStringExtra("harga");
                    kdbayar = getIntent().getStringExtra("kode");
                    harga = getIntent().getStringExtra("harga");
                    intent.putExtra("saldo", getIntent().getStringExtra("saldo"));
                    intent.putExtra("qty",getIntent().getStringExtra("qty"));
                    intent.putExtra("name",detail);
                    intent.putExtra(Utils.TAG_user_id,getIntent().getStringExtra("user_id"));
                    startActivity(intent);

                }
                else{
                    Toast.makeText(PilihKursiKembaliActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                Toast.makeText(PilihKursiKembaliActivity.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }


    private void list() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService=retrofit.create(APIService.class);
        Call<SeatsResponse> seatsResponseCall=apiService.list_kursi(getIntent().getStringExtra("kembali"),
                getIntent().getStringExtra("armada_id_kembali"),getIntent().getStringExtra("asal"),
                getIntent().getStringExtra("tujuan"));
        Toast.makeText(PilihKursiKembaliActivity.this,  getIntent().getStringExtra("armada_id_kembali")+ getIntent().getStringExtra("asal")+ getIntent().getStringExtra("tujuan")+ getIntent().getStringExtra("kembali"), Toast.LENGTH_SHORT).show();
        seatsResponseCall.enqueue(new Callback<SeatsResponse>() {
            @Override
            public void onResponse(Call<SeatsResponse> call, Response<SeatsResponse> response) {
                int value = response.body().getValue();
                if (value==1){

                    resultAItemsList=response.body().getResultA();
                    resultBItemList=response.body().getResultB();
                    resultCItemList=response.body().getResultC();
                    resultDItemList=response.body().getResultD();

                    seatsAdapter=new SeatsAdapterKembali(PilihKursiKembaliActivity.this,resultAItemsList);
                    seatAdapterB=new SeatAdapterBKembali(PilihKursiKembaliActivity.this,resultBItemList);
                    seatAdapterC=new SeatAdapterCKembali(PilihKursiKembaliActivity.this,resultCItemList);
                    seatAdapterD=new SeatAdapterDKembali(PilihKursiKembaliActivity.this,resultDItemList);

                    rv_a.setAdapter(seatsAdapter);
                    rv_b.setAdapter(seatAdapterB);
                    rv_c.setAdapter(seatAdapterC);
                    rv_d.setAdapter(seatAdapterD);
                }
                else{
                    Toast.makeText(PilihKursiKembaliActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SeatsResponse> call, Throwable t) {
                Toast.makeText(PilihKursiKembaliActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
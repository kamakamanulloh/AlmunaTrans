package com.softwarepintar.almuna_trans.seats;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.Booking.Booking2Activity;
import com.softwarepintar.almuna_trans.Booking.BookingResponse;
import com.softwarepintar.almuna_trans.Booking.BookingSatuActivity;
import com.softwarepintar.almuna_trans.MainActivity;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.payment.PembayaranActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.softwarepintar.almuna_trans.API.Utils.batas_bayar;
import static com.softwarepintar.almuna_trans.API.Utils.harga;
import static com.softwarepintar.almuna_trans.API.Utils.kdbayar;
import static com.softwarepintar.almuna_trans.API.Utils.total;

public class PilihKursiActivity extends AppCompatActivity {
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

    private SeatsAdapter seatsAdapter;
    private SeatAdapterB seatAdapterB;
    private SeatAdapterC seatAdapterC;
    private SeatAdapterD seatAdapterD;

    private List<ResultAItem> resultAItemsList=new ArrayList<>();
    private List<ResultBItem> resultBItemList=new ArrayList<>();
    private List<ResultCItem> resultCItemList=new ArrayList<>();
    private List<ResultDItem> resultDItemList=new ArrayList<>();
    private  TimerClass timerClass;

    public static String jumlah;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_kursi);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager linearLayoutManagerb = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager linearLayoutManagerc = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager linearLayoutManagerd = new LinearLayoutManager(getApplicationContext());
        seatsAdapter=new SeatsAdapter(this,resultAItemsList);
        seatAdapterB=new SeatAdapterB(this,resultBItemList);
        seatAdapterC=new SeatAdapterC(this,resultCItemList);
        seatAdapterD=new SeatAdapterD(this,resultDItemList);

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
        ///Berjalan saat waktu telah selesai atau berhenti


        timerClass=new TimerClass(300000,1000);
        timerClass.start();
        jumlah=getIntent().getStringExtra("qty");
        if (getIntent().getStringExtra("is_pulangpergi").equalsIgnoreCase("y")) {
            btnlanjut.setText("Pilih Kursi Kembali");
        }

       btnlanjut.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (getIntent().getStringExtra("is_pulangpergi").equalsIgnoreCase("y")){
                   timerClass.onFinish();
                   Intent intent=new Intent(PilihKursiActivity.this,PilihKursiKembaliActivity.class);
                   intent.putExtra("qty",getIntent().getStringExtra("qty"));
                   intent.putExtra("tempString",Utils.tempString);
                   intent.putExtra("kode",getIntent().getStringExtra("kode"));
                   intent.putExtra("harga",getIntent().getStringExtra("harga"));
                   intent.putExtra("saldo",getIntent().getStringExtra("saldo"));
                   intent.putExtra("qty",getIntent().getStringExtra("qty"));
                   intent.putExtra("armada_id",getIntent().getStringExtra("armada_id"));
                   intent.putExtra("armada_id_kembali",getIntent().getStringExtra("armada_id_kembali"));
                   intent.putExtra("armada_nama",getIntent().getStringExtra("armada_nama"));
                   intent.putExtra("armada_nama_kembali",getIntent().getStringExtra("armada_nama_kembali"));
                   intent.putExtra("kembali",getIntent().getStringExtra("kembali"));
                   intent.putExtra("asal",getIntent().getStringExtra("asal"));
                   intent.putExtra("tujuan",getIntent().getStringExtra("tujuan"));
                   intent.putExtra("is_pulangpergi",getIntent().getStringExtra("is_pulangpergi"));
                   intent.putExtra(Utils.TAG_user_id,getIntent().getStringExtra("profil_id"));
                   startActivity(intent);


               }
               else{
                   timerClass.cancel();
                   updateKursi();
               }
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
            String waktu = String.format("%02d:%02d:%02d",
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
            Intent intent=new Intent(new Intent(PilihKursiActivity.this,MainActivity.class));
            intent.putExtra(Utils.TAG_user_id,getIntent().getStringExtra("user_id"));
            startActivity(intent);
            finish();
        }
    }

    private void updateKursi(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<BookingResponse>call=apiService.add_kursi(getIntent().getStringExtra("qty"),
                getIntent().getStringExtra("kode"),String.valueOf(Utils.seat_selected),
                " ",getIntent().getStringExtra("asal"),
                getIntent().getStringExtra("tujuan"),"n");
        call.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                int value = response.body().getValue();

                if (value == 1) {
                    Toast.makeText(PilihKursiActivity.this,response.body().getMessage() , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PilihKursiActivity.this, PembayaranActivity.class);
                    batas_bayar = response.body().getBatas_bayar();
                    String detail=getIntent().getStringExtra("armada_nama")+
                            " Tujuan "+ getIntent().getStringExtra("asal")+" - "+ getIntent().getStringExtra("tujuan");
                    total = getIntent().getStringExtra("harga");
                    kdbayar =getIntent().getStringExtra("kode");
                    harga = getIntent().getStringExtra("harga");
                    intent.putExtra("saldo", getIntent().getStringExtra("saldo"));
                    intent.putExtra("qty",getIntent().getStringExtra("qty"));
                    intent.putExtra("name",detail);
                    intent.putExtra(Utils.TAG_user_id,getIntent().getStringExtra("user_id"));
                    startActivity(intent);

                }
                else{
                    Toast.makeText(PilihKursiActivity.this,"Gagal",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                Toast.makeText(PilihKursiActivity.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }


    private void list() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService=retrofit.create(APIService.class);
        Call<SeatsResponse>seatsResponseCall=apiService.list_kursi(getIntent().getStringExtra("berangkat"),
                getIntent().getStringExtra("armada_id"),getIntent().getStringExtra("asal"),
                getIntent().getStringExtra("tujuan"));
        Toast.makeText(PilihKursiActivity.this,  getIntent().getStringExtra("armada_id")+ getIntent().getStringExtra("asal")+ getIntent().getStringExtra("tujuan")+ getIntent().getStringExtra("berangkat"), Toast.LENGTH_SHORT).show();
        seatsResponseCall.enqueue(new Callback<SeatsResponse>() {
            @Override
            public void onResponse(Call<SeatsResponse> call, Response<SeatsResponse> response) {
                int value = response.body().getValue();
                if (value==1){

                    resultAItemsList=response.body().getResultA();
                    resultBItemList=response.body().getResultB();
                    resultCItemList=response.body().getResultC();
                    resultDItemList=response.body().getResultD();

                    seatsAdapter=new SeatsAdapter(PilihKursiActivity.this,resultAItemsList);
                    seatAdapterB=new SeatAdapterB(PilihKursiActivity.this,resultBItemList);
                    seatAdapterC=new SeatAdapterC(PilihKursiActivity.this,resultCItemList);
                    seatAdapterD=new SeatAdapterD(PilihKursiActivity.this,resultDItemList);

                    rv_a.setAdapter(seatsAdapter);
                    rv_b.setAdapter(seatAdapterB);
                    rv_c.setAdapter(seatAdapterC);
                    rv_d.setAdapter(seatAdapterD);
                }
                else{
                    Toast.makeText(PilihKursiActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SeatsResponse> call, Throwable t) {
                Toast.makeText(PilihKursiActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
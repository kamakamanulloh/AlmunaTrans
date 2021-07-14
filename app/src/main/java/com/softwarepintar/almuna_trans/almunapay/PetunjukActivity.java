package com.softwarepintar.almuna_trans.almunapay;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.Register.Register_Response;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.softwarepintar.almuna_trans.API.Utils.topuop;
import static com.softwarepintar.almuna_trans.API.Utils.total;

public class PetunjukActivity extends AppCompatActivity {

    @BindView(R.id.tv_norek)
    TextView tvNorek;
    @BindView(R.id.btnupload)
    Button btnupload;
    private static int nominal;

    private static final String ALLOWED_CHARACTERS = "0123456789";
    private static final String KODE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petunjuk);
        ButterKnife.bind(this);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

//
        topuop=Integer.parseInt(total);
        int kd= Integer.parseInt(getRandomString());

        nominal= topuop+kd;

        tvNorek.setText("1. Transfer sesuai nominal sampai digit terakhir ke nomor rekening berikut "+getIntent().getStringExtra("rekening")+" bank "+getIntent().getStringExtra("bank")+
                " atas nama "+getIntent().getStringExtra("nama")+" dengan nominal "+formatRupiah.format(nominal));



    }

    private static String getKODE() {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(7);
        for (int i = 0; i < 7; ++i)
            sb.append(KODE.charAt(random.nextInt(KODE.length())));

        return sb.toString();
    }

    private static String getRandomString() {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(3);
        for (int i = 0; i < 3; ++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));

        return sb.toString();
    }
    private void komfirm() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService=retrofit.create(APIService.class);
        Call<Register_Response> call=apiService.topup(String.valueOf(nominal),"TF",
                "0", getIntent().getStringExtra("bankid"),Utils.user_id,getKODE(),
                Utils.nm_user);
        call.enqueue(new Callback<Register_Response>() {
            @Override
            public void onResponse(Call<Register_Response> call, Response<Register_Response> response) {
                int value=response.body().getValue();
                String message=response.body().getMessage();
                if (value==1){

                    Toast.makeText(getApplicationContext(),"Top Up Saldo Berhasil Silahkan Tunggu Admin Akan Memverifikasi" , Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(UploadTransferActivity.this, MainActivity.class));
                }
                else {

                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register_Response> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnupload)
    public void onViewClicked() {
        komfirm();
    }
}

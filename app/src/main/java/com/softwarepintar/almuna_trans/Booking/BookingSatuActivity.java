package com.softwarepintar.almuna_trans.Booking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.PaymentMethod;
import com.midtrans.sdk.corekit.core.UIKitCustomSetting;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.snap.CustomerDetails;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;
import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.Login.Login_Response;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.BuildConfig;
import com.softwarepintar.almuna_trans.midtransmodel.CustomerModel;
import com.softwarepintar.almuna_trans.payment.PembayaranActivity;
import com.softwarepintar.almuna_trans.seats.PilihKursiActivity;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

import static com.softwarepintar.almuna_trans.API.Utils.batas_bayar;
import static com.softwarepintar.almuna_trans.API.Utils.harga;
import static com.softwarepintar.almuna_trans.API.Utils.kdbayar;
import static com.softwarepintar.almuna_trans.API.Utils.total;

public class BookingSatuActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TransactionFinishedCallback {
    @BindView(R.id.tv_all_harga)
    TextView tv_all_harga;
    @BindView(R.id.tv_trayek_kembali)
    TextView tv_trayek_kembali;
    @BindView(R.id.tv_armada_kembali)
    TextView tvArmada_kembali;
    @BindView(R.id.tv_berangkat_kembali)
    TextView tvBerangkat_kembali;
    @BindView(R.id.tv_jam_berangkat_kembali)
    TextView tvJamBerangkat_kembali;
    @BindView(R.id.tv_estimasi_kembali)
    TextView tvEstimasi_kembali;
    @BindView(R.id.tv_kelas_kembali)
    TextView tvKelas_kembali;
    @BindView(R.id.tv_harga_kembali)
    TextView tvHarga_kembali;
    @BindView(R.id.tvkode_kembali)
    TextView tvkode_kembali;
    @BindView(R.id.tv_trayek)
    TextView tvTrayek;
    @BindView(R.id.btn_lanjutkan)
    Button btn_lanjutkan;
    @BindView(R.id.tv_armada)
    TextView tvArmada;
    @BindView(R.id.garis)
    View garis;
    @BindView(R.id.tv_berangkat)
    TextView tvBerangkat;
    @BindView(R.id.tv_jam_berangkat)
    TextView tvJamBerangkat;
    @BindView(R.id.tv_estimasi)
    TextView tvEstimasi;
    @BindView(R.id.tv_kelas)
    TextView tvKelas;
    @BindView(R.id.tv_harga)
    TextView tvHarga;
    @BindView(R.id.tv_nm)
    TextView tvNm;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_nohp)
    TextView tvNohp;
    @BindView(R.id.imgseat)
    ImageView imgseat;
    @BindView(R.id.garis2)
    View garis2;
    @BindView(R.id.spn_id)
    Spinner spnId;
    @BindView(R.id.txt_noid)
    TextInputEditText txtNoid;
    @BindView(R.id.txt_nmpenumpang)
    TextInputEditText txtNmpenumpang;
    @BindView(R.id.penumpang)
    TextInputLayout penumpang;

    @BindView(R.id.card_kembali)
    CardView card_kembali;






    String user_id;
    private static String jenisid;
    @BindView(R.id.tvkode)
    TextView tvkode;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    public static String kode, date, nama, email, phone,nominal;
    private static int totharga;
    private static int totharga_kembali;
    private static int allHarga;
    private static final String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";

    private static final String kd = "0123456789";
    String kode_bayar,nominal_bayar,status_bayar;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_satu);
        ButterKnife.bind(this);

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int jml=Integer.parseInt(getIntent().getStringExtra("jumlah"));
        int harga=Integer.parseInt(getIntent().getStringExtra("harga"));
        int kode=Integer.parseInt(getkd());


        totharga = (jml *harga);


        tvTrayek.setText(getIntent().getStringExtra("asal") + " - " + getIntent().getStringExtra("tujuan"));
        tvArmada.setText(getIntent().getStringExtra("armada_nama"));
        tvBerangkat.setText(getIntent().getStringExtra("tanggal_view"));
        tvJamBerangkat.setText(getIntent().getStringExtra("jam_berangkat"));
        tvEstimasi.setText(getIntent().getStringExtra("estimasi") + " Jam");
        tvKelas.setText(getIntent().getStringExtra("kelas"));
        tvHarga.setText(formatRupiah.format(Double.parseDouble(String.valueOf(totharga))));
        allHarga=totharga+kode;

        if (getIntent().getStringExtra("is_pulangpergi").equalsIgnoreCase("y")){

            int harga_kembali=Integer.parseInt(getIntent().getStringExtra("harga_kembali"));
            totharga_kembali = (jml *harga_kembali);
            card_kembali.setVisibility(View.VISIBLE);


            tv_trayek_kembali.setText(getIntent().getStringExtra("asal_kembali") + " - " + getIntent().getStringExtra("tujuan_kembali"));
            tvArmada_kembali.setText(getIntent().getStringExtra("armada_nama_kembali"));
            tvBerangkat_kembali.setText(getIntent().getStringExtra("tanggal_view_kembali"));
            tvJamBerangkat_kembali.setText(getIntent().getStringExtra("jam_berangkat_kembali"));
            tvEstimasi_kembali.setText(getIntent().getStringExtra("estimasi_kembali") + " Jam_kembali");
            tvKelas_kembali.setText(getIntent().getStringExtra("kelas_kembali"));
            tvHarga_kembali.setText(formatRupiah.format(Double.parseDouble(String.valueOf(totharga_kembali))));
            allHarga=totharga+totharga_kembali+kode;
        }

        tv_all_harga.setText(formatRupiah.format(Double.parseDouble(String.valueOf(allHarga))));
        spnId.setOnItemSelectedListener(this);
        profil();

        calendar = Calendar.getInstance();
        tvkode.setText(getRandomString());
        tvkode_kembali.setText(getRandomString());
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        initMidtransSDK();
        CustomerModel.customerDetails();




//        btnAlfamart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               payAlfamart();
//            }
//        });
//
//        btnIndomaret.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                payIndomaret();
//            }
//        });
//
//        btnBri.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                payBri();
//            }
//        });
//
//        btnMandiri.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                payMandiri();
//            }
//        });

//        btnAlmunapay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              profil();
//            }
//        });



    }

    private void payBri() {

        String id=String.valueOf(kode);

        String title=tvArmada.getText().toString()+" Tujuan "+tvTrayek.getText().toString();

        MidtransSDK.getInstance().setTransactionRequest(CustomerModel.transactionRequest(
                id,
                totharga,
                1,
                title
        ));
        MidtransSDK.getInstance()
                .startPaymentUiFlow(this);

    }

    private void payMandiri(){
        String id=String.valueOf(kode);

        String title=tvArmada.getText().toString()+" Tujuan "+tvTrayek.getText().toString();

        MidtransSDK.getInstance().setTransactionRequest(CustomerModel.transactionRequest(
                id,
                totharga,
                1,
                title
        ));
        MidtransSDK.getInstance()
                .startPaymentUiFlow(this,PaymentMethod.BANK_TRANSFER_MANDIRI);
    }

    private void almunaPay(){

    }

    public static CustomerDetails customerDetails(){
        CustomerDetails cd = new CustomerDetails();
        cd.setName(nama);
        cd.setEmail(email);
        cd.setPhone(phone);
        return cd;
    }

    private void payAlfamart(){
        String id=String.valueOf(kode);

        String title=tvArmada.getText().toString()+" Tujuan "+tvTrayek.getText().toString();

        MidtransSDK.getInstance().setTransactionRequest(CustomerModel.transactionRequest(
                id,
                totharga+2500,
                1,
                title
        ));
        MidtransSDK.getInstance()
                .startPaymentUiFlow(this,PaymentMethod.ALFAMART);
    }

    private void payIndomaret(){
        booking();
        String id=String.valueOf(kode);
        int price=totharga;
        String title=tvArmada.getText().toString()+" Tujuan "+tvTrayek.getText().toString();

        MidtransSDK.getInstance().setTransactionRequest(CustomerModel.transactionRequest(
                id,
                totharga+2500,
                1,
                title
        ));
        MidtransSDK.getInstance()
                .startPaymentUiFlow(this,PaymentMethod.INDOMARET);

    }

    private void profil() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<Login_Response> profilResponseCall = apiService.profil(getIntent().getStringExtra("profil_id"));
        profilResponseCall.enqueue(new Callback<Login_Response>() {
            @Override
            public void onResponse(Call<Login_Response> call, Response<Login_Response> response) {
                int value = response.body().getValue();

                nama = response.body().getNama();
                email = response.body().getEmail();
                phone = response.body().getHp();
                nominal=response.body().getNominal();
                if (value == 1) {
                    user_id = response.body().getUser_id();
                    tvNm.setText(nama);
                    tvNohp.setText(phone);
                    tvEmail.setText(email);
                    txtNoid.setText(response.body().getNo_id());
                    txtNmpenumpang.setText(response.body().getNama());
                    txtNmpenumpang.setEnabled(false);
                    txtNoid.setEnabled(false);
                    spnId.setEnabled(false);


                    List<String> categories = new ArrayList<String>();
                    categories.add(response.body().getJenis_id());

                    // Creating adapter for spinner
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(BookingSatuActivity.this, android.R.layout.simple_spinner_item, categories);

                    // Drop down layout style - list view with radio button
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // attaching data adapter to spinner
                    spnId.setAdapter(dataAdapter);


                } else {
                    Toast.makeText(BookingSatuActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login_Response> call, Throwable t) {
                Toast.makeText(BookingSatuActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }



    private static String getRandomString() {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5; ++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));

        return sb.toString();
    }




    @OnClick(R.id.btn_lanjutkan)
    public void onViewClicked() {
//        MidtransSDK.getInstance().setTransactionRequest(initTransactionRequest());
//        MidtransSDK.getInstance().startPaymentUiFlow(BookingSatuActivity.this);
        booking();

    }

    private static String getkd() {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(3);
        for (int i = 0; i < 3; ++i)
            sb.append(kd.charAt(random.nextInt(kd.length())));

        return sb.toString();
    }

    private void booking() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<BookingResponse> call = apiService.booking(tvkode.getText().toString(),
                txtNoid.getText().toString(), jenisid, getIntent().getStringExtra("asal")
                , getIntent().getStringExtra("tujuan"),
                getIntent().getStringExtra("tanggal") + " " + tvJamBerangkat.getText().toString(),
                getIntent().getStringExtra("profil_id"), String.valueOf(totharga),
                getIntent().getStringExtra("jml_penumpang"),
                getIntent().getStringExtra("estimasi"), txtNmpenumpang.getText().toString(),
                getIntent().getStringExtra("armada_nama"), getIntent().getStringExtra("kelas"),
                 getIntent().getStringExtra("asal_kembali"),getIntent().getStringExtra("tujuan_kembali"),
                getIntent().getStringExtra("tanggal_kembali") + " " + getIntent().getStringExtra("jam_berangkat_kembali"),
                 String.valueOf(totharga_kembali),getIntent().getStringExtra("estimasi_kembali"),
                getIntent().getStringExtra("armada_nama_kembali"),getIntent().getStringExtra("kelas_kembali"),
                String.valueOf(allHarga),getIntent().getStringExtra("is_pulangpergi"),
                tvkode_kembali.getText().toString(),getIntent().getStringExtra("agen_asal"),
                getIntent().getStringExtra("agen_tujuan"),getIntent().getStringExtra("armada_id"),
                getIntent().getStringExtra("armada_id_kembali")
                 );
        call.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                int value = response.body().getValue();

                if (value == 1) {

                    Intent intent = new Intent(BookingSatuActivity.this, PilihKursiActivity.class);
                    batas_bayar = response.body().getBatas_bayar();
                    String detail=getIntent().getStringExtra("armada_nama")+
                            " Tujuan "+ getIntent().getStringExtra("asal")+" - "+ getIntent().getStringExtra("tujuan");
                    total = String.valueOf(totharga);
                    kdbayar = tvkode.getText().toString();
                    harga = getIntent().getStringExtra("harga");
                    intent.putExtra("saldo", nominal);
                    intent.putExtra("qty","1");
                    intent.putExtra("name",detail);
                    intent.putExtra("asal",getIntent().getStringExtra("asal"));
                    intent.putExtra("tujuan",getIntent().getStringExtra("tujuan"));
                    intent.putExtra("armada_nama",getIntent().getStringExtra("armada_nama"));
                    intent.putExtra("armada_nama_kembali",getIntent().getStringExtra("armada_nama_kembali"));
                    intent.putExtra("armada_id",getIntent().getStringExtra("armada_id"));
                    intent.putExtra(Utils.TAG_user_id,getIntent().getStringExtra("profil_id"));
                    intent.putExtra("armada_id_kembali",getIntent().getStringExtra("armada_id_kembali"));
                    intent.putExtra("kode",tvkode.getText().toString());
                    intent.putExtra("harga",String.valueOf(allHarga));
                    intent.putExtra("is_pulangpergi",getIntent().getStringExtra("is_pulangpergi"));
                    intent.putExtra("berangkat",getIntent().getStringExtra("tanggal") + " " + getIntent().getStringExtra("jam_berangkat"));
                    intent.putExtra("kembali",getIntent().getStringExtra("tanggal_kembali") + " " + getIntent().getStringExtra("jam_berangkat_kembali"));

//                    Toast.makeText(BookingSatuActivity.this, batas_bayar, Toast.LENGTH_SHORT).show();


//                    Toast.makeText(BookingSatuActivity.this, "Registrasi Berhasil Silahkan Login", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Log.e("errorbooking",response.body().getMessage().toString());

                    Toast.makeText(BookingSatuActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                Log.e("failurebooking",t.getLocalizedMessage());

                Toast.makeText(BookingSatuActivity.this, "Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        jenisid = parent.getItemAtPosition(position).toString();

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onTransactionFinished(TransactionResult result) {
        if (result.getResponse() != null){
            nominal_bayar=result.getResponse().getGrossAmount();
//            batas_bayar=result.getResponse().getTransactionTime();
            kode_bayar=result.getResponse().getOrderId();



            switch (result.getStatus()){
                case TransactionResult.STATUS_SUCCESS :
                    Toast.makeText(this, "Transaksi Sukses dengan ID : "+result.getResponse().getTransactionId(), Toast.LENGTH_SHORT).show();
                    break;
                case TransactionResult.STATUS_PENDING :
                    Toast.makeText(this, "Transaksi Tertunda dengan ID : "+result.getResponse().getTransactionId(), Toast.LENGTH_SHORT).show();
                    break;
                case TransactionResult.STATUS_FAILED :
                    Toast.makeText(this, "Transaksi Gagal dengan ID : "+result.getResponse().getTransactionId(), Toast.LENGTH_SHORT).show();
                    break;

            }
            result.getResponse().getValidationMessages();
        } else if (result.isTransactionCanceled()) {
            Toast.makeText(this, "Transaksi di Batalkan", Toast.LENGTH_SHORT).show();

        } else {
            if (result.getStatus().equalsIgnoreCase(TransactionResult.STATUS_INVALID)) {
                Toast.makeText(this, "Transaksi invalid", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Transaksi finish with fail", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void initMidtransSDK() {

        UIKitCustomSetting uisetting = new UIKitCustomSetting();
        uisetting.setShowPaymentStatus(true);
        uisetting.setSkipCustomerDetailsPages(true);

        SdkUIFlowBuilder.init()
                .setContext(this)
                .setMerchantBaseUrl(BuildConfig.BASE_URL)
                .setClientKey(BuildConfig.CLIENT_KEY)
                .setTransactionFinishedCallback(this)
                .enableLog(true)
                .setColorTheme(new CustomColorTheme("#174EE6", "#1A45BC", "#2C60EF"))
//                .setUIkitCustomSetting(uisetting)

                .buildSDK();

    }
}

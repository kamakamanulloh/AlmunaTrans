package com.softwarepintar.almuna_trans.Booking;

import android.content.Intent;
import android.os.Bundle;
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
import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.Login.Login_Response;
import com.softwarepintar.almuna_trans.R;
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

public class Booking4Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.tv_trayek)
    TextView tvTrayek;
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
    @BindView(R.id.tvkode)
    TextView tvkode;
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
    @BindView(R.id.imgseat2)
    ImageView imgseat2;
    @BindView(R.id.garis3)
    View garis3;
    @BindView(R.id.spn_id2)
    Spinner spnId2;
    @BindView(R.id.txt_noid2)
    TextInputEditText txtNoid2;
    @BindView(R.id.txt_nmpenumpang2)
    TextInputEditText txtNmpenumpang2;
    @BindView(R.id.penumpang2)
    TextInputLayout penumpang2;
    @BindView(R.id.imgseat3)
    ImageView imgseat3;
    @BindView(R.id.garis4)
    View garis4;
    @BindView(R.id.spn_id3)
    Spinner spnId3;
    @BindView(R.id.txt_noid3)
    TextInputEditText txtNoid3;
    @BindView(R.id.txt_nmpenumpang3)
    TextInputEditText txtNmpenumpang3;
    @BindView(R.id.penumpang3)
    TextInputLayout penumpang3;
    @BindView(R.id.imgseat4)
    ImageView imgseat4;
    @BindView(R.id.garis5)
    View garis5;
    @BindView(R.id.spn_id4)
    Spinner spnId4;
    @BindView(R.id.txt_noid4)
    TextInputEditText txtNoid4;
    @BindView(R.id.txt_nmpenumpang4)
    TextInputEditText txtNmpenumpang4;
    @BindView(R.id.penumpang4)
    TextInputLayout penumpang4;
    @BindView(R.id.btn_lanjutkan)
    Button btnLanjutkan;

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
    @BindView(R.id.card_kembali)
    CardView card_kembali;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private static String date, nama, email, phone,nominal;
    int totharga;
    private static int totharga_kembali;
    private static int allHarga;
    private static final String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";
    private static final String kd = "0123456789";
    private static String jenisid;
    private static String jenisid2;
    private static String jenisid3;
    private static String jenisid4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking4);
        ButterKnife.bind(this);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        int kode=Integer.parseInt(getkd());
        int jml=Integer.parseInt(getIntent().getStringExtra("jumlah"));
//        int jml=Integer.parseInt(getIntent().getStringExtra("jml_penumpang").substring(0, 1));
        totharga = 4 * Integer.parseInt(getIntent().getStringExtra("harga"));
        tvTrayek.setText( " (" + getIntent().getStringExtra("asal") + " - " + getIntent().getStringExtra("tujuan") + ")");
        tvArmada.setText(getIntent().getStringExtra("armada_nama") );
        tvBerangkat.setText(getIntent().getStringExtra("tanggal_view"));
        tvJamBerangkat.setText(getIntent().getStringExtra("jam_berangkat"));
        tvEstimasi.setText(getIntent().getStringExtra("estimasi") + " Jam");
        tvKelas.setText(getIntent().getStringExtra("kelas"));
        tvHarga.setText(formatRupiah.format(Double.parseDouble(String.valueOf(totharga))));
        allHarga=(totharga*3)+kode;
        tvkode.setText(getRandomString());
        tvkode_kembali.setText(getRandomString());
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
            allHarga=(totharga+totharga_kembali)*3+kode;
        }
        spnId.setOnItemSelectedListener(this);
        spnId2.setOnItemSelectedListener(this);
        spnId3.setOnItemSelectedListener(this);
        spnId4.setOnItemSelectedListener(this);
        profil();
        tvkode.setText(getRandomString());
        tv_all_harga.setText(formatRupiah.format(Double.parseDouble(String.valueOf(allHarga))));

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

    }
    private static String getkd() {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(3);
        for (int i = 0; i < 3; ++i)
            sb.append(kd.charAt(random.nextInt(kd.length())));

        return sb.toString();
    }
    private static String getRandomString() {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5; ++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));

        return sb.toString();
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
                    Utils.user_id = response.body().getUser_id();
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
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Booking4Activity.this, android.R.layout.simple_spinner_item, categories);

                    // Drop down layout style - list view with radio button
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // attaching data adapter to spinner
                    spnId.setAdapter(dataAdapter);


                } else {
                    Toast.makeText(Booking4Activity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login_Response> call, Throwable t) {
                Toast.makeText(Booking4Activity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void boking() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<BookingResponse> call = apiService.booking4(tvkode.getText().toString(),
                txtNoid.getText().toString(), jenisid, getIntent().getStringExtra("asal")
                , getIntent().getStringExtra("tujuan"),
                getIntent().getStringExtra("tanggal") + " " + getIntent().getStringExtra("jam_berangkat"),
                getIntent().getStringExtra("profil_id"), String.valueOf(totharga),
                getIntent().getStringExtra("jml_penumpang"),
                getIntent().getStringExtra("estimasi"), txtNmpenumpang.getText().toString(),
                getIntent().getStringExtra("armada_nama"), getIntent().getStringExtra("kelas"),
                txtNoid2.getText().toString(),jenisid2,txtNmpenumpang2.getText().toString(),
                txtNoid3.getText().toString(),jenisid3,txtNmpenumpang3.getText().toString(),
                txtNoid4.getText().toString(),jenisid4,txtNmpenumpang4.getText().toString(),
                getIntent().getStringExtra("asal_kembali"),getIntent().getStringExtra("tujuan_kembali"),
                getIntent().getStringExtra("tanggal_kembali") + " " + getIntent().getStringExtra("jam_berangkat_kembali"),
                String.valueOf(totharga_kembali),getIntent().getStringExtra("estimasi_kembali"),
                getIntent().getStringExtra("armada_nama_kembali"),getIntent().getStringExtra("kelas_kembali"),
                String.valueOf(allHarga),getIntent().getStringExtra("is_pulangpergi"),
                tvkode_kembali.getText().toString(),getIntent().getStringExtra("agen_asal"),
                getIntent().getStringExtra("agen_tujuan"),getIntent().getStringExtra("armada_id"),
                getIntent().getStringExtra("armada_id_kembali"));
        call.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                int value = response.body().getValue();

                if (value == 1) {
                    Intent intent = new Intent(Booking4Activity.this, PilihKursiActivity.class);
                    batas_bayar = response.body().getBatas_bayar();
                    String detail=getIntent().getStringExtra("armada_nama")+
                            " Tujuan "+ getIntent().getStringExtra("asal")+" - "+ getIntent().getStringExtra("tujuan");
                    total = String.valueOf(totharga);
                    kdbayar = tvkode.getText().toString();
                    harga = getIntent().getStringExtra("harga");
                    intent.putExtra("saldo", nominal);
                    intent.putExtra("qty","4");
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

//                    Toast.makeText(BookingSatuActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();


//                    Toast.makeText(BookingSatuActivity.this, "Registrasi Berhasil Silahkan Login", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(Booking4Activity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                Toast.makeText(Booking4Activity.this, "Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @OnClick(R.id.btn_lanjutkan)
    public void onViewClicked() {
        boking();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        jenisid = parent.getItemAtPosition(position).toString();
        jenisid2 = parent.getItemAtPosition(position).toString();
        jenisid3 = parent.getItemAtPosition(position).toString();
        jenisid4 = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

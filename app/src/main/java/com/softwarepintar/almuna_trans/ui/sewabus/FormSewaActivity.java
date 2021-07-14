package com.softwarepintar.almuna_trans.ui.sewabus;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.Booking.BookingResponse;
import com.softwarepintar.almuna_trans.Login.Login_Response;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.ui.sewabus.paketsewa.PaketSewaResultItem;
import com.softwarepintar.almuna_trans.ui.sewabus.pembayaran.PembayaranSewaActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import static com.softwarepintar.almuna_trans.API.Utils.jumlah;
import static com.softwarepintar.almuna_trans.API.Utils.kdbayar;
import static com.softwarepintar.almuna_trans.API.Utils.statussewa;
import static com.softwarepintar.almuna_trans.API.Utils.total;

public class FormSewaActivity extends AppCompatActivity {

    @BindView(R.id.tv_tujuan)
    TextView tvTujuan;
    @BindView(R.id.tv_kode)
    TextView tvKode;
    @BindView(R.id.garis)
    View garis;

    @BindView(R.id.tv_kelas)
    TextView tvKelas;
    @BindView(R.id.tv_harga)
    TextView tvHarga;
    @BindView(R.id.date)
    ImageView date;
    @BindView(R.id.tv_berangkat)
    TextView tvBerangkat;
    @BindView(R.id.maps)
    ImageView maps;

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
    @BindView(R.id.btn_lanjutkan)
    Button btnLanjutkan;
    private static final String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";
    private static final String KD = "0123456789";
    PaketSewaResultItem paketSewaResultItem;
    @BindView(R.id.tv_kapasitas)
    TextView tvKapasitas;
    @BindView(R.id.time)
    ImageView time;
    @BindView(R.id.tv_jam_berangkat)
    TextView tvJamBerangkat;
    @BindView(R.id.et_alamat)
    EditText etAlamat;
    Calendar c;

    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    int PLACE_PICKER_REQUEST = 1;
    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
     private static String alamat_jemput, lat, lng,nominal;
//
    String nama, email, phone, user_id,kdtujuan,kode,noid;
    @BindView(R.id.txtjmlbus)
    TextInputEditText txtjmlbus;
    private static String kodesewa,berangkat;
    public static int totharga;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sewa);
        ButterKnife.bind(this);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        paketSewaResultItem = getIntent().getParcelableExtra(Utils.EXTRA_NEWS);
        kdbayar=getRandomString();
        kdtujuan=paketSewaResultItem.getTujuan().substring(0,3);
        kode=kdtujuan+kdbayar;
        tvKode.setText("Kode Booking "+kdtujuan+kdbayar);

        tvTujuan.setText(paketSewaResultItem.getTujuan()+" "+paketSewaResultItem.getDurasi());

        tvKapasitas.setText("Kapasitas : " + getIntent().getStringExtra("kapasitas") + " Kursi");
        tvKelas.setText(getIntent().getStringExtra("kelas"));
        tvHarga.setText(formatRupiah.format(Double.parseDouble(paketSewaResultItem.getHarga())));
        profil();
    }

    private static String getRandomString() {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5; ++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));

        return sb.toString();
    }


    @OnClick({R.id.date, R.id.tv_berangkat, R.id.time, R.id.tv_jam_berangkat, R.id.maps, R.id.btn_lanjutkan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.date:
            case R.id.tv_berangkat:
                // Get Current Date
                c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                c.add(Calendar.DAY_OF_MONTH,+1);
                int mDay = c.get(Calendar.DAY_OF_MONTH);



                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {


                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                berangkat=year+"-"+(monthOfYear + 1)+ "-" +dayOfMonth;

                                        tvBerangkat.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;


            case R.id.time:

            case R.id.tv_jam_berangkat:
                c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                tvJamBerangkat.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();


                break;

//            case R.id.maps:
//                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//                try {
//                    Intent intent = builder.build(this);
//                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
//                } catch (GooglePlayServicesRepairableException e) {
//                    e.printStackTrace();
//                } catch (GooglePlayServicesNotAvailableException e) {
//                    e.printStackTrace();
//                }
//                break;
            case R.id.btn_lanjutkan:
                sewa();
                break;
        }
    }

    private static String getkd() {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(3);
        for (int i = 0; i < 3; ++i)
            sb.append(KD.charAt(random.nextInt(KD.length())));

        return sb.toString();
    }

    private void sewa() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        int jmlhbus=Integer.parseInt(txtjmlbus.getText().toString());
        int harga=Integer.parseInt(paketSewaResultItem.getHarga());

        int kd= Integer.parseInt(getkd());
        totharga= jmlhbus*harga+kd;

        int persen=70*totharga;
        int potongan=persen/100;
        Utils.bayardp=totharga-potongan;

        APIService apiService = retrofit.create(APIService.class);
        Call<BookingResponse>call=apiService.sewa(berangkat,
                tvJamBerangkat.getText().toString(),kdbayar," "," ",getIntent().getStringExtra("user_id")
                ,txtjmlbus.getText().toString(),paketSewaResultItem.getId(), String.valueOf(totharga),
                etAlamat.getText().toString(), paketSewaResultItem.getHarga());

      call.enqueue(new Callback<BookingResponse>() {
          @Override
          public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
              int value = response.body().getValue();

              if (value == 1) {

                  Intent intent = new Intent(FormSewaActivity.this, PembayaranSewaActivity.class);
                  batas_bayar = response.body().getBatas_bayar();
                  total= String.valueOf(totharga);
                  jumlah=txtjmlbus.getText().toString();
                  statussewa="Belum";
                  intent.putExtra("saldo",nominal);


//                    Toast.makeText(BookingSatuActivity.this, "Registrasi Berhasil Silahkan Login", Toast.LENGTH_SHORT).show();
                  startActivity(intent);
              }
              else {
                  Toast.makeText(FormSewaActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
              }
          }

          @Override
          public void onFailure(Call<BookingResponse> call, Throwable t) {
              Toast.makeText(FormSewaActivity.this, "Failure", Toast.LENGTH_SHORT).show();

          }
      });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "This app requires location permissions to be granted", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    //    method memilih aksi
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", "requestCode " + requestCode + ", resultCode " + resultCode);


        if (requestCode == PLACE_PICKER_REQUEST) {
//            if (resultCode == RESULT_OK) {
//                Place place = PlacePicker.getPlace(data, this);
//                String toastMsg = String.format(
//                        "Place: %s \n" +
//                                "Alamat: %s \n" +
//                                "Latlng %s \n", place.getName(), place.getAddress(), place.getLatLng().latitude+" "+place.getLatLng().longitude);
//                Toast.makeText(this,toastMsg,Toast.LENGTH_LONG).show();
//                tvLokasi.setText(place.getAddress());
//                alamat_jemput = (String) place.getAddress();
//                lat = String.valueOf(place.getLatLng().latitude);
//                lng = String.valueOf(place.getLatLng().longitude);
////                tvPlaceAPI.setText(toastMsg);
//            }
        }

    }

    private void profil() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<Login_Response> profilResponseCall = apiService.profil(getIntent().getStringExtra("user_id"));
        profilResponseCall.enqueue(new Callback<Login_Response>() {
            @Override
            public void onResponse(Call<Login_Response> call, Response<Login_Response> response) {
                int value = response.body().getValue();
                nama = response.body().getNama();
                email = response.body().getEmail();
                phone = response.body().getHp();
                nominal=response.body().getNominal();
                noid=response.body().getNo_id();
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
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(FormSewaActivity.this, android.R.layout.simple_spinner_item, categories);

                    // Drop down layout style - list view with radio button
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // attaching data adapter to spinner
                    spnId.setAdapter(dataAdapter);


                } else {
                    Toast.makeText(FormSewaActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login_Response> call, Throwable t) {
                Toast.makeText(FormSewaActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }


}


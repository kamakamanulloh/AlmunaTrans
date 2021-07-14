package com.softwarepintar.almuna_trans.ui.home;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.DatePickerFragment;
import com.softwarepintar.almuna_trans.Info.InfoFragment;
import com.softwarepintar.almuna_trans.Jadwal.JadwalFragment;
import com.softwarepintar.almuna_trans.MainActivity;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.ResultItem;
import com.softwarepintar.almuna_trans.agen.AgenResponse;
import com.softwarepintar.almuna_trans.almunapay.HistoryFragment;
import com.softwarepintar.almuna_trans.almunapay.TopUpFragment;
import com.softwarepintar.almuna_trans.kota.ListKotaFragment;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements
        SearchView.OnQueryTextListener, SearchView.OnCloseListener {


    @BindView(R.id.spinnerAsal)
    Spinner spinnerasal;

    @BindView(R.id.spinnerTujuan)
    Spinner spinnerTujuan;

    @BindView(R.id.spinnersubAsal)
    Spinner spinnersubAsal;

    @BindView(R.id.spinnersubTujuan)
    Spinner spinnersubTujuan;

    @BindView(R.id.line1)
    LinearLayout line1;
    @BindView(R.id.berangkat)
    TextView berangkat;
    @BindView(R.id.tvberangkat)
    TextView tvberangkat;

    @BindView(R.id.tvkembali)
    TextView tvKembali;

    @BindView(R.id.kembali)
    TextView kembali;

    @BindView(R.id.isPulangPergi)
    CheckBox isPulangPergi;

    @BindView(R.id.linear2)
    LinearLayout linear2;

    @BindView(R.id.tvpenumpang)
    TextView tvpenumpang;
    @BindView(R.id.spinnerpenumpang)
    Spinner spinnerpenumpang;

    @BindView(R.id.spinnerBayi)
    Spinner spinnerBayi;

    @BindView(R.id.tvaturan)
    TextView tvaturan;
    @BindView(R.id.btn_caritiket)
    Button btnCaritiket;
    @BindView(R.id.relative_berangkat)
    RelativeLayout relativeBerangkat;

    @BindView(R.id.cardview)
    CardView cardview;
    @BindView(R.id.tvinfo)
    TextView tvinfo;
    @BindView(R.id.rvinfo)
    RecyclerView rvinfo;
    @BindView(R.id.tvsaldo)
    TextView tvsaldo;
    @BindView(R.id.btntopup)
    ImageView btntopup;
    @BindView(R.id.btnreward)
    ImageView btnreward;
    @BindView(R.id.btnriwayat)
    ImageView btnriwayat;
    @BindView(R.id.cardview2)
    CardView cardview2;

    @BindView(R.id.tvlihat)
    TextView tvlihat;
    private String date,dateKembali;
    private String kota_asal, kota_tujuan,sub_kota_asal,sub_kota_tujuan;
    private Kota_Adapter kota_adapter;
    private String tanggal,tanggalKembali;
    private String ispulangpergi="n";
    private List<InfoResultItem> infoResultItems = new ArrayList<>();
    private InfoAdapter infoAdapter;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
   private static final Bundle mBundle = new Bundle();




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //    @BindView(R.id.relative_kembali)
        //    RelativeLayout relativeKembali;
        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Unbinder unbinder = ButterKnife.bind(this, root);
        ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setTitle("Almuna Trans");
        ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setSubtitle("");


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.jml_penumpang, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerpenumpang.setAdapter(adapter);

        ArrayAdapter<CharSequence> bayiAdapter=ArrayAdapter.createFromResource(getContext(),
                R.array.jml_bayi,android.R.layout.simple_spinner_item);
        bayiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBayi.setAdapter(bayiAdapter);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        mysaldo();

        list_kota();

        spinnersubAsal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sub_kota_asal=parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        spinnersubTujuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sub_kota_tujuan=parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });



        spinnerasal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kota_asal=parent.getItemAtPosition(position).toString();
                list_agen(kota_asal);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        spinnerTujuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kota_tujuan=parent.getItemAtPosition(position).toString();
                list_agen_tujuan(kota_tujuan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        homeViewModel.getText().observe(this, s -> {

        });

        listinfo();

        return root;
    }

    private void list_agen_tujuan(String kota_tujuan) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<AgenResponse> call = apiService.list_agen(kota_tujuan);
        call.enqueue(new Callback<AgenResponse>() {
            @Override
            public void onResponse(Call<AgenResponse> call, Response<AgenResponse> response) {
                int value = response.body().getValue();
                if (value == 1) {

                    List<ResultItem>resultItemList=response.body().getResult();

                    List<String>listAgenTujuan=new ArrayList<String>();

                    listAgenTujuan.add(0,"Pilih Agen Tujuan");
                    for (int i=0;i<resultItemList.size();i++){

                        listAgenTujuan.add(resultItemList.get(i).getNama());
                    }

                    ArrayAdapter<String>adapterAgenTujuan=new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item,listAgenTujuan);

                    spinnersubTujuan.setAdapter(adapterAgenTujuan);
                    adapterAgenTujuan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



                }
                else{
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AgenResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void mysaldo() {
        Toast.makeText(getContext(),Utils.user_id,Toast.LENGTH_LONG).show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<SaldoResponse> call = api.saldo(Utils.user_id);
        call.enqueue(new Callback<SaldoResponse>() {
            @Override
            public void onResponse(Call<SaldoResponse> call, Response<SaldoResponse> response) {
                int Response_Item = response.body().getValue();
                Utils.saldo=response.body().getSaldo();


                if (Response_Item == 1) {
                    tvsaldo.setText(formatRupiah.format(Double.parseDouble(response.body().getSaldo())));

                }
            }

            @Override
            public void onFailure(Call<SaldoResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void listinfo() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        rvinfo.setLayoutManager(layoutManager);
        rvinfo.setHasFixedSize(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<InfoResponse> call = api.info();
        call.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {
                int Response_Item = response.body().getValue();

                if (Response_Item == 1) {
                    infoResultItems = response.body().getResult();
                    infoAdapter = new InfoAdapter(getContext(), infoResultItems);
                    rvinfo.setAdapter(infoAdapter);
                }
            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {
                Log.e("error_info",t.getLocalizedMessage());
                Toast.makeText(getContext(), "Gagal", Toast.LENGTH_SHORT).show();

            }
        });

    }



//    private void sliderbanner() {
//
//        request = new JsonArrayRequest("https://almunatrans.com/api/slider.php", (com.android.volley.Response.Listener<JSONArray>) response -> {
//
//            JSONObject jsonObject = null;
//            for (int i = 0; i < response.length(); i++) {
//                try {
//                    jsonObject = response.getJSONObject(i);
//                    List_Data listData = new List_Data(jsonObject.getString("img"));
//                    list_data.add(listData);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            setupdata(list_data);
//
//
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//
//                requestQueue = Volley.newRequestQueue(getContext());
//        requestQueue.add(request);
//
//    }
//
//    private void setupdata(List<List_Data> list_dataList) {
//
//        for (int i = 0; i <= 4; i++) {
//            final List_Data ld = list_dataList.get(i);
//            SliderView sliderView = new SliderView(getContext());
//            sliderView.setImageUrl(ld.getImg());
//
//            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
//            final int finalI = i;
//            sliderLayout.addSliderView(sliderView);
//
//        }
//    }


    private void list_kota() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<Response_Kota> call = apiService.list_kota();
        call.enqueue(new Callback<Response_Kota>() {
            @Override
            public void onResponse(Call<Response_Kota> call, Response<Response_Kota> response) {
                int value = response.body().getValue();
                if (value == 1) {
                    List<ResultasalItem>resultItemKotaList=response.body().getResultasal();
                    List<ResultujuanItem>resultujuanItemList=response.body().getResultujuan();
                    List<String>listKota=new ArrayList<String>();
                    List<String>listTujuan=new ArrayList<String>();
                    listKota.add(0,"Pilih Kota Asal");
                    listTujuan.add(0,"Pilih Kota Tujuan");
                    for (int i=0;i<resultItemKotaList.size();i++){

                        listKota.add(resultItemKotaList.get(i).getRuteMulai());
                        listTujuan.add(resultujuanItemList.get(i).getRuteTujuan());
                    }
                    ArrayAdapter<String>adapter=new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item,listKota);

                    ArrayAdapter<String>adaptertujuan=new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item,listTujuan);
                    spinnerasal.setAdapter(adapter);
                    spinnerTujuan.setAdapter(adaptertujuan);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



                }
            }

            @Override
            public void onFailure(Call<Response_Kota> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void list_agen(String kota) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<AgenResponse> call = apiService.list_agen(kota);
        call.enqueue(new Callback<AgenResponse>() {
            @Override
            public void onResponse(Call<AgenResponse> call, Response<AgenResponse> response) {
                int value = response.body().getValue();
                if (value == 1) {

                    List<ResultItem>resultItemList=response.body().getResult();
                    List<String>listAgenAsal=new ArrayList<String>();
                    List<String>listAgenTujuan=new ArrayList<String>();
                    listAgenAsal.add(0,"Pilih Agen Asal");
                    listAgenTujuan.add(0,"Pilih Agen Tujuan");
                    for (int i=0;i<resultItemList.size();i++){

                        listAgenAsal.add(resultItemList.get(i).getNama());
                        listAgenTujuan.add(resultItemList.get(i).getNama());
                    }
                    ArrayAdapter<String>adapterAgenAsal=new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item,listAgenAsal);

                    ArrayAdapter<String>adapterAgenTujuan=new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item,listAgenAsal);
                    spinnersubAsal.setAdapter(adapterAgenAsal);
                    spinnersubTujuan.setAdapter(adapterAgenTujuan);
                    adapterAgenAsal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



                }
                else{
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AgenResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }




//    private void showdatekembali() {
//        DatePickerFragment date = new DatePickerFragment();
//        /**
//         * Set Up Current Date Into dialog
//         */
//        Calendar calender = Calendar.getInstance();
//        Bundle args = new Bundle();
//        args.putInt("year", calender.get(Calendar.YEAR));
//        args.putInt("month", calender.get(Calendar.MONTH));
//        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
//        date.setArguments(args);
//        /**
//         * Set Call back to capture selected date
//         */
//        date.setCallBack(ondatekembali);
//        date.show(getFragmentManager(), "Date Picker");
//    }
//
//    DatePickerDialog.OnDateSetListener ondatekembali = new DatePickerDialog.OnDateSetListener() {
//
//        public void onDateSet(DatePicker view, int year, int monthOfYear,
//                              int dayOfMonth) {
//
//            tvkembali.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
//                    + "-" + String.valueOf(year));
//        }
//    };


    private void showdateberangkat() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Calendar minDate=Calendar.getInstance();
        minDate.add(Calendar.DAY_OF_MONTH,1);
        Calendar maxDate=Calendar.getInstance();
        maxDate.add(Calendar.DAY_OF_MONTH,7);
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        calender.add(Calendar.DAY_OF_MONTH,1);

        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));


        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {


        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            tvberangkat.setText(dayOfMonth + "-" + (monthOfYear + 1)
                    + "-" + year);
            int bulan = monthOfYear + 1;
            date = year + "-" + bulan + "-" + dayOfMonth;
            tanggal = dayOfMonth + "-" + bulan + "-" + year;

        }
    };


    private void showdatepulang() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Calendar minDate=Calendar.getInstance();
        minDate.add(Calendar.DAY_OF_MONTH,1);
        Calendar maxDate=Calendar.getInstance();
        maxDate.add(Calendar.DAY_OF_MONTH,7);
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        calender.add(Calendar.DAY_OF_MONTH,1);

        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));


        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondatePulang);
        date.show(getFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondatePulang = new DatePickerDialog.OnDateSetListener() {


        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            tvKembali.setText(dayOfMonth + "-" + (monthOfYear + 1)
                    + "-" + year);
            int bulan = monthOfYear + 1;
            dateKembali = year + "-" + bulan + "-" + dayOfMonth;
            tanggalKembali = dayOfMonth + "-" + bulan + "-" + year;

        }
    };

    @OnClick({R.id.tvberangkat, R.id.btn_caritiket,R.id.btntopup,R.id.btnreward,R.id.btnriwayat,R.id.tvlihat,R.id.isPulangPergi,R.id.tvkembali})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tvberangkat:
                showdateberangkat();
                break;

            case R.id.isPulangPergi:
                if (isPulangPergi.isChecked()){
                    showdatepulang();
                    tvKembali.setVisibility(View.VISIBLE);
                    ispulangpergi="y";

                }
                else{
                    tvKembali.setText(" ");
                    tvKembali.setVisibility(View.GONE);
                    ispulangpergi="n";
                }
                tvKembali.isEnabled();
                kembali.isEnabled();
                tvKembali.setTextColor(R.color.text_blue);
                kembali.setTextColor(R.color.text_color);

            break;

            case R.id.tvkembali:
                   showdatepulang();
            break;


//            case R.id.tv_tujuan:
//                ListKotaFragment listKotaFragment=new ListKotaFragment();
//
//                mBundle.putString("jenis","tujuan");
//                listKotaFragment.show(mFragmentManager," ");
//                break;
            case R.id.btn_caritiket:
                FragmentManager mFragmentManager = getFragmentManager();
                JadwalFragment secondFragtry = new JadwalFragment();

                mBundle.putString("user_id", Utils.user_id);
                mBundle.putString("asal", kota_asal);
                mBundle.putString("tujuan", kota_tujuan);
                mBundle.putString("tanggal", date);
                mBundle.putString("tanggal_view", tanggal);
                mBundle.putString("penumpang", spinnerpenumpang.getSelectedItem().toString());
                mBundle.putString("is_pulangpergi", ispulangpergi);
                mBundle.putString("agen_asal", sub_kota_asal);
                mBundle.putString("agen_tujuan", sub_kota_tujuan);
                mBundle.putString("bayi", spinnerBayi.getSelectedItem().toString());
                mBundle.putString("tanggal_pergi", dateKembali);
                mBundle.putString("tanggal_view_pergi", tanggalKembali);

                secondFragtry.setArguments(mBundle);

                mFragmentManager = getFragmentManager();
                FragmentTransaction mFragmentTransaction = mFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, secondFragtry, JadwalFragment.class.getSimpleName());
                mFragmentTransaction.addToBackStack(null).commit();
                break;

            case R.id.btntopup:
                TopUpFragment topUpFragment = new TopUpFragment();
                mBundle.putString("saldo", tvsaldo.getText().toString());

                 mFragmentManager = getFragmentManager();
                 mFragmentTransaction = mFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, topUpFragment, TopUpFragment.class.getSimpleName());
                mFragmentTransaction.addToBackStack(null).commit();

                break;
            case R.id.btnriwayat:
                HistoryFragment historyFragment = new HistoryFragment();

                mFragmentManager = getFragmentManager();
                mFragmentTransaction = mFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, historyFragment, HistoryFragment.class.getSimpleName());
                mFragmentTransaction.addToBackStack(null).commit();

                break;

            case R.id.tvlihat:
                InfoFragment infoFragment = new InfoFragment();

                mFragmentManager = getFragmentManager();
                mFragmentTransaction = mFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, infoFragment, InfoFragment.class.getSimpleName());
                mFragmentTransaction.addToBackStack(null).commit();

                break;

        }
    }

    @Override
    public boolean onClose() {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
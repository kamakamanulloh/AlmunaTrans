package com.softwarepintar.almuna_trans.Jadwal;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.MainActivity;
import com.softwarepintar.almuna_trans.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JadwalFragment extends Fragment {


    @BindView(R.id.rvjadwal)
    RecyclerView rvjadwal;

    @BindView(R.id.img)
    ImageView img_;

    @BindView(R.id.txt)
    TextView txt_;

    private JadwalViewModel mViewModel;
    public static String asal;
    public static String tujuan;
    public static String tanggal;
    public static String tanggal_pergi;
    public static String is_pulangpergi="n";
    public static String agen_asal;
    public static String agen_tujuan;
    public static String bayi;
    public static String tanggal_view_pergi;



    private Jadwal_Adapter jadwal_adapter;
    private List<Jadwal_ResultItem> jadwal_resultItems = new ArrayList<>();
    ProgressDialog progress;
    public static String jml_penumpang;
    public static String tanggalView;

    public static JadwalFragment newInstance() {
        return new JadwalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jadwal_fragment, container, false);
        Unbinder unbinder = ButterKnife.bind(this, view);
        asal = getArguments().getString("asal");
        tujuan = getArguments().getString("tujuan");
        tanggal = getArguments().getString("tanggal");
        jml_penumpang = getArguments().getString("penumpang");
        tanggal_pergi=getArguments().getString("tanggal_pergi");
        is_pulangpergi=getArguments().getString("is_pulangpergi");
        agen_asal=getArguments().getString("agen_asal");
        agen_tujuan= getArguments().getString("agen_tujuan");
        bayi= getArguments().getString("bayi");
        tanggal_view_pergi= getArguments().getString("tanggal_view_pergi");
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        tanggalView=getArguments().getString("tanggal_view");



        ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setTitle(asal + " - " + tujuan);
        ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setSubtitle(tanggalView+ " " +jml_penumpang );

        jadwal_adapter = new Jadwal_Adapter(getContext(), jadwal_resultItems);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvjadwal.setLayoutManager(mLayoutManager);
        rvjadwal.setItemAnimator(new DefaultItemAnimator());
        rvjadwal.setAdapter(jadwal_adapter);



        setHasOptionsMenu(true);
        showjadwal();


        return view;
    }

    private void showjadwal() {
        progress = new ProgressDialog(getContext());
        progress.setCancelable(true);
        progress.setMessage("Sedang Proses");
        progress.show();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<Jadwal_Response> call = api.list_jadwal(asal, tujuan, tanggal);

        call.enqueue(new Callback<Jadwal_Response>() {
            @Override
            public void onResponse(Call<Jadwal_Response> call, Response<Jadwal_Response> response) {
                int Response_Item = response.body().getValue();
                progress.dismiss();
                rvjadwal.setVisibility(View.VISIBLE);
                jadwal_resultItems = response.body().getResult();
//                Toast.makeText(getContext(),jadwal_resultItems.size(),Toast.LENGTH_LONG).show();

                if (Response_Item == 1) {

                    int jumlahJadwal=response.body().getJumlahJadwal();
                    if (jumlahJadwal > 0){
                        jadwal_adapter = new Jadwal_Adapter(getContext(), jadwal_resultItems);
                        rvjadwal.setAdapter(jadwal_adapter);
                    }
                    else{
                        img_.setVisibility(View.VISIBLE);
                        txt_.setVisibility(View.VISIBLE);



                    }


                }


            }

            @Override
            public void onFailure(Call<Jadwal_Response> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal", Toast.LENGTH_LONG).show();

            }


        });
    }

    //
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(JadwalViewModel.class);
//        // TODO: Use the ViewModel
//        mViewModel.getJadwal(asal, tujuan, tanggal).observe(this, jadwal_resultItems1 -> {
//            jadwal_adapter = new Jadwal_Adapter(getContext(), jadwal_resultItems);
//            rvjadwal.setLayoutManager(new LinearLayoutManager(getContext()));
//            rvjadwal.setAdapter(jadwal_adapter);
//            rvjadwal.setHasFixedSize(true);
//            jadwal_adapter.notifyDataSetChanged();
//            showLoading();
//
//
//        });
//
//    }
//
//    private void showLoading() {
//        if (false) {
//            progressBar.setVisibility(View.VISIBLE);
//        } else {
//            progressBar.setVisibility(View.GONE);
//        }
//
//    }
//
//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//
//    }
}

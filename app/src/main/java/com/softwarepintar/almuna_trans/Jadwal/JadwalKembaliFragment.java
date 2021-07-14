package com.softwarepintar.almuna_trans.Jadwal;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JadwalKembaliFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JadwalKembaliFragment extends Fragment {
    @BindView(R.id.rvjadwalkembali)
    RecyclerView rvjadwalkembali;

    @BindView(R.id.img)
    ImageView img_;

    @BindView(R.id.txt)
    TextView txt_;

    private JadwalViewModel mViewModel;
    public static String asal;
    public static String tujuan;
    public static String tanggal;
    public static String tanggal_pergi;
    public static String is_pulangpergi;
    public static String agen_asal;
    public static String agen_tujuan;
    public static String bayi;
    public static String tanggal_view_pergi;
    public static String jadwal_id;
    public static String jam_berangkat;
    public static String estimasi;
    public static String armada_nama;
    public static String kelas;
    public static String harga;
    public static String tipe_seat;
    public static String tanggal_view;
    public static String kapasitas_berangkat,armada_berangkat,armada_id_berangkat,
            waktu_berangkat,estimasi_berangkat,asal_berangkat,tujuan_berangkat,
            jadwal_id_berangkat,jam_berangkat_berangkat,tanggal_berangkat,
            kelas_berangkat,kelas_id_berangkat,harga_berangkat,tanggal_view_berangkat;


    private Jadwal_Adapter_Kembali jadwal_adapter;
    private List<Jadwal_ResultItem> jadwal_resultItems = new ArrayList<>();
    ProgressDialog progress;
    public static String jml_penumpang;
    public static String tanggalView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Jadwal_ResultItem jadwal_resultItem;
    public JadwalKembaliFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JadwalKembaliFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JadwalKembaliFragment newInstance(String param1, String param2) {
        JadwalKembaliFragment fragment = new JadwalKembaliFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_jadwal_kembali, container, false);
        Unbinder unbinder = ButterKnife.bind(this, view);

        asal = getArguments().getString("asal");
        tujuan = getArguments().getString("tujuan");
        tanggal = getArguments().getString("tanggal");
        jml_penumpang = getArguments().getString("penumpang");
        tanggal_pergi=getArguments().getString("tanggal_pergi");
        is_pulangpergi=getArguments().getString("is_pulangpergi");
        tipe_seat=getArguments().getString("tipe_seat");
        agen_asal=getArguments().getString("agen_asal");
        agen_tujuan= getArguments().getString("agen_tujuan");
        bayi= getArguments().getString("bayi");
        tanggal_view_pergi= getArguments().getString("tanggal_view_pergi");

        kapasitas_berangkat=getArguments().getString("kapasitas_berangkat");
        armada_berangkat=getArguments().getString("armada_nama_berangkat");
        armada_id_berangkat=getArguments().getString("armada_id_berangkat");

        waktu_berangkat=getArguments().getString("jam_berangkat");
        estimasi_berangkat=getArguments().getString("estimasi_berangkat");
        asal_berangkat=getArguments().getString("asal_berangkat");
        tujuan_berangkat=getArguments().getString("tujuan_berangkat");
        jadwal_id_berangkat=getArguments().getString("jadwal_id_berangkat");
        jam_berangkat_berangkat=getArguments().getString("jam_berangkat_berangkat");
        tanggal_berangkat=getArguments().getString("tanggal");
        kelas_berangkat=getArguments().getString("kelas_nama_berangkat");
        kelas_id_berangkat=getArguments().getString("kelas_id_berangkat");
        harga_berangkat=getArguments().getString("harga_berangkat");
        tanggal_view_berangkat=getArguments().getString("tanggal_view");

        Toast.makeText(getContext(),waktu_berangkat+kapasitas_berangkat,Toast.LENGTH_LONG).show();



        ((AppCompatActivity)getActivity()).getSupportActionBar().show();


        ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setTitle(tujuan + " - " + asal);
        ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setSubtitle(tanggal_view_pergi+ " " +jml_penumpang );
        jadwal_adapter = new Jadwal_Adapter_Kembali(getContext(), jadwal_resultItems);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvjadwalkembali.setLayoutManager(mLayoutManager);
        rvjadwalkembali.setItemAnimator(new DefaultItemAnimator());
        rvjadwalkembali.setAdapter(jadwal_adapter);



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
        Call<Jadwal_Response> call = api.list_jadwal(tujuan, asal, tanggal_pergi);

        call.enqueue(new Callback<Jadwal_Response>() {
            @Override
            public void onResponse(Call<Jadwal_Response> call, Response<Jadwal_Response> response) {
                int Response_Item = response.body().getValue();
                progress.dismiss();
                rvjadwalkembali.setVisibility(View.VISIBLE);
                jadwal_resultItems = response.body().getResult();
//                Toast.makeText(getContext(),jadwal_resultItems.size(),Toast.LENGTH_LONG).show();

                if (Response_Item == 1) {

                    int jumlahJadwal=response.body().getJumlahJadwal();
                    if (jumlahJadwal > 0){
                        jadwal_adapter = new Jadwal_Adapter_Kembali(getContext(), jadwal_resultItems);
                        rvjadwalkembali.setAdapter(jadwal_adapter);
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

}
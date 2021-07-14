package com.softwarepintar.almuna_trans.kota;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.ui.home.Kota_Adapter;
import com.softwarepintar.almuna_trans.ui.home.ResultasalItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListKotaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListKotaFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private KotaViewModel kotaViewModel;
    LinearLayoutManager linearLayoutManager;
    private List<ResultasalItem> resultItem_kotasot = new ArrayList<>();
    private Kota_Adapter kota_adapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.rvkota)
    RecyclerView rvkota;


    public ListKotaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListKotaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListKotaFragment newInstance(String param1, String param2) {
        ListKotaFragment fragment = new ListKotaFragment();
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
        View rootview= inflater.inflate(R.layout.fragment_list_kota, container, false);
     kotaViewModel= ViewModelProviders.of(this).get(KotaViewModel.class);
        Utils.jenis=getArguments().getString("jenis");
      if (Objects.equals(Utils.jenis, "tujuan")){

          getDialog().setTitle("Pilih  Tujuan");

      }
      else if(Objects.equals(Utils.jenis, "asal")){

          getDialog().setTitle("Pilih  Berangkat");

      }

        return rootview;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        kotaViewModel.getKota().observe(this,resultItem_kotas -> {
            if (resultItem_kotasot!=null){
                kota_adapter=new Kota_Adapter(getContext(),resultItem_kotasot);

                rvkota.setLayoutManager(linearLayoutManager);
                rvkota.setAdapter(kota_adapter);
                kota_adapter.notifyDataSetChanged();
                rvkota.setHasFixedSize(true);
            }
        });
    }
}
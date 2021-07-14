package com.softwarepintar.almuna_trans.almunapay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarepintar.almuna_trans.MainActivity;
import com.softwarepintar.almuna_trans.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HistoryFragment extends Fragment {

    @BindView(R.id.rvhist)
    RecyclerView rvhist;
    private HistoryViewModel mViewModel;
    private RiwayatAdapter riwayatAdapter;
    LinearLayoutManager linearLayoutManager;
    private List<RiwayatResultItem> riwayatResultItemList = new ArrayList<>();

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment, container, false);
        Unbinder unbinder = ButterKnife.bind(this, view);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setTitle("Riwayat Transaksi");
        ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setSubtitle("");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        // TODO: Use the ViewModel
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        // TODO: Use the ViewModel

        mViewModel.getKelas().observe(this, kelasResultItems1 -> {
            if (riwayatResultItemList != null) {
                riwayatAdapter = new RiwayatAdapter(getContext(), kelasResultItems1);
                linearLayoutManager = new LinearLayoutManager(getContext());

                rvhist.setLayoutManager(linearLayoutManager);
                rvhist.setAdapter(riwayatAdapter);
                riwayatAdapter.notifyDataSetChanged();
                rvhist.setHasFixedSize(true);

            }
        });
    }

}

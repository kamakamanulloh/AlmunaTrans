package com.softwarepintar.almuna_trans.ui.TiketSaya.tiketaktif;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.ui.TiketSaya.TiketAdapter;
import com.softwarepintar.almuna_trans.ui.TiketSaya.TiketResultItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TiketAktifFragment extends Fragment {

    @BindView(R.id.rvaktif)
    RecyclerView rvaktif;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private TiketAktifViewModel mViewModel;
    LinearLayoutManager linearLayoutManager;
    private List<TiketResultItem> resultItems = new ArrayList<>();
    private TiketAdapter tiketAdapter;


    public static TiketAktifFragment newInstance() {
        return new TiketAktifFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = ViewModelProviders.of(this).get(TiketAktifViewModel.class);

        View view = inflater.inflate(R.layout.tiket_aktif_fragment, container, false);
        Unbinder unbinder = ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel.getTiket().observe(this, tiketResultItems -> {
            if (resultItems != null) {
                tiketAdapter = new TiketAdapter(getContext(), tiketResultItems);
                linearLayoutManager = new LinearLayoutManager(getContext());

                rvaktif.setLayoutManager(linearLayoutManager);
                rvaktif.setAdapter(tiketAdapter);
                tiketAdapter.notifyDataSetChanged();
                rvaktif.setHasFixedSize(true);
                showloading();

            }
        });
    }
    private void showloading() {
        if (false) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}

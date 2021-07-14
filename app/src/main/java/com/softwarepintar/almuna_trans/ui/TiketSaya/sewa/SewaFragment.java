package com.softwarepintar.almuna_trans.ui.TiketSaya.sewa;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SewaFragment extends Fragment {

    @BindView(R.id.rvaktif)
    RecyclerView rvaktif;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private SewaViewModel mViewModel;

    LinearLayoutManager linearLayoutManager;
    private List<SewaResultItem> sewaResultItems = new ArrayList<>();
    private SewaAdapter sewaAdapter;


    public static SewaFragment newInstance() {
        return new SewaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sewa_fragment, container, false);
        Unbinder unbinder = ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SewaViewModel.class);
        // TODO: Use the ViewModel


        mViewModel.getSewa().observe(this, sewaResultItems -> {
            if (sewaResultItems != null) {
                sewaAdapter = new SewaAdapter(getContext(), sewaResultItems);
                linearLayoutManager = new LinearLayoutManager(getContext());

                rvaktif.setLayoutManager(linearLayoutManager);
                rvaktif.setAdapter(sewaAdapter);
                sewaAdapter.notifyDataSetChanged();
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

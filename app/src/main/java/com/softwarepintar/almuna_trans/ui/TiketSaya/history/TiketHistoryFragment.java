package com.softwarepintar.almuna_trans.ui.TiketSaya.history;

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

public class TiketHistoryFragment extends Fragment {

    @BindView(R.id.rvmenunggu)
    RecyclerView rvmenunggu;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private TiketHistoryViewModel mViewModel;
    LinearLayoutManager linearLayoutManager;
    private List<TiketResultItem> resultItems = new ArrayList<>();
    private TiketAdapter tiketAdapter;

    public static TiketHistoryFragment newInstance() {
        return new TiketHistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tiket_history_fragment, container, false);
        Unbinder unbinder = ButterKnife.bind(this, view);
       return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TiketHistoryViewModel.class);
        // TODO: Use the ViewModel
        mViewModel.getTiket().observe(this, tiketResultItems -> {
            if (resultItems != null) {
                tiketAdapter = new TiketAdapter(getContext(), tiketResultItems);
                linearLayoutManager = new LinearLayoutManager(getContext());

                rvmenunggu.setLayoutManager(linearLayoutManager);
                rvmenunggu.setAdapter(tiketAdapter);
                tiketAdapter.notifyDataSetChanged();
                rvmenunggu.setHasFixedSize(true);
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

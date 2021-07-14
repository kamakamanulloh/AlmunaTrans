package com.softwarepintar.almuna_trans.ui.TiketSaya.tiketbelumkomfirm;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.ui.TiketSaya.TiketAdapter;
import com.softwarepintar.almuna_trans.ui.TiketSaya.TiketResultItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TiketPendingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private TiketPendingViewModel2 mViewModel;
    @BindView(R.id.rvaktif)
    RecyclerView rvmenunggu;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.swipe_to_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayoutManager linearLayoutManager;
    private List<TiketResultItem> resultItems = new ArrayList<>();
    private TiketAdapter tiketAdapter;
    SwipeRefreshLayout refreshLayout;

    public static TiketPendingFragment newInstance() {
        return new TiketPendingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.tiket_pending_fragment, container, false);
        Unbinder unbinder = ButterKnife.bind(this, v);

        swipeRefreshLayout.setOnRefreshListener(this);


        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TiketPendingViewModel2.class);
        // TODO: Use the ViewModel
        mViewModel.getTiket().observe(this, tiketResultItems -> {
            if (resultItems != null) {

                tiketAdapter = new TiketAdapter(getContext(), tiketResultItems);
                linearLayoutManager = new LinearLayoutManager(getContext());
                tiketAdapter.notifyDataSetChanged();

                swipeRefreshLayout.setRefreshing(false);
                rvmenunggu.setLayoutManager(linearLayoutManager);
                rvmenunggu.setAdapter(tiketAdapter);
                tiketAdapter.notifyDataSetChanged();
                rvmenunggu.setHasFixedSize(true);
                showloading();
                swipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {

                        swipeRefreshLayout.setRefreshing(true);
                        tiketAdapter.notifyDataSetChanged();
//                loadData();
                    }
                });



            }
        });
    }
    private void loadData(){

        mViewModel = new ViewModelProvider(this).get(TiketPendingViewModel2.class);
        // TODO: Use the ViewModel
        mViewModel.getTiket().observe(this, tiketResultItems -> {
            if (resultItems != null) {
                tiketAdapter = new TiketAdapter(getContext(), tiketResultItems);
                linearLayoutManager = new LinearLayoutManager(getContext());
                tiketAdapter.notifyDataSetChanged();

                swipeRefreshLayout.setRefreshing(false);
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

    @Override
    public void onRefresh() {
        resultItems.clear();
        tiketAdapter.notifyDataSetChanged();
        loadData();
    }

}
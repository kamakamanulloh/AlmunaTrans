package com.softwarepintar.almuna_trans.Info;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarepintar.almuna_trans.MainActivity;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.ui.home.InfoAdapter;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class InfoFragment extends Fragment {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.rv)
    RecyclerView rv;
    private InfoViewModel mViewModel;
    private Info_Adater infoAdapter;

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.info_fragment, container, false);
        Unbinder unbinder = ButterKnife.bind(this, v);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setTitle("Info");

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
        // TODO: Use the ViewModel
        mViewModel.getResult().observe(this,infoResultItems -> {
            infoAdapter=new Info_Adater(getContext(),infoResultItems);

            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(infoAdapter);
            rv.setHasFixedSize(true);
            infoAdapter.notifyDataSetChanged();
            showLoading();
        });
    }
    private void showLoading() {
        if (false) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}

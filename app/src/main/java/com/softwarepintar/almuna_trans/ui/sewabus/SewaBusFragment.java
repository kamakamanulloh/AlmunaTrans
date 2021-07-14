package com.softwarepintar.almuna_trans.ui.sewabus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SewaBusFragment extends Fragment {

    @BindView(R.id.rvkelas)
    RecyclerView rvkelas;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private SewaBusViewModel mViewModel;
    private Kelas_Adapter kelas_adapter;
    LinearLayoutManager linearLayoutManager;
    private List<KelasResultItem> kelasResultItems = new ArrayList<>();

    public static SewaBusFragment newInstance() {
        return new SewaBusFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sewa_bus_fragment, container, false);
        Unbinder unbinder = ButterKnife.bind(this, view);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setTitle("Sewa Bus");
        ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setSubtitle("");




        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SewaBusViewModel.class);
        // TODO: Use the ViewModel

        mViewModel.getKelas().observe(this, kelasResultItems1 -> {
            if (kelasResultItems != null) {
                kelas_adapter = new Kelas_Adapter(getContext(), kelasResultItems1);
                linearLayoutManager = new LinearLayoutManager(getContext());

                rvkelas.setLayoutManager(linearLayoutManager);
                rvkelas.setAdapter(kelas_adapter);
                kelas_adapter.notifyDataSetChanged();
                rvkelas.setHasFixedSize(true);
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

package com.softwarepintar.almuna_trans.ui.kritiksaran;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.softwarepintar.almuna_trans.MainActivity;
import com.softwarepintar.almuna_trans.R;

import java.util.Objects;

public class KriktikSaranFragment extends Fragment {

    private KriktikSaranViewModel mViewModel;

    public static KriktikSaranFragment newInstance() {
        return new KriktikSaranFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.kriktik_saran_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(KriktikSaranViewModel.class);
        // TODO: Use the ViewModel
    }

}

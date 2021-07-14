package com.softwarepintar.almuna_trans.ui.TiketSaya;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.ui.TiketSaya.history.TiketHistoryFragment;
import com.softwarepintar.almuna_trans.ui.TiketSaya.sewa.SewaFragment;
import com.softwarepintar.almuna_trans.ui.TiketSaya.tiketaktif.TiketAktifFragment;
import com.softwarepintar.almuna_trans.ui.TiketSaya.tiketbelumkomfirm.TiketPendingFragment;
import com.softwarepintar.almuna_trans.ui.TiketSaya.tiketmenunggu.TiketMenungguFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TiketSayaFragment extends Fragment {


    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private TiketSayaViewModel dashboardViewModel;
    public static int int_items = 5;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(TiketSayaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tiketsaya, null);
        Unbinder unbinder = ButterKnife.bind(this, root);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Tiket Saya");
        viewPager.setAdapter(new GrafikTabAdapter(getChildFragmentManager()));


        //setup
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });


        return root;
    }

    static class GrafikTabAdapter extends FragmentPagerAdapter {
        GrafikTabAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);;
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new TiketAktifFragment();

                case 1:
                    return new TiketPendingFragment();

                case 2:
                    return new TiketMenungguFragment();

                case 3:
                    return new TiketHistoryFragment();
                case 4:
                    return new SewaFragment();




            }
            return null;
        }


        @Override
        public int getCount() {
            return int_items;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Tiket Aktif";
                case 1:
                    return "Menunggu Komfirmasi";
                case 2:
                    return "Menunggu Pembayaran";
                case 3:
                    return "History";
                case 4:
                    return "Sewa Bus";


            }
            return null;
        }


    }
}
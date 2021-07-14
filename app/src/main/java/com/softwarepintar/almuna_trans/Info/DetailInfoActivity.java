package com.softwarepintar.almuna_trans.Info;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.ui.home.InfoResultItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailInfoActivity extends AppCompatActivity {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tvjudul)
    TextView tvjudul;
    @BindView(R.id.tvsubtitle)
    TextView tvsubtitle;
    InfoResultItem infoResultItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);
        ButterKnife.bind(this);
        infoResultItems=getIntent().getParcelableExtra(Utils.EXTRA_NEWS);

        Glide.with(this)
                .load(infoResultItems.getImg())
                .into(img);
        tvjudul.setText(infoResultItems.getJudul());
        tvsubtitle.setText(infoResultItems.getKeterangan());

    }
}

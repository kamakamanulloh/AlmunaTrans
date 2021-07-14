package com.softwarepintar.almuna_trans.ui.TiketSaya.sewa;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.Guideline;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.softwarepintar.almuna_trans.R;
import com.vipulasri.ticketview.TicketView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TiketSewaActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ticketView)
    TicketView ticketView;
    @BindView(R.id.guideline)
    Guideline guideline;
    @BindView(R.id.imgqr)
    AppCompatImageView imgqr;
    @BindView(R.id.tv_kd)
    AppCompatTextView tvKd;
    @BindView(R.id.tvnmbus)
    AppCompatTextView tvnmbus;
    @BindView(R.id.tvberangkat)
    AppCompatTextView tvberangkat;
    @BindView(R.id.pemesan)
    AppCompatTextView pemesan;
    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiket_sewa);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Tiket Sewa");
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(getIntent().getStringExtra("kode"), BarcodeFormat.QR_CODE, 700, 700);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(bitMatrix);
            imgqr.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
        tvberangkat.setText("Berangkat \n"+getIntent().getStringExtra("berangkat"));
        tvKd.setText(getIntent().getStringExtra("kode"));
        tvnmbus.setText(getIntent().getStringExtra("kelas"));
        pemesan.setText(getIntent().getStringExtra("tujuan"));


    }
}

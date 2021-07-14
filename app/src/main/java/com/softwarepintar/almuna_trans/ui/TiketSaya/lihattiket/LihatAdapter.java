package com.softwarepintar.almuna_trans.ui.TiketSaya.lihattiket;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.ui.TiketSaya.PenumpangResultItem;
import com.vipulasri.ticketview.TicketView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LihatAdapter extends RecyclerView.Adapter<LihatAdapter.ViewHolder> {
    @NonNull
    private Context context;
    private List<PenumpangResultItem>penumpangResultItemList;

    public LihatAdapter(@NonNull Context context, List<PenumpangResultItem> penumpangResultItemList) {
        this.context = context;
        this.penumpangResultItemList = penumpangResultItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lihat_tiket, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PenumpangResultItem penumpangResultItem=penumpangResultItemList.get(position);
        holder.tvKd.setText("Nama \n"+penumpangResultItem.getNmPenumpang());
        holder.tvberangkat.setText("Tanggal Berangkat \n"+penumpangResultItem.getBerangkat()
                );
        holder.tvnmbus.setText(penumpangResultItem.getArmada_nama()+"\n ("+penumpangResultItem.getRuteAsal()+" - "+penumpangResultItem.getRuteTujuan()
                +")");

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(penumpangResultItem.getKd_tiket(), BarcodeFormat.QR_CODE, 800, 800);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(bitMatrix);
            holder.imgqr.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }




    }

    @Override
    public int getItemCount() {
        return penumpangResultItemList.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ticketView)
        TicketView ticketView;
        @BindView(R.id.guideline)
        Guideline guideline;
        @BindView(R.id.tvnmbus)
        AppCompatTextView tvnmbus;
        @BindView(R.id.tvberangkat)
        AppCompatTextView tvberangkat;
        @BindView(R.id.imgqr)
        AppCompatImageView imgqr;

        @BindView(R.id.tv_kd)
        AppCompatTextView tvKd;




        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

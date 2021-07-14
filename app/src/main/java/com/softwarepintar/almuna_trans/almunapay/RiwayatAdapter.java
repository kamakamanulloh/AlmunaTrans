package com.softwarepintar.almuna_trans.almunapay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarepintar.almuna_trans.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.ViewHolder> {
    @NonNull
    private Context context;
    private List<RiwayatResultItem> riwayatResultItemList;

    public RiwayatAdapter(@NonNull Context context, List<RiwayatResultItem> riwayatResultItemList) {
        this.context = context;
        this.riwayatResultItemList = riwayatResultItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hist_almunapay, parent, false);

        return new ViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RiwayatResultItem riwayatResultItem=riwayatResultItemList.get(position);
        String cara;
        String status = null;
        String jenis = null;
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        if (riwayatResultItem.getCara().equalsIgnoreCase("TF")){
             cara="Transfer";
        }
        else{
             cara="Top Up Petugas";
        }

        if (riwayatResultItem.getStatus().equalsIgnoreCase("N")){
             status="Sedang Proses";
        }
        else if (riwayatResultItem.getStatus().equalsIgnoreCase("Y")){
             status="Berhasil";
        }
        if (riwayatResultItem.getJenis().equalsIgnoreCase("BAYAR")){
            jenis="Pembayaran";
            holder.tvnominal.setText("-"+formatRupiah.format(Double.parseDouble(riwayatResultItem.getNominal())));

        }
        else if(riwayatResultItem.getJenis().equalsIgnoreCase("TOPUP")){
            jenis="Top Up";
            holder.tvnominal.setText(formatRupiah.format(Double.parseDouble(riwayatResultItem.getNominal())));
        }

        holder.tvcara.setText(cara);
        holder.tvjenis.setText(jenis);
        holder.tvkode.setText("#"+riwayatResultItem.getKode());


        holder.tvtanggal.setText(riwayatResultItem.getCreatedAt()+" ("+status+")");

    }

    @Override
    public int getItemCount() {
        return riwayatResultItemList.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvjenis)
        TextView tvjenis;
        @BindView(R.id.tvcara)
        TextView tvcara;
        @BindView(R.id.tvnominal)
        TextView tvnominal;
        @BindView(R.id.tvkode)
        TextView tvkode;
        @BindView(R.id.tvtanggal)
        TextView tvtanggal;
        @BindView(R.id.garis3)
        View garis3;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

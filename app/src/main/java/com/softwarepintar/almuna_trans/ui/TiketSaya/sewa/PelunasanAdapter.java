package com.softwarepintar.almuna_trans.ui.TiketSaya.sewa;

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

public class PelunasanAdapter extends RecyclerView.Adapter<PelunasanAdapter.ViewHolder> {
    @NonNull
    private Context context;
    private List<PelunasanResultItem> pelunasanResultItems;

    public PelunasanAdapter(@NonNull Context context, List<PelunasanResultItem> pelunasanResultItems) {
        this.context = context;
        this.pelunasanResultItems = pelunasanResultItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_penumpang, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PelunasanResultItem pelunasanResultItem=pelunasanResultItems.get(position);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        String status;
        if (pelunasanResultItem.getJenis().equalsIgnoreCase("BELUM")){
            status="Belum Bayar / DP";
        }
        else if (pelunasanResultItem.getJenis().equalsIgnoreCase("PENDING")){
            status="Menunggu Konfirmasi";
        }
        else if (pelunasanResultItem.getJenis().equalsIgnoreCase("DP")){
            status="DP";
        }
        else {
            status="Pelunasan";
        }


        holder.tvnama.setText("Nominal : "+formatRupiah.format(Double.parseDouble(pelunasanResultItem.getNominal()))+"\n"
        +status);
        holder.tvnoid.setText("Total "+formatRupiah.format(Double.parseDouble(pelunasanResultItem.getTotalBayar()))
        +"\n"+pelunasanResultItem.getCreatedAt());

    }

    @Override
    public int getItemCount() {
        return pelunasanResultItems.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvnama)
        TextView tvnama;
        @BindView(R.id.tvnoid)
        TextView tvnoid;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

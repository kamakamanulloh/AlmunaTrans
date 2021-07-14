package com.softwarepintar.almuna_trans.ui.sewabus.paketsewa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.ui.sewabus.FormSewaActivity;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaketSewaAdapter extends RecyclerView.Adapter<PaketSewaAdapter.ViewHolder> {
    @NonNull
    private Context context;
    private List<PaketSewaResultItem> paketSewaResultItems;

    public PaketSewaAdapter(@NonNull Context context, List<PaketSewaResultItem> paketSewaResultItems) {
        this.context = context;
        this.paketSewaResultItems = paketSewaResultItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_paket_sewa, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PaketSewaResultItem paketSewaResultItem=paketSewaResultItems.get(position);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        holder.tvHarga.setText(formatRupiah.format(Double.valueOf(paketSewaResultItem.getHarga())));
        holder.tvJadwal.setText("Tujuan "+paketSewaResultItem.getTujuan()+" ("+paketSewaResultItem.getDurasi()+" )");
        Glide.with(context)
                .load(paketSewaResultItem.getGambar())
                .error(R.mipmap.ic_launcher)
                .into(holder.img);
        holder.itemView.setOnClickListener(v -> {

            Intent intent=new Intent(holder.itemView.getContext(), FormSewaActivity.class);
            PaketSewaResultItem paketSewaResultItem1=new PaketSewaResultItem();
            paketSewaResultItem1.setDurasi(paketSewaResultItem.getDurasi());
            paketSewaResultItem1.setDurasiTime(paketSewaResultItem.getDurasiTime());
            paketSewaResultItem1.setHarga(paketSewaResultItem.getHarga());
            paketSewaResultItem1.setId(paketSewaResultItem.getId());
            paketSewaResultItem1.setKelasId(paketSewaResultItem.getKelasId());
            paketSewaResultItem1.setTujuan(paketSewaResultItem.getTujuan());

            intent.putExtra("kapasitas",PaketSewaActivity.kapasitas);
            intent.putExtra("kelas",PaketSewaActivity.kelas);
            intent.putExtra("user_id", Utils.user_id);
            intent.putExtra(Utils.EXTRA_NEWS,paketSewaResultItem1);
            holder.itemView.getContext().startActivity(intent);


        });
    }

    @Override
    public int getItemCount() {
        return paketSewaResultItems.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_jadwal)
        TextView tvJadwal;
        @BindView(R.id.tv_harga)
        TextView tvHarga;
        @BindView(R.id.surat)
        CardView surat;
        @BindView(R.id.img)
        ImageView img;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

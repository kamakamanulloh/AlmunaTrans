package com.softwarepintar.almuna_trans.ui.TiketSaya.sewa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SewaAdapter extends RecyclerView.Adapter<SewaAdapter.ViewHolder> {
    @NonNull
    private Context context;
    private List<SewaResultItem>sewaResultItems;

    public SewaAdapter(@NonNull Context context, List<SewaResultItem> sewaResultItems) {
        this.context = context;
        this.sewaResultItems = sewaResultItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tiket, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SewaResultItem sewaResultItem=sewaResultItems.get(position);
        holder.tvasal.setText(sewaResultItem.getTujuan()+" "+sewaResultItem.getDurasi());
        holder.tvkd.setText("Kode "+sewaResultItem.getKode());
        if (sewaResultItem.getStatus().equalsIgnoreCase("BELUM")){
            holder.tvstatus.setText("Belum Bayar");
        }
        else if (sewaResultItem.getStatus().equalsIgnoreCase("DP")){
            holder.tvstatus.setText("Sudah DP");
        }
        else if (sewaResultItem.getStatus().equalsIgnoreCase("LUNAS")){
            holder.tvstatus.setText("Sudah Lunas");
        }
        else if (sewaResultItem.getStatus().equalsIgnoreCase("PENDING")){
            holder.tvstatus.setText("Menunggu Konfirmasi");
        }
        else if (sewaResultItem.getStatus().equalsIgnoreCase("SELESAI")){
            holder.tvstatus.setText("Perjalanan Selesai");
        }


        holder.tvtujuan.setText("Berangkat "+sewaResultItem.getBerangkat());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SewaResultItem sewaResultItem1=new SewaResultItem();
                Intent intent;

                    intent=new Intent(holder.itemView.getContext(), DetaillSewaActivity.class);
                    sewaResultItem1.setMemberId(sewaResultItem.getMemberId());
                    sewaResultItem1.setKode(sewaResultItem.getKode());
                    sewaResultItem1.setTujuan(sewaResultItem.getTujuan());
                    sewaResultItem1.setDurasi(sewaResultItem.getDurasi());
                    sewaResultItem1.setBerangkat(sewaResultItem.getBerangkat());
                    sewaResultItem1.setKembali(sewaResultItem.getKembali());
                    sewaResultItem1.setPenjemputan(sewaResultItem.getPenjemputan());
                    sewaResultItem1.setNama_kelas(sewaResultItem.getNama_kelas());
                    sewaResultItem1.setJumlah_bus(sewaResultItem.getJumlah_bus());
                    sewaResultItem1.setTotal(sewaResultItem.getTotal());
                    sewaResultItem1.setStatus(sewaResultItem.getStatus());
                    sewaResultItem1.setBatasBayar(sewaResultItem.getBatasBayar());
                    intent.putExtra(Utils.EXTRA_NEWS,sewaResultItem1);
                    intent.putExtra("status",holder.tvstatus.getText().toString());


                    holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return sewaResultItems.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvkd)
        TextView tvkd;
        @BindView(R.id.tvstatus)
        TextView tvstatus;
        @BindView(R.id.garis)
        View garis;
        @BindView(R.id.tvasal)
        TextView tvasal;
        @BindView(R.id.tvtujuan)
        TextView tvtujuan;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

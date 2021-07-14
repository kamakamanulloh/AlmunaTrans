package com.softwarepintar.almuna_trans.ui.TiketSaya;

import android.annotation.SuppressLint;
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
import com.softwarepintar.almuna_trans.ui.TiketSaya.tiketaktif.DetailTiketActivity;
import com.softwarepintar.almuna_trans.ui.TiketSaya.tiketbelumkomfirm.DetailTiketBelumKomfirmActivity;
import com.softwarepintar.almuna_trans.ui.TiketSaya.tiketmenunggu.DetailTiketMenungguActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TiketAdapter extends RecyclerView.Adapter<TiketAdapter.ViewHolder> {
    @NonNull
    private Context context;
    private List<TiketResultItem> tiketResultItems;

    public TiketAdapter(@NonNull Context context, List<TiketResultItem> tiketResultItems) {
        this.context = context;
        this.tiketResultItems = tiketResultItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tiket, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       final TiketResultItem tiketResultItem=tiketResultItems.get(position);
        holder.tvkd.setText("Kode Pembayaran "+tiketResultItem.getKodeTiket());
        holder.tvasal.setText(tiketResultItem.getRuteAsal()+"\n"+tiketResultItem.getBerangkat());
        holder.tvtujuan.setText(tiketResultItem.getRuteTujuan()+"\n"+tiketResultItem.getEstimasi()+" Jam");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TiketResultItem tiketResultItem1=new TiketResultItem();
                Intent intent;

                if (tiketResultItem.getStatus().equals("S") || tiketResultItem.getStatus().equals("F") ){

                    intent = new Intent(holder.itemView.getContext(), DetailTiketActivity.class);
                    tiketResultItem1.setKodeTiket(tiketResultItem.getKodeTiket());
                    tiketResultItem1.setBerangkat(tiketResultItem.getBerangkat());
                    tiketResultItem1.setRuteAsal(tiketResultItem.getRuteAsal());
                    tiketResultItem1.setRuteTujuan(tiketResultItem.getRuteTujuan());
                    tiketResultItem1.setEstimasi(tiketResultItem.getEstimasi());
                    tiketResultItem1.setStatus(tiketResultItem.getStatus());
                    tiketResultItem1.setArmada_nama(tiketResultItem.getArmada_nama());
                    tiketResultItem1.setKelas(tiketResultItem.getKelas());
                    tiketResultItem1.setNominal(tiketResultItem.getNominal());
                    tiketResultItem1.setJumlah(tiketResultItem.getJumlah());

                    intent.putExtra(Utils.EXTRA_NEWS,tiketResultItem1);
                    holder.itemView.getContext().startActivity(intent);
                }
                else if (tiketResultItem.getStatus().equalsIgnoreCase("P")){

                    intent = new Intent(holder.itemView.getContext(), DetailTiketBelumKomfirmActivity.class);
                    tiketResultItem1.setKodeTiket(tiketResultItem.getKodeTiket());
                    tiketResultItem1.setBerangkat(tiketResultItem.getBerangkat());
                    tiketResultItem1.setRuteAsal(tiketResultItem.getRuteAsal());
                    tiketResultItem1.setRuteTujuan(tiketResultItem.getRuteTujuan());
                    tiketResultItem1.setEstimasi(tiketResultItem.getEstimasi());
                    tiketResultItem1.setStatus(tiketResultItem.getStatus());
                    tiketResultItem1.setArmada_nama(tiketResultItem.getArmada_nama());
                    tiketResultItem1.setKelas(tiketResultItem.getKelas());
                    tiketResultItem1.setNominal(tiketResultItem.getNominal());
                    tiketResultItem1.setJumlah(tiketResultItem.getJumlah());
                    tiketResultItem1.setBatas_bayar(tiketResultItem.getBatas_bayar());
                    intent.putExtra(Utils.EXTRA_NEWS,tiketResultItem1);
                    holder.itemView.getContext().startActivity(intent);
                }
                else if (tiketResultItem.getStatus().equalsIgnoreCase("N")){

                    intent = new Intent(holder.itemView.getContext(), DetailTiketMenungguActivity.class);
                    tiketResultItem1.setKodeTiket(tiketResultItem.getKodeTiket());
                    tiketResultItem1.setBerangkat(tiketResultItem.getBerangkat());
                    tiketResultItem1.setRuteAsal(tiketResultItem.getRuteAsal());
                    tiketResultItem1.setRuteTujuan(tiketResultItem.getRuteTujuan());
                    tiketResultItem1.setEstimasi(tiketResultItem.getEstimasi());
                    tiketResultItem1.setStatus(tiketResultItem.getStatus());
                    tiketResultItem1.setArmada_nama(tiketResultItem.getArmada_nama());
                    tiketResultItem1.setKelas(tiketResultItem.getKelas());
                    tiketResultItem1.setNominal(tiketResultItem.getNominal());
                    tiketResultItem1.setJumlah(tiketResultItem.getJumlah());
                    tiketResultItem1.setBatas_bayar(tiketResultItem.getBatas_bayar());
                    intent.putExtra(Utils.EXTRA_NEWS,tiketResultItem1);
                    holder.itemView.getContext().startActivity(intent);

                }





            }
        });


        if (tiketResultItem.getStatus().equals("S")){
            holder.tvstatus.setText("Aktif");

        }
        else if (tiketResultItem.getStatus().equals("P")){
            holder.tvstatus.setText("Menunggu Konfirmasi Admin");
        }
        else if (tiketResultItem.getStatus().equals("N")){
            holder.tvstatus.setText("Menunggu Pembayaran");
        }

        else  if (tiketResultItem.getStatus().equals("F")){
            holder.tvstatus.setText("Selesai");
        }



    }

    @Override
    public int getItemCount() {
        return tiketResultItems.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvkd)
        TextView tvkd;
        @BindView(R.id.tvstatus)
        TextView tvstatus;
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

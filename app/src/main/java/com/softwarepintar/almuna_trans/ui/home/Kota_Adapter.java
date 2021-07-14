package com.softwarepintar.almuna_trans.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.Jadwal.Jadwal_Adapter;
import com.softwarepintar.almuna_trans.Jadwal.Jadwal_ResultItem;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.kota.ListKotaFragment;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Kota_Adapter extends RecyclerView.Adapter<Kota_Adapter.ViewHolder> {


    @NonNull

    private Context context;
    private List<ResultasalItem> resultItem_kotas;

    public Kota_Adapter(@NonNull Context context, List<ResultasalItem> resultItem_kotas) {
        this.context = context;
        this.resultItem_kotas = resultItem_kotas;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kota, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Kota_Adapter.ViewHolder holder, int position) {
        holder.tvKota.setText(resultItem_kotas.get(position).getRuteMulai().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Objects.equals(Utils.jenis, "tujuan")){



                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return resultItem_kotas.size();
    }


    static
    class ViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.tv_kota)
        TextView tvKota;
        public ViewHolder(View view) {

            super(view);
            ButterKnife.bind(this,view);

        }
    }
}

package com.softwarepintar.almuna_trans.ui.sewabus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.ui.sewabus.paketsewa.PaketSewaActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Kelas_Adapter extends RecyclerView.Adapter<Kelas_Adapter.ViewHolder> {
    @NonNull
    private Context context;
    private List<KelasResultItem> kelasResultItems;

    public Kelas_Adapter(@NonNull Context context, List<KelasResultItem> kelasResultItems) {
        this.context = context;
        this.kelasResultItems = kelasResultItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kelas, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KelasResultItem kelasResultItem=kelasResultItems.get(position);
        holder.tvkelas.setText(kelasResultItem.getNama()+" ("+kelasResultItem.getKapasitas()+" Kursi )");
        Glide.with(context)
                .load(kelasResultItem.getGambar())
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KelasResultItem kelasResultItem1=new KelasResultItem();
                Intent intent=new Intent(holder.itemView.getContext(), PaketSewaActivity.class);
                kelasResultItem1.setId(kelasResultItem.getId());
                kelasResultItem1.setGambar(kelasResultItem.getGambar());
                kelasResultItem1.setNama(kelasResultItem.getNama());
                kelasResultItem1.setKapasitas(kelasResultItem.getKapasitas());
                intent.putExtra(Utils.EXTRA_NEWS,kelasResultItem1);
                holder.itemView.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return kelasResultItems.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.framelayout)
        FrameLayout framelayout;
        @BindView(R.id.tvkelas)
        TextView tvkelas;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

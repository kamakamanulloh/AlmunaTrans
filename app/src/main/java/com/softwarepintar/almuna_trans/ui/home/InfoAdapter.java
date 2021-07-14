package com.softwarepintar.almuna_trans.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.Info.DetailInfoActivity;
import com.softwarepintar.almuna_trans.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {
    @NonNull
    private Context context;
    private List<InfoResultItem>infoResultItems;

    public InfoAdapter(@NonNull Context context, List<InfoResultItem> infoResultItems) {
        this.context = context;
        this.infoResultItems = infoResultItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_info, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InfoResultItem infoResultItem=infoResultItems.get(position);
        holder.tvJudul.setText(infoResultItem.getJudul());
        Glide.with(context)
                .load(infoResultItem.getImg())
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoResultItem infoResultItem1=new InfoResultItem();
                Intent intent=new Intent(holder.itemView.getContext(), DetailInfoActivity.class);
                infoResultItem1.setId(infoResultItem.getId());
                infoResultItem1.setCreatedAt(infoResultItem.getCreatedAt());
                infoResultItem1.setEndAt(infoResultItem.getEndAt());
                infoResultItem1.setImg(infoResultItem.getImg());
                infoResultItem1.setJudul(infoResultItem.getJudul());
                infoResultItem1.setKeterangan(infoResultItem.getKeterangan());
                infoResultItem1.setStatus(infoResultItem.getStatus());
                intent.putExtra(Utils.EXTRA_NEWS,infoResultItem1);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return infoResultItems.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_judul)
        TextView tvJudul;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

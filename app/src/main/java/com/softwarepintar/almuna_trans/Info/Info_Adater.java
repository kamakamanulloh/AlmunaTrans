package com.softwarepintar.almuna_trans.Info;

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
import com.softwarepintar.almuna_trans.DetailBus.DetailBusActivity;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.ui.home.InfoResultItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Info_Adater extends RecyclerView.Adapter<Info_Adater.ViewHolder> {
    @NonNull
    private Context context;
    private List<InfoResultItem> items;

    public Info_Adater(@NonNull Context context, List<InfoResultItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_all_info, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        InfoResultItem infoResultItem=items.get(i);
        Glide.with(context)
                .load(infoResultItem.getImg())
                .into(viewHolder.img);
        viewHolder.title.setText(infoResultItem.getJudul());
        viewHolder.date.setText("Berlaku Sampai "+infoResultItem.getEndAt());

        viewHolder.subtittle.setText(infoResultItem.getKeterangan());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoResultItem infoResultItem1=new InfoResultItem();
                Intent intent=new Intent(viewHolder.itemView.getContext(), DetailInfoActivity.class);
                infoResultItem1.setId(infoResultItem.getId());
                infoResultItem1.setCreatedAt(infoResultItem.getCreatedAt());
                infoResultItem1.setEndAt(infoResultItem.getEndAt());
                infoResultItem1.setImg(infoResultItem.getImg());
                infoResultItem1.setJudul(infoResultItem.getJudul());
                infoResultItem1.setKeterangan(infoResultItem.getKeterangan());
                infoResultItem1.setStatus(infoResultItem.getStatus());
                intent.putExtra(Utils.EXTRA_NEWS,infoResultItem1);
                viewHolder.itemView.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.framelayout)
        FrameLayout framelayout;
        @BindView(R.id.subtittle)
        TextView subtittle;

        ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}

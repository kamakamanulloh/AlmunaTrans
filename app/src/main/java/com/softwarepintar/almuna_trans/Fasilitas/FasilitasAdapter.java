package com.softwarepintar.almuna_trans.Fasilitas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softwarepintar.almuna_trans.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FasilitasAdapter extends RecyclerView.Adapter<FasilitasAdapter.ViewHolder> {
    @NonNull
    private Context context;
    private List<FasilitasResultItem> fasilitasResultItems;

    public FasilitasAdapter(@NonNull Context context, List<FasilitasResultItem> fasilitasResultItems) {
        this.context = context;
        this.fasilitasResultItems = fasilitasResultItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fasilitas_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FasilitasResultItem fasilitasResultItem=fasilitasResultItems.get(position);
        Glide.with(context)
                .load(fasilitasResultItem.getGambar())
                .into(holder.img);
        holder.tvFasilitas.setText(fasilitasResultItem.getNama());



    }

    @Override
    public int getItemCount() {
        return fasilitasResultItems.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_fasilitas)
        TextView tvFasilitas;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

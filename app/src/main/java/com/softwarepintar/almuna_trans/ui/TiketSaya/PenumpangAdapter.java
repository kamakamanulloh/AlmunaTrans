package com.softwarepintar.almuna_trans.ui.TiketSaya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarepintar.almuna_trans.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PenumpangAdapter extends RecyclerView.Adapter<PenumpangAdapter.ViewHolder> {
    @NonNull
    private Context context;
    private List<PenumpangResultItem>penumpangResultItemList;

    public PenumpangAdapter(@NonNull Context context, List<PenumpangResultItem> penumpangResultItemList) {
        this.context = context;
        this.penumpangResultItemList = penumpangResultItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_penumpang, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PenumpangResultItem penumpangResultItem=penumpangResultItemList.get(position);
        holder.tvnama.setText(penumpangResultItem.getNmPenumpang());
        holder.tvnoid.setText(penumpangResultItem.getNoId());

    }

    @Override
    public int getItemCount() {
        return penumpangResultItemList.size();
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

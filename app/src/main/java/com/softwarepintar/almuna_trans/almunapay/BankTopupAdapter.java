package com.softwarepintar.almuna_trans.almunapay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.payment.BankResultItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BankTopupAdapter extends RecyclerView.Adapter<BankTopupAdapter.ViewHolder> {
    @NonNull
    private Context context;
    private List<BankResultItem> bankResultItems;

    private static int nominal;

    private static final String ALLOWED_CHARACTERS = "0123456789";
    public BankTopupAdapter(@NonNull Context context, List<BankResultItem> bankResultItems) {
        this.context = context;
        this.bankResultItems = bankResultItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_banktopup, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BankResultItem bankResultItem=bankResultItems.get(position);
        holder.tvbank.setText(bankResultItem.getNama());
        Glide.with(context)
                .load(bankResultItem.getImg())
                .into(holder.ivbank);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.itemView.getContext(), PetunjukActivity.class);
                intent.putExtra("nama", bankResultItem.getNama());
                intent.putExtra("bank", bankResultItem.getBank());
                intent.putExtra("rekening", bankResultItem.getNomor());
                intent.putExtra("bankid", bankResultItem.getId());
                holder.itemView.getContext().startActivity(intent);

            }

        });

    }


    @Override
    public int getItemCount() {
        return bankResultItems.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivbank)
        ImageView ivbank;
        @BindView(R.id.tvbank)
        TextView tvbank;
        @BindView(R.id.garis3)
        View garis3;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

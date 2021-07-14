package com.softwarepintar.almuna_trans.seats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.softwarepintar.almuna_trans.API.Utils.seat_selected;

class SeatAdapterD extends RecyclerView.Adapter<SeatAdapterD.ViewHolder> {
    private Context context;
    private List<ResultDItem>resultDItemList;

    public SeatAdapterD(Context context, List<ResultDItem> resultDItemList) {
        this.context = context;
        this.resultDItemList = resultDItemList;
    }


    @NonNull
    @NotNull
    @Override
    public SeatAdapterD.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kursi_d,parent,false);
        return new SeatAdapterD.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SeatAdapterD.ViewHolder holder, int position) {
        ResultDItem resultDItem=resultDItemList.get(position);
        if (resultDItem.getStatus().equalsIgnoreCase("Y")){
            holder.img_seatD.setBackgroundResource(R.drawable.seat_normal_booked);
        }
        holder.txt_d.setText(resultDItem.getSeatNama());
        holder.line_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.seat_selected2.contains(resultDItem.getSeatNama())){
                    Utils.seat_selected2.remove(resultDItem.getSeatNama());
                    holder.img_seatD.setBackgroundResource(R.drawable.seat_normal);

                }
                else{
//                    Toast.makeText(context,"show "+String.valueOf(Utils.seat_selected.size()), Toast.LENGTH_LONG).show();

                    if (Utils.seat_selected.size() <= Integer.parseInt(PilihKursiActivity.jumlah)){

                        Utils.seat_selected.add(resultDItem.getSeatNama());
                        holder.img_seatD.setBackgroundResource(R.drawable.seat_normal_selected);
                        Toast.makeText(context,String.valueOf(Utils.seat_selected),Toast.LENGTH_LONG).show();
                    }
                    else if (Utils.seat_selected.size() > Integer.parseInt(PilihKursiActivity.jumlah)){
                        Toast.makeText(context,"lebih ", Toast.LENGTH_LONG).show();

                    }

                }

                for (int x=0;x<Utils.seat_selected.toArray().length;x++){
                    Utils.tempString = Utils.tempString + "," + Utils.seat_selected.get(x);


                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultDItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_seatD)
        ImageView img_seatD;
        @BindView(R.id.img_seat_selectedD)
        ImageView img_seat_selectedD;
        @BindView(R.id.txt_d)
        TextView txt_d;
        @BindView(R.id.line_d)
        RelativeLayout line_d;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}

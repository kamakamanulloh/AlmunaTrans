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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.softwarepintar.almuna_trans.API.Utils.seat_selected;
import static com.softwarepintar.almuna_trans.API.Utils.tempString;

class SeatAdapterB extends RecyclerView.Adapter<SeatAdapterB.ViewHolder>{
    private Context context;
    private List<ResultBItem> resultBItemList;


    public SeatAdapterB(Context context, List<ResultBItem> resultBItemList) {
        this.context = context;
        this.resultBItemList = resultBItemList;
    }

    @NonNull
    @NotNull
    @Override
    public SeatAdapterB.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kursi_b,parent,false);
        return new SeatAdapterB.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SeatAdapterB.ViewHolder holder, int position) {
        ResultBItem resultBItem =resultBItemList.get(position);
        String kursi=resultBItem.getSeatNama();


        if (resultBItem.getStatus().equalsIgnoreCase("Y")){
            holder.img_seatB.setBackgroundResource(R.drawable.seat_normal_booked);
            holder.img_seatB.setEnabled(false);
            holder.img_seatB.setClickable(false);
        }
        holder.txt_b.setText(resultBItem.getSeatNama());

        holder.lineb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.seat_selected.contains(resultBItem.getSeatNama())){
                    Utils.seat_selected.remove(resultBItem.getSeatNama());
                    holder.img_seatB.setBackgroundResource(R.drawable.seat_normal);

                }
                else{
//                    Toast.makeText(context,"show "+String.valueOf(Utils.seat_selected.size()), Toast.LENGTH_LONG).show();

                    if (Utils.seat_selected.size() <= Integer.parseInt(PilihKursiActivity.jumlah)){

                        Utils.seat_selected.add(resultBItem.getSeatNama());
                        Toast.makeText(context,String.valueOf(seat_selected),Toast.LENGTH_LONG).show();
                        holder.img_seatB.setBackgroundResource(R.drawable.seat_normal_selected);

                    }
                    else if (Utils.seat_selected.size() > Integer.parseInt(Utils.jumlah)){
                        Toast.makeText(context,"lebih ", Toast.LENGTH_LONG).show();

                    }

                }

                for (int x=0;x<Utils.seat_selected.toArray().length;x++){
                    tempString = tempString + "," + Utils.seat_selected.get(x);


                }





            }
        });





    }

    @Override
    public int getItemCount() {

        return resultBItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_seatB)
        ImageView img_seatB;
        @BindView(R.id.img_seat_selectedB)
        ImageView img_seat_selectedB;
        @BindView(R.id.txt_b)
        TextView txt_b;
        @BindView(R.id.lineb)
        RelativeLayout lineb;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


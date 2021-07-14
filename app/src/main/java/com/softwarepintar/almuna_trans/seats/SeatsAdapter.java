package com.softwarepintar.almuna_trans.seats;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.softwarepintar.almuna_trans.API.Utils.seat_selected;

class SeatsAdapter extends RecyclerView.Adapter<SeatsAdapter.ViewHolder> {
    private Context context;
    private List<ResultAItem>resultAItemList;





    public SeatsAdapter(Context context, List<ResultAItem> resultAItemList) {
        this.context = context;
        this.resultAItemList = resultAItemList;
    }

    @NonNull

    @Override
    public SeatsAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kursi_a,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SeatsAdapter.ViewHolder holder, int position) {
        ResultAItem resultAItem = resultAItemList.get(position);
        if (resultAItem.getStatus().equalsIgnoreCase("Y")) {
            holder.img_seatA.setBackgroundResource(R.drawable.seat_normal_booked);
        }
        holder.txtA.setText(resultAItem.getSeatNama());

        holder.lineA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.seat_selected.contains(resultAItem.getSeatNama())){
                    Utils.seat_selected.remove(resultAItem.getSeatNama());
                    holder.img_seatA.setBackgroundResource(R.drawable.seat_normal);

                }
                else{
//                    Toast.makeText(context,"show "+String.valueOf(PilihKursiActivity.seat_selected.size()), Toast.LENGTH_LONG).show();

                    if (Utils.seat_selected.size() <= Integer.parseInt(PilihKursiActivity.jumlah)){

                        Utils.seat_selected.add(resultAItem.getSeatNama());
                        holder.img_seatA.setBackgroundResource(R.drawable.seat_normal_selected);
                        Toast.makeText(context,String.valueOf(seat_selected),Toast.LENGTH_LONG).show();
                    }
                    else if (Utils.seat_selected.size() > Integer.parseInt(PilihKursiActivity.jumlah)){
                        Toast.makeText(context,"lebih ", Toast.LENGTH_LONG).show();

                    }

                }

                for (int x=0;x<Utils.seat_selected.toArray().length;x++){
                    Utils.tempString = Utils.tempString + "," + Utils.seat_selected.get(x);


                }
//                Toast.makeText(context,Utils.tempString, Toast.LENGTH_LONG).show();



            }
        });


    }
    @Override
    public int getItemCount() {

        return resultAItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_seatA)
        ImageView img_seatA;
        @BindView(R.id.img_seat_selectedA)
        ImageView img_seat_selectedA;
        @BindView(R.id.txt_a)
        TextView txtA;
        @BindView(R.id.line_a)
        RelativeLayout lineA;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

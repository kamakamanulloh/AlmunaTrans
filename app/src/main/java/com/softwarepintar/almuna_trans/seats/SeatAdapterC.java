package com.softwarepintar.almuna_trans.seats;

import android.content.Context;
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

import com.softwarepintar.almuna_trans.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.softwarepintar.almuna_trans.API.Utils;

import static com.softwarepintar.almuna_trans.API.Utils.seat_selected;

class SeatAdapterC extends RecyclerView.Adapter<SeatAdapterC.ViewHolder> {

    private Context context;
    private List<ResultCItem> resultCItems;

    public SeatAdapterC(Context context, List<ResultCItem> resultCItems) {
        this.context = context;
        this.resultCItems = resultCItems;
    }

    @NotNull
    @Override
    public SeatAdapterC.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kursi_c,parent,false);
        return new SeatAdapterC.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SeatAdapterC.ViewHolder holder, int position) {
        ResultCItem resultCItem=resultCItems.get(position);
        if (resultCItem.getStatus().equalsIgnoreCase("Y")){
            holder.img_seatC.setBackgroundResource(R.drawable.seat_normal_booked);
        }
        holder.txt_c.setText(resultCItem.getSeatNama());


//                Toast.makeText(context,tempString, Toast.LENGTH_LONG).show();

        holder.line_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.seat_selected.contains(resultCItem.getSeatNama())){
                    Utils.seat_selected.remove(resultCItem.getSeatNama());
                    holder.img_seatC.setBackgroundResource(R.drawable.seat_normal);

                }
                else{
//                    Toast.makeText(context,"show "+String.valueOf(Utils.seat_selected.size()), Toast.LENGTH_LONG).show();

                    if (Utils.seat_selected.size() <= Integer.parseInt(PilihKursiActivity.jumlah)){

                        Utils.seat_selected.add(resultCItem.getSeatNama());
                        holder.img_seatC.setBackgroundResource(R.drawable.seat_normal_selected);
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
        return resultCItems.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_seatC)
        ImageView img_seatC;
        @BindView(R.id.img_seat_selectedC)
        ImageView img_seat_selectedC;
        @BindView(R.id.txt_c)
        TextView txt_c;
        @BindView(R.id.line_c)
        RelativeLayout line_c;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

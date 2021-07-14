package com.softwarepintar.almuna_trans.Jadwal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.DetailBus.DetailBusActivity;
import com.softwarepintar.almuna_trans.MainActivity;
import com.softwarepintar.almuna_trans.R;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Jadwal_Adapter extends RecyclerView.Adapter<Jadwal_Adapter.ViewHolder> {


    @NonNull
    private Context context;
    private List<Jadwal_ResultItem> jadwal_resultItems;
    public static Date date;
    private static final Bundle mBundle = new Bundle();
    public Jadwal_Adapter(@NonNull Context context, List<Jadwal_ResultItem> jadwal_resultItems) {
        this.context = context;
        this.jadwal_resultItems = jadwal_resultItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_jadwal, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Jadwal_ResultItem jadwal_resultItem = jadwal_resultItems.get(position);

        int durasi= Integer.parseInt(jadwal_resultItem.getEstimasi());

        if (jadwal_resultItem.getStatus().equalsIgnoreCase("Y")){
            holder.cardView.setBackgroundResource(R.color.disable);
            holder.relativeLayout.setBackgroundResource(R.color.disable);


        }
        if (jadwal_resultItem.getSisa()==0){
            holder.cardView.setBackgroundColor(R.color.disable);
            holder.tvTersedia.setText("Penuh");
            holder.tvTersedia.setTextColor(R.color.red);
        }

//
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//
//        Date d = null;
//        try {
//            d = df.parse(jadwal_resultItem.getJadwalTanggal()+" "+jadwal_resultItem.getMulaiWaktu());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Calendar gc = Calendar.getInstance();
//        gc.setTime(d);
//        gc.add(Calendar.HOUR, durasi);
//        gc.get(Calendar.HOUR_OF_DAY);
//        Date d2 = gc.getTime();




        holder.tvWaktu.setText(jadwal_resultItem.getMulaiWaktu()+" s/d "+jadwal_resultItem.getMulai_sampai());


//        System.out.println("Time here "+calendar.getTime());
        holder.tvArmada.setText(jadwal_resultItem.getArmadaNama());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        holder.tvHarga.setText(formatRupiah.format(Double.parseDouble(jadwal_resultItem.getHarga())));
        holder.tvKelas.setText(jadwal_resultItem.getKelas_nama());
        holder.tvTersedia.setText(jadwal_resultItem.getSisa() + " Tersedia");
        holder.tvRute.setText(jadwal_resultItem.getMulai() + " - " + jadwal_resultItem.getAkhir());

        holder.tvDurasi.setText("durasi : "+jadwal_resultItem.getEstimasi()+" jam");


        holder.btnLanjut.setOnClickListener(v -> {
            Jadwal_ResultItem jadwalResultItem=new Jadwal_ResultItem();
            if (JadwalFragment.is_pulangpergi.equalsIgnoreCase("y") && jadwal_resultItem.getStatus().equalsIgnoreCase("N")&&jadwal_resultItem.getSisa()>0 ){

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new JadwalKembaliFragment();
                mBundle.putString("user_id", Utils.user_id);
                mBundle.putString("asal", JadwalFragment.asal);
                mBundle.putString("tujuan", JadwalFragment.tujuan);
                mBundle.putString("tanggal", JadwalFragment.tanggal);
                mBundle.putString("tanggal_view", JadwalFragment.tanggalView);
                mBundle.putString("penumpang", JadwalFragment.jml_penumpang);
                mBundle.putString("is_pulangpergi", "y");
                mBundle.putString("agen_asal", JadwalFragment.agen_asal);
                mBundle.putString("agen_tujuan", JadwalFragment.agen_tujuan);
                mBundle.putString("bayi", JadwalFragment.bayi);
                mBundle.putString("tanggal_pergi", JadwalFragment.tanggal_pergi);
                mBundle.putString("tanggal_view_pergi", JadwalFragment.tanggal_view_pergi);

                mBundle.putString("tujuan_berangkat", jadwal_resultItem.getAkhir());
                mBundle.putString("estimasi_berangkat", jadwal_resultItem.getEstimasi());
                mBundle.putString("jam_berangkat", jadwal_resultItem.getMulaiWaktu());
                mBundle.putString("harga_berangkat", jadwal_resultItem.getHarga());
                mBundle.putString("armada_nama_berangkat", jadwal_resultItem.getArmadaNama());
                mBundle.putString("jadwal_id_berangkat", jadwal_resultItem.getJadwalId());
                mBundle.putString("kelas_nama_berangkat", jadwal_resultItem.getKelas_nama());
                mBundle.putString("asal_berangkat", jadwal_resultItem.getMulai());
                mBundle.putString("sisa_berangkat", Integer.toString(jadwal_resultItem.getSisa()));
                mBundle.putString("tipe_seat_berangkat", jadwal_resultItem.getTipe_seat());
                mBundle.putString("kapasitas_berangkat", jadwal_resultItem.getKapasitas());
                mBundle.putString("armada_id_berangkat", jadwal_resultItem.getArmada_id());
                mBundle.putString("kelas_id_berangkat", jadwal_resultItem.getKelas_id());
                mBundle.putString("tipe_seat_berangkat", jadwal_resultItem.getTipe_seat());
                mBundle.putString("kapasitas_berangkat", jadwal_resultItem.getKapasitas());
                mBundle.putString("jadwal_id_berangkat", jadwal_resultItem.getJadwalId());

//                mBundle.putString(Utils.EXTRA_NEWS,jadwalResultItem);
                myFragment.setArguments(mBundle);

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, myFragment).addToBackStack(null).commit();



            }
            else if ( jadwal_resultItem.getStatus().equalsIgnoreCase("N")&&jadwal_resultItem.getSisa()>0){


                Intent intent=new Intent(holder.itemView.getContext(), DetailBusActivity.class);
                jadwalResultItem.setAkhir(jadwal_resultItem.getAkhir());
                jadwalResultItem.setEstimasi(jadwal_resultItem.getEstimasi());
                jadwalResultItem.setMulaiWaktu(jadwal_resultItem.getMulaiWaktu());
                jadwalResultItem.setHarga(jadwal_resultItem.getHarga());
                jadwalResultItem.setArmadaNama(jadwal_resultItem.getArmadaNama());
                jadwalResultItem.setJadwalId(jadwal_resultItem.getJadwalId());
                jadwalResultItem.setKelas_nama(jadwal_resultItem.getKelas_nama());
                jadwalResultItem.setMulai(jadwal_resultItem.getMulai());
                jadwalResultItem.setSisa(jadwal_resultItem.getSisa());
                jadwalResultItem.setKapasitas(jadwal_resultItem.getKapasitas());
                jadwalResultItem.setArmada_id(jadwal_resultItem.getArmada_id());
                jadwalResultItem.setKelas_id(jadwal_resultItem.getKelas_id());
                jadwalResultItem.setTipe_seat(jadwal_resultItem.getTipe_seat());
                jadwalResultItem.setKapasitas(jadwal_resultItem.getKapasitas());
                jadwalResultItem.setMulai_sampai(jadwal_resultItem.getMulai_sampai());
                intent.putExtra("tanggal_view",JadwalFragment.tanggalView);
                intent.putExtra("agen_asal",JadwalFragment.agen_asal);
                intent.putExtra("agen_tujuan",JadwalFragment.agen_tujuan);
                intent.putExtra("tanggal",JadwalFragment.tanggal);
                intent.putExtra("is_pulangpergi", "n");

                intent.putExtra(Utils.EXTRA_NEWS,jadwalResultItem);
                holder.itemView.getContext().startActivity(intent);


            }
            else{
                Toast.makeText(context,"Jadwal Tidak Tersedia",Toast.LENGTH_LONG).show();
            }


        });

    }

    @Override
    public int getItemCount() {
        return jadwal_resultItems.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_armada)
        TextView tvArmada;
        @BindView(R.id.tv_harga)
        TextView tvHarga;
        @BindView(R.id.tv_kelas)
        TextView tvKelas;
        @BindView(R.id.tv_tersedia)
        TextView tvTersedia;
        @BindView(R.id.tv_rute)
        TextView tvRute;
        @BindView(R.id.tv_waktu)
        TextView tvWaktu;
        @BindView(R.id.tv_durasi)
        TextView tvDurasi;
        @BindView(R.id.btn_lanjut)
        Button btnLanjut;
        @BindView(R.id.card)
        CardView cardView;
        @BindView(R.id.relative)
        RelativeLayout relativeLayout;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}

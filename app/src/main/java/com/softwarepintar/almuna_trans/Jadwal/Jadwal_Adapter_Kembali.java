package com.softwarepintar.almuna_trans.Jadwal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.DetailBus.DetailBusActivity;
import com.softwarepintar.almuna_trans.R;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

class Jadwal_Adapter_Kembali extends RecyclerView.Adapter<Jadwal_Adapter_Kembali.ViewHolder> {
    private Context context;
    private List<Jadwal_ResultItem>jadwal_resultItems;
    public static Date date;
    private static final Bundle mBundle = new Bundle();
    public Jadwal_Adapter_Kembali(@NonNull Context context, List<Jadwal_ResultItem> jadwal_resultItems) {
        this.context = context;
        this.jadwal_resultItems = jadwal_resultItems;
    }

    @Override
    public Jadwal_Adapter_Kembali.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_jadwal, parent, false);
        return new Jadwal_Adapter_Kembali.ViewHolder(view);
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Jadwal_Adapter_Kembali.ViewHolder holder, int position) {
        final Jadwal_ResultItem jadwal_resultItem = jadwal_resultItems.get(position);

        int durasi= Integer.parseInt(jadwal_resultItem.getEstimasi());

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

                intent.putExtra("asal", JadwalFragment.asal);
                intent.putExtra("tujuan", JadwalFragment.tujuan);
                intent.putExtra("tanggal", JadwalFragment.tanggal);
                intent.putExtra("tanggal_view", JadwalFragment.tanggalView);
                intent.putExtra("penumpang", JadwalFragment.jml_penumpang);
                intent.putExtra("is_pulangpergi", JadwalFragment.is_pulangpergi);
                intent.putExtra("agen_asal", JadwalFragment.agen_asal);
                intent.putExtra("agen_tujuan", JadwalFragment.agen_tujuan);
                intent.putExtra("bayi", JadwalFragment.bayi);
                intent.putExtra("tanggal_pergi", JadwalFragment.tanggal_pergi);
                intent.putExtra("tanggal_view_pergi", JadwalFragment.tanggal_view_pergi);
                intent.putExtra("armada", JadwalKembaliFragment.armada_berangkat);
                intent.putExtra("jadwal_id", JadwalKembaliFragment.jadwal_id_berangkat);
                intent.putExtra("armada_id", JadwalKembaliFragment.armada_id_berangkat);
                intent.putExtra("kelas_id", JadwalKembaliFragment.kelas_id_berangkat);
                intent.putExtra("harga", JadwalKembaliFragment.harga_berangkat);
                intent.putExtra("waktu_berangkat", JadwalKembaliFragment.waktu_berangkat);
                intent.putExtra("kelas_nama", JadwalKembaliFragment.kelas_berangkat);
                intent.putExtra("kapasitas", JadwalKembaliFragment.kapasitas_berangkat);
                intent.putExtra("estimasi", JadwalKembaliFragment.estimasi_berangkat);


                intent.putExtra(Utils.EXTRA_NEWS,jadwalResultItem);
                holder.itemView.getContext().startActivity(intent);





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

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}

package com.softwarepintar.almuna_trans.payment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.DetailBus.DetailBusActivity;
import com.softwarepintar.almuna_trans.Jadwal.JadwalFragment;
import com.softwarepintar.almuna_trans.MainActivity;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.Register.Register_Response;
import com.softwarepintar.almuna_trans.midtransmodel.MidtransPaymentActivity;
import com.softwarepintar.almuna_trans.ui.TiketSaya.tiketaktif.DetailTiketActivity;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.softwarepintar.almuna_trans.API.Utils.detail_order;
import static com.softwarepintar.almuna_trans.API.Utils.kdbayar;
import static com.softwarepintar.almuna_trans.API.Utils.qty;
import static com.softwarepintar.almuna_trans.API.Utils.saldo;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.ViewHolder> {
    @NonNull
    private Context context;
    private List<BankResultItem> bankResultItems;
    private static final String ALLOWED_CHARACTERS = "0123456789";
    private static int totharga;
    private static String bankid;
    private static String pesan;



    public BankAdapter(@NonNull Context context, List<BankResultItem> bankResultItems) {
        this.context = context;
        this.bankResultItems = bankResultItems;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bank, parent, false);
        return new ViewHolder(view);
    }
    private static String getRandomString() {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(3);
        for (int i = 0; i < 3; ++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));

        return sb.toString();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       BankResultItem  bankResultItem= bankResultItems.get(position);
        holder.nmbank.setText(bankResultItem.getBank());
        holder.norek.setText(bankResultItem.getNomor());

        Glide.with(context)
                .load(bankResultItem.getImg())
                .into(holder.img);

        holder.btn_petunjuk.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.layout_petunjuk);
                dialog.setTitle("Form Transfer");
                dialog.setCancelable(true);
                TextView tvkdbayar=dialog.findViewById(R.id.tvkdbayar);
                TextView tv_norek=dialog.findViewById(R.id.tv_norek);
                TextView tvbatas=dialog.findViewById(R.id.tvbatas);
                Button btnupload=dialog.findViewById(R.id.btnupload);
                Button btnuploadnanti=dialog.findViewById(R.id.btnuploadnanti);
                Locale localeID = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);


                if (bankResultItem.getId().equalsIgnoreCase("3")){

                    tvkdbayar.setText("Kode Bayar "+bankResultItem.getId()+" Saldo anda "+saldo);
                    int nominal= Integer.parseInt(Utils.total);
                    totharga=nominal;

                    tv_norek.setText("1. Pastikan saldo AlmunaPay anda mencukupi untuk melakukan transaksi ini dengan nominal "+formatRupiah.format(nominal));

                    tvbatas.setText("* Harap Selesaikan Pembayaran Sebelum "+Utils.batas_bayar+", dan nikmati rewardsnya");

                    btnupload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (Integer.parseInt(saldo) <=nominal){
                                Toast.makeText(holder.itemView.getContext(), "Saldo Anda Tidak Mencukupi Silahkan Top Up Terlebih Dahulu", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                komfirm();


                            }

                        }
                    });
                }
                else if (bankResultItem.getId().equalsIgnoreCase("2") || bankResultItem.getId().equalsIgnoreCase("1")){

                    tvkdbayar.setText("Kode Bayar "+ Utils.kdbayar);
                    int nominal= Integer.parseInt(Utils.total);
                    int kd= Integer.parseInt(getRandomString());
                    totharga= nominal+kd;
                    tv_norek.setText("1. Transfer sesuai nominal sampai digit terakhir ke nomor rekening berikut "+bankResultItem.getNomor()+" bank "+bankResultItem.getBank()+
                            " atas nama "+bankResultItem.getNama()+" dengan nominal "+formatRupiah.format(nominal));

                    tvbatas.setText("* Harap Selesaikan Pembayaran Sebelum "+Utils.batas_bayar);

                    btnupload.setOnClickListener(v1 -> komfirm());
                    btnuploadnanti.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                       pesan="Untuk Melakukan Pembayaran Tiket Silahkan ke Menu Tiket kemudian pilih menunggu pembayaran";

                        }
                    });






                }

                else if (bankResultItem.getId().equalsIgnoreCase("4")) {


                    int nominal= Integer.parseInt(Utils.total);
                    int kd= Integer.parseInt(getRandomString());
                    totharga= nominal+2500+kd;
                    tv_norek.setText("1. Lakukan Pembayaran sesuai nominal sampai digit terakhir dengan nominal "+formatRupiah.format(nominal));

                    tvbatas.setText("* Harap Selesaikan Pembayaran Sebelum "+Utils.batas_bayar);

                    btnupload.setOnClickListener(v1 -> {

                        Intent intent=new Intent(holder.itemView.getContext(), MidtransPaymentActivity.class);
                        intent.putExtra("id", kdbayar);
                        intent.putExtra("nominal", totharga);
                        intent.putExtra("qty", qty);
                        intent.putExtra("name", detail_order);
                        holder.itemView.getContext().startActivity(intent);

                    });
                    btnupload.setText("Lanjutkan");
                   btnuploadnanti.setVisibility(View.INVISIBLE);




                }




                dialog.show();





            }

            private void alert(){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.
                        setMessage(pesan
                        );
                        alertDialogBuilder.setPositiveButton("oke",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        Intent intent;
                                        intent = new Intent(holder.itemView.getContext(), MainActivity.class);
                                        intent.putExtra(Utils.TAG_user_id,PembayaranActivity.user_id);
                                        holder.itemView.getContext().startActivity(intent);
                                    }
                                });


                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }


            private void komfirm() {

                ProgressDialog progressDialog =new ProgressDialog(context);
                progressDialog.setCancelable(true);
                progressDialog.setMessage("Proses Konfirmasi");
                progressDialog.show();
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(Utils.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIService apiService=retrofit.create(APIService.class);
                Call<Register_Response> call=apiService.uploadtf(Utils.kdbayar,
                        Utils.batas_bayar, String.valueOf(totharga),bankResultItem.getId(),Utils.user_id,
                        Utils.nm_user);
                call.enqueue(new Callback<Register_Response>() {
                    @Override
                    public void onResponse(Call<Register_Response> call, Response<Register_Response> response) {
                        int value=response.body().getValue();
                        String message=response.body().getMessage();
                        if (value==1){
                            progressDialog.dismiss();
                            pesan="Pembayaran Sukses Silahkan Tunggu Konfirmasi dari Admin Untuk Melihat Status Tiket Ada di Menu Tiket";
                            alert();
//                            Toast.makeText(context,message , Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(UploadTransferActivity.this, MainActivity.class));
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Register_Response> call, Throwable t) {
                        Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
                    }
                });


            }

        });


    }



    @Override
    public int getItemCount() {
        return bankResultItems.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.nmbank)
        TextView nmbank;
        @BindView(R.id.norek)
        TextView norek;
        @BindView(R.id.btn_petunjuk)
        Button btn_petunjuk;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

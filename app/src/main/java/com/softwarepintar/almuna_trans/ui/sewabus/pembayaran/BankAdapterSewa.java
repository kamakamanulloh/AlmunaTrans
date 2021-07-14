package com.softwarepintar.almuna_trans.ui.sewabus.pembayaran;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.Register.Register_Response;
import com.softwarepintar.almuna_trans.payment.BankResultItem;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.softwarepintar.almuna_trans.API.Utils.bayardp;
import static com.softwarepintar.almuna_trans.API.Utils.jumlah;
import static com.softwarepintar.almuna_trans.API.Utils.kurang;
import static com.softwarepintar.almuna_trans.API.Utils.saldo;
import static com.softwarepintar.almuna_trans.API.Utils.statussewa;
import static com.softwarepintar.almuna_trans.API.Utils.total;

public class BankAdapterSewa extends RecyclerView.Adapter<BankAdapterSewa.ViewHolder> {
    @NonNull
    private Context context;
    private List<BankResultItem> bankResultItems;


    private static int nilai;


    public BankAdapterSewa(@NonNull Context context, List<BankResultItem> bankResultItems) {
        this.context = context;
        this.bankResultItems = bankResultItems;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bank, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BankResultItem bankResultItem=bankResultItems.get(position);
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
                dialog.setContentView(R.layout.layout_petunjuk_sewa);
                dialog.setTitle("Form Transfer");
                dialog.setCancelable(true);
                TextView tvkdbayar=dialog.findViewById(R.id.tvkdbayar);
                TextView tv_norek=dialog.findViewById(R.id.tv_norek);
                TextView tvdua=dialog.findViewById(R.id.tvdua);
                TextView tvbatas=dialog.findViewById(R.id.tvbatas);
                Button btnupload=dialog.findViewById(R.id.btnupload);
                Button btnuploadnanti=dialog.findViewById(R.id.btnuploadnanti);
                Locale localeID = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

                tvkdbayar.setText("Kode Bayar "+ Utils.kdbayar);
                if (statussewa.equalsIgnoreCase("BELUM")) {
                    if (bankResultItem.getId().equalsIgnoreCase("3")) {

                        tvkdbayar.setText("Kode Bayar " + Utils.kdbayar + " Saldo anda " + saldo);

                        tv_norek.setText("1. Silahkan dp min 30% via transfer nomor rekening yang tersedia dengan biaya" + formatRupiah.format(bayardp) + ", apabila ingin lakukan pelunasan bisa transfer sesuai nominal sampai digit terakhir ke nomor rekening berikut " + bankResultItem.getNomor() + " bank " + bankResultItem.getBank() +
                                " atas nama " + bankResultItem.getNama() + " dengan nominal " + formatRupiah.format(Double.parseDouble(total)));

                        tvbatas.setText("* Harap Selesaikan Pembayaran Sebelum " + Utils.batas_bayar);


                        nilai = bayardp;
                        btnupload.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (Integer.parseInt(saldo) <= bayardp) {
                                    Toast.makeText(holder.itemView.getContext(), "Saldo Anda Tidak Mencukupi Silahkan Top Up Terlebih Dahulu", Toast.LENGTH_SHORT).show();

                                } else {
                                    komfirm();


                                }

                            }
                        });
                    }
                    else{

                        tvkdbayar.setText("Kode Bayar " + Utils.kdbayar + " Saldo anda " + saldo);

                        tv_norek.setText("1. Silahkan dp min 30% via transfer nomor rekening yang tersedia dengan biaya" + formatRupiah.format(bayardp) + ", apabila ingin lakukan pelunasan bisa transfer sesuai nominal sampai digit terakhir ke nomor rekening berikut " + bankResultItem.getNomor() + " bank " + bankResultItem.getBank() +
                                " atas nama " + bankResultItem.getNama() + " dengan nominal " + formatRupiah.format(Double.parseDouble(total)));

                        tvbatas.setText("* Harap Selesaikan Pembayaran Sebelum " + Utils.batas_bayar);

                        tvbatas.setText("* Harap Selesaikan Pembayaran Sebelum " + Utils.batas_bayar);
                        nilai = bayardp;
                        btnupload.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                    komfirm();




                            }
                        });
                    }
                }
                else{
                    if (bankResultItem.getId().equalsIgnoreCase("3")){
                        tv_norek.setText("1. Silahkan melakukan pelunasan via transfer nomor rekening yang tersedia dengan biaya"+ formatRupiah.format(kurang)
                                +  " atas nama "+bankResultItem.getNama());

                        tvdua.setText("2. Klik konfirmasi pelunasan");
                        tvbatas.setText("* Harap Selesaikan Pembayaran Sebelum "+Utils.batas_bayar);
                        nilai=kurang;

                            btnupload.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Integer.parseInt(saldo) <=bayardp){
                                        Toast.makeText(holder.itemView.getContext(), "Saldo Anda Tidak Mencukupi Silahkan Top Up Terlebih Dahulu", Toast.LENGTH_SHORT).show();

                                    }
                                    else{
                                        komfirm();


                                    }
                                }
                            });
                            btnuploadnanti.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    komfirm();
                                }
                            });


                        }
                        else{



                            tv_norek.setText("1. Silahkan melakukan pelunasan via transfer nomor rekening yang tersedia dengan biaya"+ formatRupiah.format(kurang)
                                    +  " atas nama "+bankResultItem.getNama());

                            tvdua.setText("2. Klik konfirmasi pelunasan");
                            tvbatas.setText("* Harap Selesaikan Pembayaran Sebelum "+Utils.batas_bayar);
                            nilai=kurang;
                            btnupload.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                        komfirm();



                                }
                            });
                            btnuploadnanti.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    komfirm();
                                }
                            });

                        }


                    }














                dialog.show();






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
                Call<Register_Response> call=apiService.konfirm_sewa(String.valueOf(nilai),
                        jumlah, total,Utils.kdbayar
                        ,Utils.user_id,bankResultItem.getId(),Utils.batas_bayar,
                        Utils.nm_user);
                call.enqueue(new Callback<Register_Response>() {
                    @Override
                    public void onResponse(Call<Register_Response> call, Response<Register_Response> response) {
                        int value=response.body().getValue();
                        String message=response.body().getMessage();
                        if (value==1){
                            progressDialog.dismiss();
                            Toast.makeText(context,message , Toast.LENGTH_SHORT).show();
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

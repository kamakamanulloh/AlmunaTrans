package com.softwarepintar.almuna_trans.API;

import com.softwarepintar.almuna_trans.Booking.BookingResponse;
import com.softwarepintar.almuna_trans.Fasilitas.FasilitasResponse;
import com.softwarepintar.almuna_trans.Jadwal.Jadwal_Response;
import com.softwarepintar.almuna_trans.Login.Login_Response;
import com.softwarepintar.almuna_trans.Register.Register_Response;
import com.softwarepintar.almuna_trans.agen.AgenResponse;
import com.softwarepintar.almuna_trans.almunapay.RiwayatResponse;
import com.softwarepintar.almuna_trans.payment.BankResponse;
import com.softwarepintar.almuna_trans.seats.SeatsResponse;
import com.softwarepintar.almuna_trans.ui.TiketSaya.PenumpangResponse;
import com.softwarepintar.almuna_trans.ui.TiketSaya.TiketResponse;
import com.softwarepintar.almuna_trans.ui.TiketSaya.sewa.PelunasanResponse;
import com.softwarepintar.almuna_trans.ui.TiketSaya.sewa.SewaResponse;
import com.softwarepintar.almuna_trans.ui.home.InfoResponse;
import com.softwarepintar.almuna_trans.ui.home.Response_Kota;
import com.softwarepintar.almuna_trans.ui.home.SaldoResponse;
import com.softwarepintar.almuna_trans.ui.sewabus.KelasResponse;
import com.softwarepintar.almuna_trans.ui.sewabus.paketsewa.PaketSewaResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    @FormUrlEncoded
    @POST("register.php")
    Call<Register_Response>register
            (@Field("username")String nama,
             @Field("alamat")String alamat,
             @Field("nohp")String nohp,
             @Field("email")String email,
             @Field("user_password")String user_password,
             @Field("jk")String jk,
             @Field("jenis_id")String jenis_id,
             @Field("no_id")String no_id

             );

    @FormUrlEncoded
    @POST("add_kursi.php")
    Call<BookingResponse>add_kursi(
            @Field("jumlah")String qty,
            @Field("kode")String kode,
            @Field("kursi_berangkat")String kursi,
            @Field("kursi_kembali")String kursi_kembali,
            @Field("asal")String asal,
            @Field("tujuan")String tujuan,
            @Field("is_pulangpergi")String is_pulangpergi
    );

    @FormUrlEncoded
    @POST("booking.php")
    Call<BookingResponse>booking
            (@Field("kode")String kode,
             @Field("noid")String noid,
             @Field("jenis_id")String jenis_id,
             @Field("rute_asal")String rute_asal,
             @Field("rute_tujuan")String rute_tujuan,
             @Field("berangkat")String berangkat,
             @Field("member_id")String memberid,
             @Field("nominal")String nominal,
             @Field("jumlah")String jumlah,
             @Field("estimasi")String estimasi,
             @Field("nm_penumpang")String nm_penumpang,
             @Field("armada_nama")String armada_nama,
             @Field("kelas")String kelas,
             @Field("rute_asal_kembali")String rute_asal_kembali,
             @Field("rute_tujuan_kembali")String rute_tujuan_kembali,
             @Field("berangkat_kembali")String berangkat_kembali,
             @Field("harga_kembali")String harga_kembali,
             @Field("estimasi_kembali")String estimasi_kembali,
             @Field("armada_nama_kembali")String armada_nama_kembali,
             @Field("kelas_kembali")String kelas_kembali,
             @Field("harga_total")String harga_total,
             @Field("is_pulangpergi")String is_pulangpergi,
             @Field("kode_kembali")String kode_kembali,
             @Field("agen_asal")String agen_asal,
            @Field("agen_tujuan")String agen_tujuan,
             @Field("armada_id")String armada_id,
             @Field("armada_id_kembali")String armada_id_kembali);

    @FormUrlEncoded
    @POST("booking2.php")
    Call<BookingResponse>booking2
            (@Field("kode")String kode,
             @Field("noid")String noid,
             @Field("jenis_id")String jenis_id,
             @Field("rute_asal")String rute_asal,
             @Field("rute_tujuan")String rute_tujuan,
             @Field("berangkat")String berangkat,
             @Field("member_id")String memberid,
             @Field("nominal")String nominal,
             @Field("jumlah")String jumlah,
             @Field("estimasi")String estimasi,
             @Field("nm_penumpang")String nm_penumpang,
             @Field("armada_nama")String armada_nama,
             @Field("kelas")String kelas,
             @Field("noid2")String noid2,
             @Field("jenis_id2")String jenis_id2,
             @Field("nm_penumpang2")String nm_penumpang2,
             @Field("rute_asal_kembali")String rute_asal_kembali,
             @Field("rute_tujuan_kembali")String rute_tujuan_kembali,
             @Field("berangkat_kembali")String berangkat_kembali,
             @Field("harga_kembali")String harga_kembali,
             @Field("estimasi_kembali")String estimasi_kembali,
             @Field("armada_nama_kembali")String armada_nama_kembali,
             @Field("kelas_kembali")String kelas_kembali,
             @Field("harga_total")String harga_total,
             @Field("is_pulangpergi")String is_pulangpergi,
             @Field("kode_kembali")String kode_kembali,
             @Field("agen_asal")String agen_asal,
             @Field("agen_tujuan")String agen_tujuan,
             @Field("armada_id")String armada_id,
             @Field("armada_id_kembali")String armada_id_kembali);


    @FormUrlEncoded
    @POST("booking3.php")
    Call<BookingResponse>booking3
            (@Field("kode")String kode,
             @Field("noid")String noid,
             @Field("jenis_id")String jenis_id,
             @Field("rute_asal")String rute_asal,
             @Field("rute_tujuan")String rute_tujuan,
             @Field("berangkat")String berangkat,
             @Field("member_id")String memberid,
             @Field("nominal")String nominal,
             @Field("jumlah")String jumlah,
             @Field("estimasi")String estimasi,
             @Field("nm_penumpang")String nm_penumpang,
             @Field("armada_nama")String armada_nama,
             @Field("kelas")String kelas,
             @Field("noid2")String noid2,
             @Field("jenis_id2")String jenis_id2,
             @Field("nm_penumpang2")String nm_penumpang2,
             @Field("noid3")String noid3,
             @Field("jenis_id3")String jenis_id3,
             @Field("nm_penumpang3")String nm_penumpang3,
             @Field("rute_asal_kembali")String rute_asal_kembali,
             @Field("rute_tujuan_kembali")String rute_tujuan_kembali,
             @Field("berangkat_kembali")String berangkat_kembali,
             @Field("harga_kembali")String harga_kembali,
             @Field("estimasi_kembali")String estimasi_kembali,
             @Field("armada_nama_kembali")String armada_nama_kembali,
             @Field("kelas_kembali")String kelas_kembali,
             @Field("harga_total")String harga_total,
             @Field("is_pulangpergi")String is_pulangpergi,
             @Field("kode_kembali")String kode_kembali,
             @Field("agen_asal")String agen_asal,
             @Field("agen_tujuan")String agen_tujuan,
             @Field("armada_id")String armada_id,
             @Field("armada_id_kembali")String armada_id_kembali);

    @FormUrlEncoded
    @POST("booking4.php")
    Call<BookingResponse>booking4
            (@Field("kode")String kode,
             @Field("noid")String noid,
             @Field("jenis_id")String jenis_id,
             @Field("rute_asal")String rute_asal,
             @Field("rute_tujuan")String rute_tujuan,
             @Field("berangkat")String berangkat,
             @Field("member_id")String memberid,
             @Field("nominal")String nominal,
             @Field("jumlah")String jumlah,
             @Field("estimasi")String estimasi,
             @Field("nm_penumpang")String nm_penumpang,
             @Field("armada_nama")String armada_nama,
             @Field("kelas")String kelas,
             @Field("noid2")String noid2,
             @Field("jenis_id2")String jenis_id2,
             @Field("nm_penumpang2")String nm_penumpang2,
             @Field("noid3")String noid3,
             @Field("jenis_id3")String jenis_id3,
             @Field("nm_penumpang3")String nm_penumpang3,
             @Field("noid4")String noid4,
             @Field("jenis_id4")String jenis_id4,
             @Field("nm_penumpang4")String nm_penumpang4,
             @Field("rute_asal_kembali")String rute_asal_kembali,
             @Field("rute_tujuan_kembali")String rute_tujuan_kembali,
             @Field("berangkat_kembali")String berangkat_kembali,
             @Field("harga_kembali")String harga_kembali,
             @Field("estimasi_kembali")String estimasi_kembali,
             @Field("armada_nama_kembali")String armada_nama_kembali,
             @Field("kelas_kembali")String kelas_kembali,
             @Field("harga_total")String harga_total,
             @Field("is_pulangpergi")String is_pulangpergi,
             @Field("kode_kembali")String kode_kembali,
             @Field("agen_asal")String agen_asal,
             @Field("agen_tujuan")String agen_tujuan,
             @Field("armada_id")String armada_id,
             @Field("armada_id_kembali")String armada_id_kembali);

    @FormUrlEncoded
    @POST("sewa.php")
    Call<BookingResponse>sewa(
            @Field("berangkat")String berangkat,
            @Field("jam")String jam,
            @Field("kode")String kode,
            @Field("longitude")String longitude,
            @Field("lattitude")String lattitude,
            @Field("member_id")String member_id,

            @Field("jumlah")String jumlah,
            @Field("daftar_sewa_bus_id")String daftar_sewa_bus_id,

            @Field("total_bayar")String total_bayar,
            @Field("penjemputan")String penjemputan,
            @Field("harga")String harga

    );


    @GET("list_sewa_paket.php")
    Call<PaketSewaResponse>list_paket();

    @GET("info.php")
    Call<InfoResponse>info();

    @GET("all_info.php")
    Call<InfoResponse>all_info();

    @FormUrlEncoded
    @POST("list_sewa_paket_cari.php")
    Call<PaketSewaResponse> list_sewa_paket_cari(


            @Field("tujuan") String cari);

    @GET("list_kelas.php")
    Call<KelasResponse>list_kelas();

    @GET("list_bus.php")
    Call<KelasResponse>list_bus();


    @GET("list_rekening.php")
    Call<BankResponse>list_bank();

    @GET("list_rekening_topup.php")
    Call<BankResponse>list_bank_topup();

    @FormUrlEncoded
    @POST("konfirmasi.php")
    Call<Register_Response>uploadtf(@Field("kode")String kode,
                                    @Field("batas")String batas,
                                    @Field("nominal")String nominal,
                                    @Field("rekening")String rekening,
                                    @Field("member_id")String memberid,
                                    @Field("nama")String nama    )
                                   ;


    @FormUrlEncoded
    @POST("edit_pswd.php")
    Call<Register_Response>edit_password(@Field("user_id")String user_id,
                                    @Field("password")String password )
            ;


    @FormUrlEncoded
    @POST("cekhp.php")
    Call<Login_Response>cekhp(@Field("nohp")String nohp )
            ;

    @FormUrlEncoded
    @POST("konfirm_sewa.php")
    Call<Register_Response>konfirm_sewa(
            @Field("nominal") String nominal,

            @Field("jumlah_bus")String jumlah_bus,
            @Field("total_bayar")String total,
            @Field("kode")String kode,
            @Field("member_id")String member_id,
            @Field("rekening_id")String rekening_id,
            @Field("batas_bayar")String batas_bayar,
            @Field("nama")String nama);


    @FormUrlEncoded
    @POST("topup.php")
    Call<Register_Response>topup(
            @Field("nominal")String nominal,
            @Field("cara")String cara,
            @Field("petugas_id")String petugas_id,
            @Field("rekening_id")String rekening_id,
            @Field("member_id")String member_id,
            @Field("kode")String kode,
            @Field("nama")String nama

    );

    @FormUrlEncoded
    @POST("EditProfil.php")
    Call<Register_Response>editprofil(
            @Field("id")String id,
            @Field("username")String username,
            @Field("alamat")String alamat,
            @Field("jk")String jk

    );



    @GET("saldo.php?member_id=")
        Call<SaldoResponse>saldo(
                @Query("member_id")String memberid);



    @GET("json/tiket?member_id=")
    Call<TiketResponse>tiket(@Query("member_id")String memberid);

    @GET("json/almunapay?member_id=")
    Call<RiwayatResponse>his_almunapay(@Query("member_id")String memberid);

    @GET("json/pelunasan?kode=")
    Call<PelunasanResponse>pelunasan(@Query("kode")String kode);

    @GET("json/tiketmenunggu?member_id=")
    Call<TiketResponse>tiket_menunggu(@Query("member_id")String memberid);

    @GET("json/tikethistory?member_id=")
    Call<TiketResponse>tikethistory(@Query("member_id")String memberid);

    @GET("json/tiketpending?member_id=")
    Call<TiketResponse>tiketPending(@Query("member_id")String memberid);

    @GET("json/sewa?member_id=")
    Call<SewaResponse>list_sewa(@Query("member_id")String memberid);

    @GET("json/penumpang?kode=")
    Call<PenumpangResponse>penumpang(@Query("kode")String kode);


    @FormUrlEncoded
    @POST("profil.php?id=")
    Call<Login_Response>profil
            (@Field("id")String id);

    @GET("fasilitas.php?kelas_id=")
    Call<FasilitasResponse>fasiltas(
            @Query("kelas_id") String armada_id);

    @GET("fasilitas_jalan.php?jadwal_id=")
    Call<FasilitasResponse>fasiltas_jalan(
            @Query("jadwal_id") String jadwal_id);


    @FormUrlEncoded
    @POST("login.php")
    Call<Login_Response>login
            (@Field("username")String username,
             @Field("password")String passwordd);

    @GET("list_kota.php")
    Call<Response_Kota>list_kota();

    @FormUrlEncoded
    @POST("list_jadwal.php")
    Call<Jadwal_Response>list_jadwal
            (@Field("asal") String asal,
             @Field("tujuan") String tujuan,
             @Field("tanggal") String tanggal);

    @FormUrlEncoded
    @POST("listAgen.php")
    Call<AgenResponse>list_agen
            (@Field("kota") String kota);

    @FormUrlEncoded
    @POST("list_kursi.php")
    Call<SeatsResponse>list_kursi
            (@Field("berangkat")String berangkat,
             @Field("armada_id")String armada_nama,
             @Field("rute_asal")String rute_asal,
             @Field("rute_tujuan")String rute_tujuan);





}

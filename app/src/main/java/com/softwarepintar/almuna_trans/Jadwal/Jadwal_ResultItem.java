package com.softwarepintar.almuna_trans.Jadwal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Jadwal_ResultItem implements Parcelable {

	@SerializedName("armada_nama")
	private String armadaNama;

	@SerializedName("sisa")
	private int sisa;

	@SerializedName("estimasi")
	private String estimasi;

	@SerializedName("jadwal_id")
	private String jadwalId;

	@SerializedName("harga")
	private String harga;

	@SerializedName("mulai_waktu")
	private String mulaiWaktu;

	@SerializedName("jadwal_tanggal")
	private String jadwalTanggal;

	@SerializedName("mulai")
	private String mulai;

	@SerializedName("akhir")
	private String akhir;


	@SerializedName("kelas_nama")
	private String kelas_nama;

    @SerializedName("kapasitas")
    private String kapasitas;

	@SerializedName("armada_id")
	private String armada_id;

	@SerializedName("kelas_id")
	private String kelas_id;

	@SerializedName("mulai_sampai")
	private String mulai_sampai;

	@SerializedName("tipe_seat")
	private String tipe_seat;


	@SerializedName("status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipe_seat() {
		return tipe_seat;
	}

	public void setTipe_seat(String tipe_seat) {
		this.tipe_seat = tipe_seat;
	}

	public String getMulai_sampai() {
		return mulai_sampai;
	}

	public void setMulai_sampai(String mulai_sampai) {
		this.mulai_sampai = mulai_sampai;
	}

	protected Jadwal_ResultItem(Parcel in) {
		armadaNama = in.readString();
		sisa = in.readInt();
		estimasi = in.readString();
		jadwalId = in.readString();
		harga = in.readString();
		mulaiWaktu = in.readString();
		jadwalTanggal = in.readString();
		mulai = in.readString();
		akhir = in.readString();
		kelas_nama = in.readString();
		kapasitas = in.readString();
		armada_id = in.readString();
		kelas_id = in.readString();
		mulai_sampai = in.readString();
	}

	public static final Creator<Jadwal_ResultItem> CREATOR = new Creator<Jadwal_ResultItem>() {
		@Override
		public Jadwal_ResultItem createFromParcel(Parcel in) {
			return new Jadwal_ResultItem(in);
		}

		@Override
		public Jadwal_ResultItem[] newArray(int size) {
			return new Jadwal_ResultItem[size];
		}
	};

	public Jadwal_ResultItem() {

	}

	public String getArmadaNama() {
		return armadaNama;
	}

	public void setArmadaNama(String armadaNama) {
		this.armadaNama = armadaNama;
	}

	public int getSisa() {
		return sisa;
	}

	public void setSisa(int sisa) {
		this.sisa = sisa;
	}

	public String getEstimasi() {
		return estimasi;
	}

	public void setEstimasi(String estimasi) {
		this.estimasi = estimasi;
	}

	public String getJadwalId() {
		return jadwalId;
	}

	public void setJadwalId(String jadwalId) {
		this.jadwalId = jadwalId;
	}

	public String getHarga() {
		return harga;
	}

	public void setHarga(String harga) {
		this.harga = harga;
	}

	public String getMulaiWaktu() {
		return mulaiWaktu;
	}

	public void setMulaiWaktu(String mulaiWaktu) {
		this.mulaiWaktu = mulaiWaktu;
	}

	public String getJadwalTanggal() {
		return jadwalTanggal;
	}

	public void setJadwalTanggal(String jadwalTanggal) {
		this.jadwalTanggal = jadwalTanggal;
	}

	public String getMulai() {
		return mulai;
	}

	public void setMulai(String mulai) {
		this.mulai = mulai;
	}

	public String getAkhir() {
		return akhir;
	}

	public void setAkhir(String akhir) {
		this.akhir = akhir;
	}

	public String getKelas_nama() {
		return kelas_nama;
	}

	public void setKelas_nama(String kelas_nama) {
		this.kelas_nama = kelas_nama;
	}

	public String getKapasitas() {
		return kapasitas;
	}

	public void setKapasitas(String kapasitas) {
		this.kapasitas = kapasitas;
	}

	public String getArmada_id() {
		return armada_id;
	}

	public void setArmada_id(String armada_id) {
		this.armada_id = armada_id;
	}

	public String getKelas_id() {
		return kelas_id;
	}

	public void setKelas_id(String kelas_id) {
		this.kelas_id = kelas_id;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"armada_nama = '" + armadaNama + '\'' + 
			",sisa = '" + sisa + '\'' + 
			",estimasi = '" + estimasi + '\'' +
			",jadwal_id = '" + jadwalId + '\'' + 
			",harga = '" + harga + '\'' + 
			",mulai_waktu = '" + mulaiWaktu + '\'' + 
			",jadwal_tanggal = '" + jadwalTanggal + '\'' + 
			",mulai = '" + mulai + '\'' + 
			",akhir = '" + akhir + '\'' +
			",kelas_nama = '" + kelas_nama + '\'' +
                    ",kapasitas = '" + kapasitas + '\'' +
					",armada_id = '" + armada_id + '\'' +
					",kelas_id = '" + kelas_id + '\'' +
					",mulai_sampai = '" + mulai_sampai + '\'' +
					",tipe_seat = '" + tipe_seat + '\'' +
					",status = '" + status + '\'' +
					"}";
		}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(armadaNama);
		dest.writeInt(sisa);
		dest.writeString(estimasi);
		dest.writeString(jadwalId);
		dest.writeString(harga);
		dest.writeString(mulaiWaktu);
		dest.writeString(jadwalTanggal);
		dest.writeString(mulai);
		dest.writeString(akhir);
		dest.writeString(kelas_nama);
		dest.writeString(kapasitas);
		dest.writeString(armada_id);
		dest.writeString(kelas_id);
		dest.writeString(mulai_sampai);
	}

}
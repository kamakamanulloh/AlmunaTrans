package com.softwarepintar.almuna_trans.ui.TiketSaya;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class TiketResultItem implements Parcelable {

	@SerializedName("kode")
	private String kodeTiket;

	@SerializedName("rute_tujuan")
	private String ruteTujuan;

	@SerializedName("berangkat")
	private String berangkat;

	@SerializedName("estimasi")
	private String estimasi;

	@SerializedName("status")
	private String status;

	@SerializedName("rute_asal")
	private String ruteAsal;

	@SerializedName("armada_nama")
	private String armada_nama;

	@SerializedName("kelas")
	private String kelas;


	@SerializedName("nominal")
	private String nominal;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("batas_bayar")
	private String batas_bayar;



	public String getBatas_bayar() {
		return batas_bayar;
	}

	public void setBatas_bayar(String batas_bayar) {
		this.batas_bayar = batas_bayar;
	}

	public String getJumlah() {
		return jumlah;
	}

	public void setJumlah(String jumlah) {
		this.jumlah = jumlah;
	}

	public String getNominal() {
		return nominal;
	}

	public void setNominal(String nominal) {
		this.nominal = nominal;
	}

	public String getKelas() {
		return kelas;
	}

	public void setKelas(String kelas) {
		this.kelas = kelas;
	}

	public String getArmada_nama() {
		return armada_nama;
	}

	public void setArmada_nama(String armada_nama) {
		this.armada_nama = armada_nama;
	}



	protected TiketResultItem(Parcel in) {
		ruteTujuan = in.readString();
		berangkat = in.readString();
		estimasi = in.readString();
		ruteAsal = in.readString();
		kodeTiket=in.readString();
		armada_nama=in.readString();
		kelas=in.readString();
		nominal=in.readString();
		jumlah=in.readString();
		batas_bayar=in.readString();
	}

	public static final Creator<TiketResultItem> CREATOR = new Creator<TiketResultItem>() {
		@Override
		public TiketResultItem createFromParcel(Parcel in) {
			return new TiketResultItem(in);
		}

		@Override
		public TiketResultItem[] newArray(int size) {
			return new TiketResultItem[size];
		}
	};

	public void setKodeTiket(String kodeTiket){
		this.kodeTiket = kodeTiket;
	}

	public String getKodeTiket(){
		return kodeTiket;
	}

	public void setRuteTujuan(String ruteTujuan){
		this.ruteTujuan = ruteTujuan;
	}

	public String getRuteTujuan(){
		return ruteTujuan;
	}

	public void setBerangkat(String berangkat){
		this.berangkat = berangkat;
	}

	public String getBerangkat(){
		return berangkat;
	}

	public void setEstimasi(String estimasi){
		this.estimasi = estimasi;
	}

	public String getEstimasi(){
		return estimasi;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setRuteAsal(String ruteAsal){
		this.ruteAsal = ruteAsal;
	}

	public String getRuteAsal(){
		return ruteAsal;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"kode = '" + kodeTiket + '\'' +
			",rute_tujuan = '" + ruteTujuan + '\'' + 
			",berangkat = '" + berangkat + '\'' + 
			",estimasi = '" + estimasi + '\'' + 
			",status = '" + status + '\'' + 
			",rute_asal = '" + ruteAsal + '\'' +
					",armada_nama = '" + armada_nama + '\'' +
					",nominal = '" + nominal + '\'' +

					",jumlah = '" + jumlah + '\'' +
					",batas_bayar = '" + batas_bayar + '\'' +

			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(ruteTujuan);
		dest.writeString(berangkat);
		dest.writeString(estimasi);
		dest.writeString(ruteAsal);
		dest.writeString(kodeTiket);
		dest.writeString(armada_nama);
		dest.writeString(kelas);
		dest.writeString(nominal);
		dest.writeString(jumlah);
		dest.writeString(batas_bayar);

	}
	public TiketResultItem(){

	}
}
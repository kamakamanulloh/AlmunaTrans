package com.softwarepintar.almuna_trans.ui.sewabus.paketsewa;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class PaketSewaResultItem implements Parcelable {

	@SerializedName("harga")
	private String harga;

	@SerializedName("durasi_time")
	private String durasiTime;

	@SerializedName("id")
	private String id;

	@SerializedName("durasi")
	private String durasi;

	@SerializedName("kelas_id")
	private String kelasId;

	@SerializedName("tujuan")
	private String tujuan;

	@SerializedName("gambar")
	private String gambar;

	public String getGambar() {
		return gambar;
	}

	public void setGambar(String gambar) {
		this.gambar = gambar;
	}

	protected PaketSewaResultItem(Parcel in) {
		harga = in.readString();
		durasiTime = in.readString();
		id = in.readString();
		durasi = in.readString();
		kelasId = in.readString();
		tujuan = in.readString();
		gambar=in.readString();
	}
	public PaketSewaResultItem(){

	}

	public static final Creator<PaketSewaResultItem> CREATOR = new Creator<PaketSewaResultItem>() {
		@Override
		public PaketSewaResultItem createFromParcel(Parcel in) {
			return new PaketSewaResultItem(in);
		}

		@Override
		public PaketSewaResultItem[] newArray(int size) {
			return new PaketSewaResultItem[size];
		}
	};

	public void setHarga(String harga){
		this.harga = harga;
	}

	public String getHarga(){
		return harga;
	}

	public void setDurasiTime(String durasiTime){
		this.durasiTime = durasiTime;
	}

	public String getDurasiTime(){
		return durasiTime;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDurasi(String durasi){
		this.durasi = durasi;
	}

	public String getDurasi(){
		return durasi;
	}

	public void setKelasId(String kelasId){
		this.kelasId = kelasId;
	}

	public String getKelasId(){
		return kelasId;
	}

	public void setTujuan(String tujuan){
		this.tujuan = tujuan;
	}

	public String getTujuan(){
		return tujuan;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"harga = '" + harga + '\'' + 
			",durasi_time = '" + durasiTime + '\'' + 
			",id = '" + id + '\'' + 
			",durasi = '" + durasi + '\'' + 
			",kelas_id = '" + kelasId + '\'' + 
			",tujuan = '" + tujuan + '\'' +
					",gambar = '" + gambar + '\'' +
					"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(harga);
		dest.writeString(durasiTime);
		dest.writeString(id);
		dest.writeString(durasi);
		dest.writeString(kelasId);
		dest.writeString(tujuan);
		dest.writeString(gambar);
	}
}
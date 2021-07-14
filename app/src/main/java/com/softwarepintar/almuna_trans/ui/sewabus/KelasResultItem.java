package com.softwarepintar.almuna_trans.ui.sewabus;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class KelasResultItem implements Parcelable {
	public KelasResultItem(String nama, String id, String gambar) {

		this.nama = nama;
		this.id = id;
		this.gambar = gambar;
	}
	public KelasResultItem(){

	}

	@SerializedName("nama")
	private String nama;

	@SerializedName("id")
	private String id;

	@SerializedName("gambar")
	private String gambar;
	@SerializedName("kapasitas")
	private String kapasitas;

	public String getKapasitas() {
		return kapasitas;
	}

	public void setKapasitas(String kapasitas) {
		this.kapasitas = kapasitas;
	}

	protected KelasResultItem(Parcel in) {
		nama = in.readString();
		id = in.readString();
		gambar = in.readString();
		kapasitas=in.readString();
	}

	public static final Creator<KelasResultItem> CREATOR = new Creator<KelasResultItem>() {
		@Override
		public KelasResultItem createFromParcel(Parcel in) {
			return new KelasResultItem(in);
		}

		@Override
		public KelasResultItem[] newArray(int size) {
			return new KelasResultItem[size];
		}
	};

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setGambar(String gambar){
		this.gambar = gambar;
	}

	public String getGambar(){
		return gambar;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"nama = '" + nama + '\'' + 
			",id = '" + id + '\'' + 
			",gambar = '" + gambar + '\'' +
					",kapasitas = '" + kapasitas + '\'' +
					"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(nama);
		dest.writeString(id);
		dest.writeString(gambar);
		dest.writeString(kapasitas);
	}
}
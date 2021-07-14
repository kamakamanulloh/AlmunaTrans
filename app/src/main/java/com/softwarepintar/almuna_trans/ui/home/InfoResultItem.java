package com.softwarepintar.almuna_trans.ui.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class InfoResultItem implements Parcelable {

	@SerializedName("end_at")
	private String endAt;

	@SerializedName("img")
	private String img;

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("judul")
	private String judul;

	@SerializedName("status")
	private String status;

	protected InfoResultItem(Parcel in) {
		endAt = in.readString();
		img = in.readString();
		keterangan = in.readString();
		createdAt = in.readString();
		id = in.readString();
		judul = in.readString();
		status = in.readString();
	}

	public static final Creator<InfoResultItem> CREATOR = new Creator<InfoResultItem>() {
		@Override
		public InfoResultItem createFromParcel(Parcel in) {
			return new InfoResultItem(in);
		}

		@Override
		public InfoResultItem[] newArray(int size) {
			return new InfoResultItem[size];
		}
	};

	public void setEndAt(String endAt){
		this.endAt = endAt;
	}

	public String getEndAt(){
		return endAt;
	}

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setJudul(String judul){
		this.judul = judul;
	}

	public String getJudul(){
		return judul;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"end_at = '" + endAt + '\'' + 
			",img = '" + img + '\'' + 
			",keterangan = '" + keterangan + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",judul = '" + judul + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(endAt);
		dest.writeString(img);
		dest.writeString(keterangan);
		dest.writeString(createdAt);
		dest.writeString(id);
		dest.writeString(judul);
		dest.writeString(status);
	}
	public InfoResultItem(){

	}
}
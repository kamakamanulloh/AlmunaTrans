package com.softwarepintar.almuna_trans.Jadwal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Jadwal_Response {

	@SerializedName("result")
	private List<Jadwal_ResultItem> result;

	@SerializedName("value")
	private int value;

	public int getJumlahJadwal() {
		return jumlahJadwal;
	}

	public void setJumlahJadwal(int jumlahJadwal) {
		this.jumlahJadwal = jumlahJadwal;
	}

	@SerializedName("jumlahJadwal")
	private int jumlahJadwal;

	public void setResult(List<Jadwal_ResultItem> result){
		this.result = result;
	}

	public List<Jadwal_ResultItem> getResult(){
		return result;
	}

	public void setValue(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"result = '" + result + '\'' + 
			",value = '" + value + '\'' +
					",jumlahJadwal = '" + jumlahJadwal + '\'' +
					"}";
		}
}
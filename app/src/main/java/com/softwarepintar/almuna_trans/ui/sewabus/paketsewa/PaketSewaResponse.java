package com.softwarepintar.almuna_trans.ui.sewabus.paketsewa;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PaketSewaResponse {

	@SerializedName("result")
	private List<PaketSewaResultItem> result;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("value")
	private int value;

	public void setResult(List<PaketSewaResultItem> result){
		this.result = result;
	}

	public List<PaketSewaResultItem> getResult(){
		return result;
	}

	public void setJumlah(String jumlah){
		this.jumlah = jumlah;
	}

	public String getJumlah(){
		return jumlah;
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
			",jumlah = '" + jumlah + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}
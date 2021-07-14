package com.softwarepintar.almuna_trans.almunapay;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class RiwayatResponse {

	@SerializedName("result")
	private List<RiwayatResultItem> result;

	@SerializedName("value")
	private int value;

	public void setResult(List<RiwayatResultItem> result){
		this.result = result;
	}

	public List<RiwayatResultItem> getResult(){
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
			"}";
		}
}
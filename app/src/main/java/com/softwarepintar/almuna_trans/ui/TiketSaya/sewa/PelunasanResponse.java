package com.softwarepintar.almuna_trans.ui.TiketSaya.sewa;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PelunasanResponse {

	@SerializedName("result")
	private List<PelunasanResultItem> result;

	@SerializedName("value")
	private int value;

	public void setResult(List<PelunasanResultItem> result){
		this.result = result;
	}

	public List<PelunasanResultItem> getResult(){
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
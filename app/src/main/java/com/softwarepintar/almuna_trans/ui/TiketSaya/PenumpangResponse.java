package com.softwarepintar.almuna_trans.ui.TiketSaya;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PenumpangResponse {

	@SerializedName("result")
	private List<PenumpangResultItem> result;

	@SerializedName("value")
	private int value;

	public void setResult(List<PenumpangResultItem> result){
		this.result = result;
	}

	public List<PenumpangResultItem> getResult(){
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
package com.softwarepintar.almuna_trans.ui.TiketSaya;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TiketResponse {

	@SerializedName("result")
	private List<TiketResultItem> result;

	@SerializedName("value")
	private int value;

	public void setResult(List<TiketResultItem> result){
		this.result = result;
	}

	public List<TiketResultItem> getResult(){
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
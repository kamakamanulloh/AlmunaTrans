package com.softwarepintar.almuna_trans.ui.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfoResponse {

	@SerializedName("result")
	private List<InfoResultItem> result;

	@SerializedName("value")
	private int value;

	public void setResult(List<InfoResultItem> result){
		this.result = result;
	}

	public List<InfoResultItem> getResult(){
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
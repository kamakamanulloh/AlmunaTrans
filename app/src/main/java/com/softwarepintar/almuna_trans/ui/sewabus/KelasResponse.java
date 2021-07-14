package com.softwarepintar.almuna_trans.ui.sewabus;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KelasResponse {

	@SerializedName("result")
	private List<KelasResultItem> result;

	@SerializedName("value")
	private int value;

	public void setResult(List<KelasResultItem> result){
		this.result = result;
	}

	public List<KelasResultItem> getResult(){
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
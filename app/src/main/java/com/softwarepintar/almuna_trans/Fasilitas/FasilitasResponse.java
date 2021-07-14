package com.softwarepintar.almuna_trans.Fasilitas;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class FasilitasResponse {

	@SerializedName("result")
	private List<FasilitasResultItem> result;

	@SerializedName("value")
	private int value;

	public void setResult(List<FasilitasResultItem> result){
		this.result = result;
	}

	public List<FasilitasResultItem> getResult(){
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
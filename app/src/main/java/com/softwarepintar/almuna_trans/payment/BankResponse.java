package com.softwarepintar.almuna_trans.payment;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class BankResponse {

	@SerializedName("result")
	private List<BankResultItem> result;

	@SerializedName("value")
	private int value;

	public void setResult(List<BankResultItem> result){
		this.result = result;
	}

	public List<BankResultItem> getResult(){
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
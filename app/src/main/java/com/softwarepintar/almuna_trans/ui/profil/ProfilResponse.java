package com.softwarepintar.almuna_trans.ui.profil;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ProfilResponse {

	@SerializedName("result")
	private List<ProfilResultItem> result;

	@SerializedName("value")
	private int value;

	public void setResult(List<ProfilResultItem> result){
		this.result = result;
	}

	public List<ProfilResultItem> getResult(){
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
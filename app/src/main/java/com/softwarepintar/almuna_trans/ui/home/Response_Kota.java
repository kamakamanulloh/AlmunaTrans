package com.softwarepintar.almuna_trans.ui.home;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response_Kota {

	@SerializedName("resultasal")
	private List<ResultasalItem> resultasal;

	@SerializedName("value")
	private int value;

	@SerializedName("resultujuan")
	private List<ResultujuanItem> resultujuan;

	public void setResultasal(List<ResultasalItem> resultasal){
		this.resultasal = resultasal;
	}

	public List<ResultasalItem> getResultasal(){
		return resultasal;
	}

	public void setValue(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}

	public void setResultujuan(List<ResultujuanItem> resultujuan){
		this.resultujuan = resultujuan;
	}

	public List<ResultujuanItem> getResultujuan(){
		return resultujuan;
	}

	@Override
 	public String toString(){
		return 
			"Response_Kota{" +
			"resultasal = '" + resultasal + '\'' + 
			",value = '" + value + '\'' + 
			",resultujuan = '" + resultujuan + '\'' + 
			"}";
		}
}
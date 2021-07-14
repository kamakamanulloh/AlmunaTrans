package com.softwarepintar.almuna_trans.ui.home;

import com.google.gson.annotations.SerializedName;

public class ResultasalItem{

	@SerializedName("rute_mulai")
	private String ruteMulai;

	@SerializedName("asal")
	private String asal;

	public String getAsal() {
		return asal;
	}

	public void setAsal(String asal) {
		this.asal = asal;
	}

	public void setRuteMulai(String ruteMulai){
		this.ruteMulai = ruteMulai;
	}

	public String getRuteMulai(){
		return ruteMulai;
	}

	@Override
 	public String toString(){
		return 
			"ResultasalItem{" + 
			"rute_mulai = '" + ruteMulai + '\'' +
					"asal = '" + asal + '\'' +
					"}";
		}
}
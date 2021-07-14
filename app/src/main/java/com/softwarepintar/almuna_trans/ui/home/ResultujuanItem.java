package com.softwarepintar.almuna_trans.ui.home;

import com.google.gson.annotations.SerializedName;

public class ResultujuanItem{

	@SerializedName("rute_tujuan")
	private String ruteTujuan;

	@SerializedName("tujuan")
	private String tujuan;

	public String getTujuan() {
		return tujuan;
	}

	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}

	public void setRuteTujuan(String ruteTujuan){
		this.ruteTujuan = ruteTujuan;
	}

	public String getRuteTujuan(){
		return ruteTujuan;
	}

	@Override
 	public String toString(){
		return 
			"ResultujuanItem{" + 
			"rute_tujuan = '" + ruteTujuan + '\'' +
					"tujuan = '" + tujuan + '\'' +
					"}";
		}
}
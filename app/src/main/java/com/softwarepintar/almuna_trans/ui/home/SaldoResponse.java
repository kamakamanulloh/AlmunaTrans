package com.softwarepintar.almuna_trans.ui.home;

import com.google.gson.annotations.SerializedName;

public class SaldoResponse {

	@SerializedName("saldo")
	private String saldo;

	public void setSaldo(String saldo){
		this.saldo = saldo;
	}

	public String getSaldo(){
		return saldo;
	}
	@SerializedName("value")
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"saldo = '" + saldo + '\'' +
					"value = '" + value + '\'' +
					"}";
		}
}
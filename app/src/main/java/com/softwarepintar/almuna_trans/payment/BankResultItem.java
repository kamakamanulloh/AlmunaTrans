package com.softwarepintar.almuna_trans.payment;

import com.google.gson.annotations.SerializedName;


public class BankResultItem {

	@SerializedName("bank")
	private String bank;

	@SerializedName("img")
	private String img;

	@SerializedName("nama")
	private String nama;

	@SerializedName("id")
	private String id;

	@SerializedName("nomor")
	private String nomor;

	public void setBank(String bank){
		this.bank = bank;
	}

	public String getBank(){
		return bank;
	}

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNomor(String nomor){
		this.nomor = nomor;
	}

	public String getNomor(){
		return nomor;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"bank = '" + bank + '\'' + 
			",img = '" + img + '\'' + 
			",nama = '" + nama + '\'' + 
			",id = '" + id + '\'' + 
			",nomor = '" + nomor + '\'' + 
			"}";
		}
}
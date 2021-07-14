package com.softwarepintar.almuna_trans.ui.profil;

import com.google.gson.annotations.SerializedName;

public class ProfilResultItem {

	@SerializedName("password")
	private String password;

	@SerializedName("nama")
	private Object nama;

	@SerializedName("hp")
	private String hp;

	@SerializedName("jenis_id")
	private Object jenisId;

	@SerializedName("id")
	private String id;

	@SerializedName("no_id")
	private Object noId;

	@SerializedName("jenis_kelamin")
	private Object jenisKelamin;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	@SerializedName("alamat")
	private String alamat;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setNama(Object nama){
		this.nama = nama;
	}

	public Object getNama(){
		return nama;
	}

	public void setHp(String hp){
		this.hp = hp;
	}

	public String getHp(){
		return hp;
	}

	public void setJenisId(Object jenisId){
		this.jenisId = jenisId;
	}

	public Object getJenisId(){
		return jenisId;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNoId(Object noId){
		this.noId = noId;
	}

	public Object getNoId(){
		return noId;
	}

	public void setJenisKelamin(Object jenisKelamin){
		this.jenisKelamin = jenisKelamin;
	}

	public Object getJenisKelamin(){
		return jenisKelamin;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"password = '" + password + '\'' + 
			",nama = '" + nama + '\'' + 
			",hp = '" + hp + '\'' + 
			",jenis_id = '" + jenisId + '\'' + 
			",id = '" + id + '\'' + 
			",no_id = '" + noId + '\'' + 
			",jenis_kelamin = '" + jenisKelamin + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			",alamat = '" + alamat + '\'' + 
			"}";
		}
}
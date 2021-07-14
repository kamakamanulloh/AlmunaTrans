package com.softwarepintar.almuna_trans.ui.TiketSaya.sewa;

import com.google.gson.annotations.SerializedName;

public class PelunasanResultItem {

	@SerializedName("pelunasan_id")
	private String pelunasanId;

	@SerializedName("nominal")
	private String nominal;

	@SerializedName("kode")
	private String kode;

	@SerializedName("jenis")
	private String jenis;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("total_bayar")
	private String totalBayar;

	@SerializedName("jumlah_bus")
	private String jumlahBus;

	public void setPelunasanId(String pelunasanId){
		this.pelunasanId = pelunasanId;
	}

	public String getPelunasanId(){
		return pelunasanId;
	}

	public void setNominal(String nominal){
		this.nominal = nominal;
	}

	public String getNominal(){
		return nominal;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setJenis(String jenis){
		this.jenis = jenis;
	}

	public String getJenis(){
		return jenis;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setTotalBayar(String totalBayar){
		this.totalBayar = totalBayar;
	}

	public String getTotalBayar(){
		return totalBayar;
	}

	public void setJumlahBus(String jumlahBus){
		this.jumlahBus = jumlahBus;
	}

	public String getJumlahBus(){
		return jumlahBus;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"pelunasan_id = '" + pelunasanId + '\'' + 
			",nominal = '" + nominal + '\'' + 
			",kode = '" + kode + '\'' + 
			",jenis = '" + jenis + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",total_bayar = '" + totalBayar + '\'' + 
			",jumlah_bus = '" + jumlahBus + '\'' + 
			"}";
		}
}
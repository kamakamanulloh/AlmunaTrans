package com.softwarepintar.almuna_trans.ui.TiketSaya;

import com.google.gson.annotations.SerializedName;


public class PenumpangResultItem {

	@SerializedName("member_id")
	private String memberId;

	@SerializedName("rute_tujuan")
	private String ruteTujuan;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("berangkat")
	private String berangkat;

	@SerializedName("jenis_id")
	private String jenisId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("no_id")
	private String noId;

	@SerializedName("nm_penumpang")
	private String nmPenumpang;

	@SerializedName("estimasi")
	private String estimasi;

	@SerializedName("pembayaran_kode")
	private String pembayaranKode;

	@SerializedName("rute_asal")
	private String ruteAsal;

	@SerializedName("kd_tiket")
	private String kd_tiket;

	@SerializedName("armada_nama")
	private String armada_nama;

	public String getArmada_nama() {
		return armada_nama;
	}

	public void setArmada_nama(String armada_nama) {
		this.armada_nama = armada_nama;
	}

	public String getKd_tiket() {
		return kd_tiket;
	}

	public void setKd_tiket(String kd_tiket) {
		this.kd_tiket = kd_tiket;
	}

	public void setMemberId(String memberId){
		this.memberId = memberId;
	}

	public String getMemberId(){
		return memberId;
	}

	public void setRuteTujuan(String ruteTujuan){
		this.ruteTujuan = ruteTujuan;
	}

	public String getRuteTujuan(){
		return ruteTujuan;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setBerangkat(String berangkat){
		this.berangkat = berangkat;
	}

	public String getBerangkat(){
		return berangkat;
	}

	public void setJenisId(String jenisId){
		this.jenisId = jenisId;
	}

	public String getJenisId(){
		return jenisId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNoId(String noId){
		this.noId = noId;
	}

	public String getNoId(){
		return noId;
	}

	public void setNmPenumpang(String nmPenumpang){
		this.nmPenumpang = nmPenumpang;
	}

	public String getNmPenumpang(){
		return nmPenumpang;
	}

	public void setEstimasi(String estimasi){
		this.estimasi = estimasi;
	}

	public String getEstimasi(){
		return estimasi;
	}

	public void setPembayaranKode(String pembayaranKode){
		this.pembayaranKode = pembayaranKode;
	}

	public String getPembayaranKode(){
		return pembayaranKode;
	}

	public void setRuteAsal(String ruteAsal){
		this.ruteAsal = ruteAsal;
	}

	public String getRuteAsal(){
		return ruteAsal;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"member_id = '" + memberId + '\'' + 
			",rute_tujuan = '" + ruteTujuan + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",berangkat = '" + berangkat + '\'' + 
			",jenis_id = '" + jenisId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",no_id = '" + noId + '\'' + 
			",nm_penumpang = '" + nmPenumpang + '\'' + 
			",estimasi = '" + estimasi + '\'' + 
			",pembayaran_kode = '" + pembayaranKode + '\'' + 
			",rute_asal = '" + ruteAsal + '\'' + 
			"}";
		}
}
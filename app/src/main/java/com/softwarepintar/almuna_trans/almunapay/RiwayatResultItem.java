package com.softwarepintar.almuna_trans.almunapay;

import com.google.gson.annotations.SerializedName;


public class RiwayatResultItem {

	@SerializedName("member_id")
	private String memberId;

	@SerializedName("rekening_id")
	private String rekeningId;

	@SerializedName("keterangan")
	private Object keterangan;

	@SerializedName("cara")
	private String cara;

	@SerializedName("nominal")
	private String nominal;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("petugas_id")
	private String petugasId;

	@SerializedName("jenis")
	private String jenis;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("status")
	private String status;

	@SerializedName("kode")
	private String kode;

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public void setMemberId(String memberId){
		this.memberId = memberId;
	}

	public String getMemberId(){
		return memberId;
	}

	public void setRekeningId(String rekeningId){
		this.rekeningId = rekeningId;
	}

	public String getRekeningId(){
		return rekeningId;
	}

	public void setKeterangan(Object keterangan){
		this.keterangan = keterangan;
	}

	public Object getKeterangan(){
		return keterangan;
	}

	public void setCara(String cara){
		this.cara = cara;
	}

	public String getCara(){
		return cara;
	}

	public void setNominal(String nominal){
		this.nominal = nominal;
	}

	public String getNominal(){
		return nominal;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPetugasId(String petugasId){
		this.petugasId = petugasId;
	}

	public String getPetugasId(){
		return petugasId;
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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"member_id = '" + memberId + '\'' + 
			",rekening_id = '" + rekeningId + '\'' + 
			",keterangan = '" + keterangan + '\'' + 
			",cara = '" + cara + '\'' + 
			",nominal = '" + nominal + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",petugas_id = '" + petugasId + '\'' + 
			",jenis = '" + jenis + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' +
					",kode = '" + kode + '\'' +
			"}";
		}
}
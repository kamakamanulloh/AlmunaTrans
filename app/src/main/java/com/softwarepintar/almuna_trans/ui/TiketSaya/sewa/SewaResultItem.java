package com.softwarepintar.almuna_trans.ui.TiketSaya.sewa;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class SewaResultItem implements Parcelable {

	@SerializedName("member_id")
	private String memberId;

	@SerializedName("penjemputan")
	private String penjemputan;

	@SerializedName("daftar_sewa_bus_id")
	private String daftarSewaBusId;

	@SerializedName("batas_bayar")
	private String batasBayar;

	@SerializedName("lattitude")
	private String lattitude;

	@SerializedName("berangkat")
	private String berangkat;

	@SerializedName("longtitude")
	private String longtitude;

	@SerializedName("catatan")
	private String catatan;

	@SerializedName("kembali")
	private String kembali;

	@SerializedName("tujuan")
	private String tujuan;

	@SerializedName("harga")
	private String harga;

	@SerializedName("kode")
	private String kode;

	@SerializedName("id")
	private String id;

	@SerializedName("durasi")
	private String durasi;

	@SerializedName("sewa_id")
	private String sewaId;

	@SerializedName("status")
	private String status;

	@SerializedName("nama_kelas")
	private String nama_kelas;
	@SerializedName("jumlah_bus")
	private String jumlah_bus;
	@SerializedName("total")
	private String total;

	public String getJumlah_bus() {
		return jumlah_bus;
	}

	public void setJumlah_bus(String jumlah_bus) {
		this.jumlah_bus = jumlah_bus;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getNama_kelas() {
		return nama_kelas;
	}

	public void setNama_kelas(String nama_kelas) {
		this.nama_kelas = nama_kelas;
	}

	public void setMemberId(String memberId){
		this.memberId = memberId;
	}

	public String getMemberId(){
		return memberId;
	}

	public void setPenjemputan(String penjemputan){
		this.penjemputan = penjemputan;
	}

	public String getPenjemputan(){
		return penjemputan;
	}

	public void setDaftarSewaBusId(String daftarSewaBusId){
		this.daftarSewaBusId = daftarSewaBusId;
	}

	public String getDaftarSewaBusId(){
		return daftarSewaBusId;
	}

	public void setBatasBayar(String batasBayar){
		this.batasBayar = batasBayar;
	}

	public String getBatasBayar(){
		return batasBayar;
	}

	public void setLattitude(String lattitude){
		this.lattitude = lattitude;
	}

	public String getLattitude(){
		return lattitude;
	}

	public void setBerangkat(String berangkat){
		this.berangkat = berangkat;
	}

	public String getBerangkat(){
		return berangkat;
	}

	public void setLongtitude(String longtitude){
		this.longtitude = longtitude;
	}

	public String getLongtitude(){
		return longtitude;
	}

	public void setCatatan(String catatan){
		this.catatan = catatan;
	}

	public String getCatatan(){
		return catatan;
	}

	public void setKembali(String kembali){
		this.kembali = kembali;
	}

	public String getKembali(){
		return kembali;
	}

	public void setTujuan(String tujuan){
		this.tujuan = tujuan;
	}

	public String getTujuan(){
		return tujuan;
	}

	public void setHarga(String harga){
		this.harga = harga;
	}

	public String getHarga(){
		return harga;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDurasi(String durasi){
		this.durasi = durasi;
	}

	public String getDurasi(){
		return durasi;
	}

	public void setSewaId(String sewaId){
		this.sewaId = sewaId;
	}

	public String getSewaId(){
		return sewaId;
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
			",penjemputan = '" + penjemputan + '\'' + 
			",daftar_sewa_bus_id = '" + daftarSewaBusId + '\'' + 
			",batas_bayar = '" + batasBayar + '\'' + 
			",lattitude = '" + lattitude + '\'' + 
			",berangkat = '" + berangkat + '\'' + 
			",longtitude = '" + longtitude + '\'' + 
			",catatan = '" + catatan + '\'' + 
			",kembali = '" + kembali + '\'' + 
			",tujuan = '" + tujuan + '\'' + 
			",harga = '" + harga + '\'' + 
			",kode = '" + kode + '\'' + 
			",id = '" + id + '\'' + 
			",durasi = '" + durasi + '\'' + 
			",sewa_id = '" + sewaId + '\'' + 
			",status = '" + status + '\'' +
			",nama_kelas = '" + nama_kelas + '\'' +
					",total = '" + total + '\'' +
					",jumlah_bus = '" + jumlah_bus + '\'' +
					"}";
		}

	public SewaResultItem(){

	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.memberId);
		dest.writeString(this.penjemputan);
		dest.writeString(this.daftarSewaBusId);
		dest.writeString(this.batasBayar);
		dest.writeString(this.lattitude);
		dest.writeString(this.berangkat);
		dest.writeString(this.longtitude);
		dest.writeString(this.catatan);
		dest.writeString(this.kembali);
		dest.writeString(this.tujuan);
		dest.writeString(this.harga);
		dest.writeString(this.kode);
		dest.writeString(this.id);
		dest.writeString(this.durasi);
		dest.writeString(this.sewaId);
		dest.writeString(this.status);
		dest.writeString(this.nama_kelas);
		dest.writeString(this.jumlah_bus);
		dest.writeString(this.total);
	}

	protected SewaResultItem(Parcel in) {
		this.memberId = in.readString();
		this.penjemputan = in.readString();
		this.daftarSewaBusId = in.readString();
		this.batasBayar = in.readString();
		this.lattitude = in.readString();
		this.berangkat = in.readString();
		this.longtitude = in.readString();
		this.catatan = in.readString();
		this.kembali = in.readString();
		this.tujuan = in.readString();
		this.harga = in.readString();
		this.kode = in.readString();
		this.id = in.readString();
		this.durasi = in.readString();
		this.sewaId = in.readString();
		this.status = in.readString();
		this.nama_kelas = in.readString();
		this.jumlah_bus = in.readString();
		this.total = in.readString();
	}

	public static final Creator<SewaResultItem> CREATOR = new Creator<SewaResultItem>() {
		@Override
		public SewaResultItem createFromParcel(Parcel source) {
			return new SewaResultItem(source);
		}

		@Override
		public SewaResultItem[] newArray(int size) {
			return new SewaResultItem[size];
		}
	};
}
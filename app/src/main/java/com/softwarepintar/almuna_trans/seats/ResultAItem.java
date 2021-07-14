package com.softwarepintar.almuna_trans.seats;

import com.google.gson.annotations.SerializedName;

public class ResultAItem{

	@SerializedName("seat_nama")
	private String seatNama;

	@SerializedName("seat_row")
	private String seatRow;

	@SerializedName("seat_id")
	private String seatId;

	@SerializedName("status")
	private String status;

	public void setSeatNama(String seatNama){
		this.seatNama = seatNama;
	}

	public String getSeatNama(){
		return seatNama;
	}

	public void setSeatRow(String seatRow){
		this.seatRow = seatRow;
	}

	public String getSeatRow(){
		return seatRow;
	}

	public void setSeatId(String seatId){
		this.seatId = seatId;
	}

	public String getSeatId(){
		return seatId;
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
			"ResultAItem{" + 
			"seat_nama = '" + seatNama + '\'' + 
			",seat_row = '" + seatRow + '\'' + 
			",seat_id = '" + seatId + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
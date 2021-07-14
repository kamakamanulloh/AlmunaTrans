package com.softwarepintar.almuna_trans.Booking;

import com.google.gson.annotations.SerializedName;

public class BookingResponse {
    @SerializedName("value")
    private int value;
    @SerializedName("message")
    private String message;

    @SerializedName("batas_bayar")
    private String batas_bayar;

    @SerializedName("kode")
    private String kode;

    @SerializedName("kode_kembali")
    private String kode_kembali;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKode_kembali() {
        return kode_kembali;
    }

    public void setKode_kembali(String kode_kembali) {
        this.kode_kembali = kode_kembali;
    }

    public String getBatas_bayar() {
        return batas_bayar;
    }

    public void setBatas_bayar(String batas_bayar) {
        this.batas_bayar = batas_bayar;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

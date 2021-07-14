package com.softwarepintar.almuna_trans.Login;

import com.google.gson.annotations.SerializedName;

public class Login_Response {
    @SerializedName("value")
    private int value;
    @SerializedName("message")
    private String message;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("username")
    private String user_name;

    @SerializedName("nama")
    private String nama;
    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;
    @SerializedName("alamat")
    private String alamat;

    @SerializedName("email")
    private String email;
    @SerializedName("hp")
    private String hp;
    @SerializedName("no_id")
    private String no_id;
    @SerializedName("jenis_id")
    private String jenis_id;
    @SerializedName("nominal")
    private String nominal;

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getNo_id() {
        return no_id;
    }

    public void setNo_id(String no_id) {
        this.no_id = no_id;
    }

    public String getJenis_id() {
        return jenis_id;
    }

    public void setJenis_id(String jenis_id) {
        this.jenis_id = jenis_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

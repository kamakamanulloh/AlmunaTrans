package com.softwarepintar.almuna_trans.Register;

import com.google.gson.annotations.SerializedName;

public class Register_Response {

    @SerializedName("value")
    private int value;
    @SerializedName("message")
    private String message;


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

package com.example.usman.proretro.models;

/**
 * Created by usman on 15/03/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fuel {

    @SerializedName("userperson")
    @Expose
    private String userperson;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("litres")
    @Expose
    private Integer litres;
    @SerializedName("date")
    @Expose
    private String date;

    public String getUserperson() {
        return userperson;
    }

    public void setUserperson(String userperson) {
        this.userperson = userperson;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getLitres() {
        return litres;
    }

    public void setLitres(Integer litres) {
        this.litres = litres;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

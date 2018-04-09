package com.example.usman.proretro.models;

/**
 * Created by usman on 15/03/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fuel {

    @SerializedName("fuelId")
    @Expose
    private Long fuelId;
    @SerializedName("userperson")
    @Expose
    private String userperson;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("litres")
    @Expose
    private String litres;
    @SerializedName("date")
    @Expose
    private String date;

    public Fuel(Long fuelId, String userperson, String amount, String litres, String date) {
        this.fuelId = fuelId;
        this.userperson = userperson;
        this.amount = amount;
        this.litres = litres;
        this.date = date;
    }
    public Fuel(String userperson, String amount, String litres, String date) {
        this.userperson = userperson;
        this.amount = amount;
        this.litres = litres;
        this.date = date;
    }

    public Long getFuelId() {
        return fuelId;
    }

    public void setFuelId(Long fuelId) {
        this.fuelId = fuelId;
    }

    public String getUserperson() {
        return userperson;
    }

    public void setUserperson(String userperson) {
        this.userperson = userperson;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLitres() {
        return litres;
    }

    public void setLitres(String litres) {
        this.litres = litres;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

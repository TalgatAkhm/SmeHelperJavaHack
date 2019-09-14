package com.mipt.smehelper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @JsonProperty("name")
    private String name;

    @JsonProperty("job")
    private String job;

    @JsonProperty("regDate")
    private String regDate;

    @JsonProperty("isSmallUSN")
    private boolean isSmallUSN;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("city")
    private String city;

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getRegDate() {
        return regDate;
    }

    public boolean isSmallUSN() {
        return isSmallUSN;
    }

    public double getAmount() {
        return amount;
    }

    public String getCity() {
        return city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public void setSmallUSN(boolean smallUSN) {
        isSmallUSN = smallUSN;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

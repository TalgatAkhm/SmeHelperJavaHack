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
}

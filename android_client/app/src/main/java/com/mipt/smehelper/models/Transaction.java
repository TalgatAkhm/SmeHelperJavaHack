package com.mipt.smehelper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    @JsonProperty("isIncome")
    private boolean isIncome;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("purpose")
    private String purpose;

    @JsonProperty("realString")
    private String realComment;

    @JsonProperty("officialComment")
    private String officialComment;

    // May be remove se/deserialize
    @JsonDeserialize(using = NumberDeserializers.NumberDeserializer.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("userId")
    private Integer userId;

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRealComment() {
        return realComment;
    }

    public void setRealComment(String realComment) {
        this.realComment = realComment;
    }

    public String getOfficialComment() {
        return officialComment;
    }

    public void setOfficialComment(String officialComment) {
        this.officialComment = officialComment;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
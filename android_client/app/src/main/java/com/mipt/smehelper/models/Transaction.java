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

    @JsonProperty("date")
    private String date;

    // May be remove se/deserialize
    @JsonDeserialize(using = NumberDeserializers.NumberDeserializer.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("userId")
    private Integer userId;

    public boolean isIncome() {
        return isIncome;
    }

    public double getAmount() {
        return amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getRealComment() {
        return realComment;
    }

    public String getOfficialComment() {
        return officialComment;
    }
    
    public String getDate() {
        return date;
    }

    public Integer getUserId() {
        return userId;
    }
}
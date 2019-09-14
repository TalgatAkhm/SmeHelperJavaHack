package com.mipt.smehelper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification {
    @JsonProperty("isNalog")
    private boolean isNalog;

    @JsonProperty("title")
    private String title;

    @JsonProperty("text")
    private String text;

    @JsonProperty("date")
    private String date;

    public boolean isNalog() {
        return isNalog;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }
}

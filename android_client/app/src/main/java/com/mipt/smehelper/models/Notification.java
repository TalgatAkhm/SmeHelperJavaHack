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

    public boolean isNalog() {
        return isNalog;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public void setNalog(boolean nalog) {
        isNalog = nalog;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }
}

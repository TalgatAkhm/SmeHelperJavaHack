package com.mipt.smehelper.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepIds {
    @JsonProperty("ids")
    private String ids;

    public String getIds() {
        return ids;
    }
}

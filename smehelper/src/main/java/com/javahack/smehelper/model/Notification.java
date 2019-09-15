package com.javahack.smehelper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.sf.autodao.PersistentEntity;

import javax.persistence.*;

@Entity
public class Notification implements PersistentEntity<Integer> {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    @JsonProperty("isNalog")
    private Boolean isNalog;

    @JsonProperty("title")
    private String title;

    @JsonProperty("text")
    @Column
    @Lob
    private String text;

//    @JsonProperty("date")
//    @Transient
//    private String date;

    public boolean isNalog() {
        return isNalog;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

//    public String getDate() {
//        return date;
//    }

    public void setNalog(Boolean nalog) {
        isNalog = nalog;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

//    public void setDate(String date) {
//        this.date = date;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getPrimaryKey() {
        return this.id;
    }
}

package com.javahack.smehelper.model;

import net.sf.autodao.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Notification implements PersistentEntity<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    private Boolean isNalog;
    private String title;
    private String text;

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

    public void setNalog(Boolean nalog) {
        isNalog = nalog;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

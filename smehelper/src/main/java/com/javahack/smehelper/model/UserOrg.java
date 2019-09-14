package com.javahack.smehelper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.sf.autodao.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserOrg implements PersistentEntity<Integer> {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("job")
    private String job;

    @JsonProperty("isSmallUSN")
    private Boolean isSmallUSN;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("city")
    private String city;

    private String regDate;

    private String orgName;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setSmallUSN(Boolean smallUSN) {
        isSmallUSN = smallUSN;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    @Override
    public Integer getPrimaryKey() {
        return this.id;
    }
}

package com.javahack.smehelper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.sf.autodao.PersistentEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class JobAndDependencies implements PersistentEntity<Integer>{

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    @JsonProperty("avitoId")
    private Integer avitoId;

    @JsonProperty("job")
    private String job;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="location")
//    @OrderColumn
    @ElementCollection
    @JsonProperty("dependencies")
    private List<Integer> avitoIdDependencies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAvitoId() {
        return avitoId;
    }

    public void setAvitoId(Integer avitoId) {
        this.avitoId = avitoId;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public List<Integer> getDependencies() {
        return avitoIdDependencies;
    }

    public void setDependencies(List<Integer> dependencies) {
        this.avitoIdDependencies = dependencies;
    }

    @Override
    public Integer getPrimaryKey() {
        return this.id;
    }
}

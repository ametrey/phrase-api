package com.rooftop.seniorityboost.challenge.entities;

import java.util.Map;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "text")
@SQLDelete(sql = "UPDATE text SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@JsonIgnoreProperties(ignoreUnknown = true, value = 
{"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Text {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String hash;
    private Integer chars;
    private String url;

    @JsonIgnore
    private boolean deleted = Boolean.FALSE;

    @JsonIgnore
    private String text;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "res")
    @CollectionTable(name = "result", joinColumns = @JoinColumn(name = "id"))
    @OrderBy(value = "id")
    private Map<String, Integer> results;

    public Text() {
    }

    public Text(Integer id, Integer chars, String text) {
        this.id = id;
        this.chars = chars;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getChars() {
        return chars;
    }

    public void setChars(Integer chars) {
        this.chars = chars;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Integer> getResults() {
        return results;
    }

    public void setResults(Map<String, Integer> results) {
        this.results = results;
    }

 

}

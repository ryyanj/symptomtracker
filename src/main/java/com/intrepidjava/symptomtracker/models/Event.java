package com.intrepidjava.symptomtracker.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "events")
public class Event {

    @Id
    private String id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String userid;

    @NotNull
    private Timestamp timestamp;

    @NotEmpty
    private Map<String,String> symptom = new HashMap<>();

    private String latitue;

    private String longitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, String> getSymptom() {
        return symptom;
    }

    public void setSymptom(Map<String, String> symptom) {
        this.symptom = symptom;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLatitue() {
        return latitue;
    }

    public void setLatitue(String latitue) {
        this.latitue = latitue;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}

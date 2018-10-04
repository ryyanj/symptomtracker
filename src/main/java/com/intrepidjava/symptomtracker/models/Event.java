package com.intrepidjava.symptomtracker.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "events")
public class Event {

    @Id
    private String id;

    @NotEmpty
    private String username;

    @NotEmpty
    private Map<String,Integer> symptom = new HashMap<>();

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

    public Map<String, Integer> getSymptom() {
        return symptom;
    }

    public void setSymptom(Map<String, Integer> symptom) {
        this.symptom = symptom;
    }

}

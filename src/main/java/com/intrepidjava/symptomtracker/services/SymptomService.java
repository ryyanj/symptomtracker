package com.intrepidjava.symptomtracker.services;

import com.intrepidjava.symptomtracker.models.User;
import com.intrepidjava.symptomtracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.logging.Logger;

@Service
public class SymptomService {

    @Autowired
    private UserRepository userRepository;

    public void addSymptom(User user, String symptom) {
        if(symptomHasNotBeenAdded(symptom,user.getSymptoms())) {
            user.getSymptoms().add(symptom);
            userRepository.save(user);
        }
    }

    public void removeSymptoms(User user, List<String> symptoms) {
        user.getSymptoms().removeAll(symptoms);
        userRepository.save(user);
    }

    private boolean symptomHasNotBeenAdded(String symptom, Set<String> symptoms) {
        List<String> symptomList = new ArrayList<>();
        symptomList.addAll(symptoms);
        for(String s: symptomList) {
            if(symptom.toLowerCase().equals(s.toLowerCase())) return false;
        }
        return true;
    }

}
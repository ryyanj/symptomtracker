package com.intrepidjava.symptomtracker.services;

import com.intrepidjava.symptomtracker.models.User;
import com.intrepidjava.symptomtracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SymptomService {

    @Autowired
    private UserRepository userRepository;

    public void addSymptom(User user, String symptom) {
        user.getSymptoms().add(symptom);
        userRepository.save(user);
    }

    public void removeSymptoms(User user, List<String> symptoms) {
        user.getSymptoms().removeAll(symptoms);
        userRepository.save(user);
    }

}
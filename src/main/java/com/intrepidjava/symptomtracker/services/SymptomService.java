package com.intrepidjava.symptomtracker.services;

import com.intrepidjava.symptomtracker.models.Event;
import com.intrepidjava.symptomtracker.models.Role;
import com.intrepidjava.symptomtracker.models.User;
import com.intrepidjava.symptomtracker.repositories.EventRepository;
import com.intrepidjava.symptomtracker.repositories.RoleRepository;
import com.intrepidjava.symptomtracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
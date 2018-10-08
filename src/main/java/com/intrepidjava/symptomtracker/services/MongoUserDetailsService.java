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
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         User user = userRepository.findByEmail(email);

        if(user == null) throw new UsernameNotFoundException("User not found");

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role: user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

    }

    public User findByUsername(String username) {
        return userRepository.findByEmail(username);
    }

    public void registerUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    public void addEvent(User user, Map<String,String> eventMap) {
        Event event = new Event();
        event.setUsername(user.getEmail());
        event.setUserid(user.getId());
        event.setTimestamp(new Timestamp(System.currentTimeMillis()));

        if(eventMap.containsKey("Latitude")) {
            event.setLatitude(eventMap.get("Latitude"));
            eventMap.remove("Latitude");
        }

        if(eventMap.containsKey("Longitude")) {
            event.setLongitude(eventMap.get("Longitude"));
            eventMap.remove("Longitude");
        }

        event.setSymptom(eventMap);
        eventRepository.save(event);
    }
}
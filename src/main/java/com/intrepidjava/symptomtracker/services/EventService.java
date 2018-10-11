package com.intrepidjava.symptomtracker.services;

import com.intrepidjava.symptomtracker.models.Event;
import com.intrepidjava.symptomtracker.models.User;
import com.intrepidjava.symptomtracker.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public void addEvent(User user, Map<String,String> eventMap) {
        Event event = new Event();
        event.setUsername(user.getEmail());
        event.setUserid(user.getId());
        event.setTimestamp(new Date());

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

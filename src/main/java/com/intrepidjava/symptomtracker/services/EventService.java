package com.intrepidjava.symptomtracker.services;

import com.intrepidjava.symptomtracker.models.Event;
import com.intrepidjava.symptomtracker.models.User;
import com.intrepidjava.symptomtracker.models.charts.linechart.*;
import com.intrepidjava.symptomtracker.repositories.EventRepository;
import com.intrepidjava.symptomtracker.repositories.EventRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventRepositoryImpl eventRepositoryImpl;

    public void addEvent(User user, Map<String,String> eventMap) {
        Event event = new Event();
        event.setUsername(user.getEmail());
        event.setUserid(user.getId());
        event.setTimestamp(new Date());

        //the event map is embedded with the
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

    public List<Event> getEvents(User user) {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -20);
        Date from = cal.getTime();
        Date to = Calendar.getInstance().getTime();
        List<Event> events = eventRepositoryImpl.findByusernameAndtimestampBetween(user.getId(),from,to);
        return events;
    }

}

package com.intrepidjava.symptomtracker.controllers;

import com.intrepidjava.symptomtracker.models.Event;
import com.intrepidjava.symptomtracker.models.User;
import com.intrepidjava.symptomtracker.services.EventService;
import com.intrepidjava.symptomtracker.services.MongoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EventController {

    @Autowired
    private MongoUserDetailsService mongoUserDetailsService;

    @Autowired
    private EventService eventService;

    @GetMapping("/recordevent")
    public ModelAndView getRecordEvent(@RequestParam String param) {
        String[] params = param.split(",");
        List<String> symptoms = new ArrayList<>();
        for(int i = 0; i < params.length; i++) {
            symptoms.add(params[i].trim());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("symptoms",symptoms);
        modelAndView.setViewName("recordevent");
        return modelAndView;
    }

    @PostMapping("/recordevent")
    public ModelAndView postRecordEvent(@RequestBody String str , Principal principal) {
        User user = mongoUserDetailsService.findByUsername(principal.getName());
        String[] pairs = str.split("&");
        Map<String,String> map = new HashMap<>();

        for(int i = 0; i < pairs.length; i++) {
            String[] pair = pairs[i].split("=");
            map.put(cleanString(pair[0]),cleanString(pair[1]));
        }
        eventService.addEvent(user,map);
        return new ModelAndView("redirect:/dashboard");
    }

    @GetMapping("/getevents")
    @ResponseBody
    public List<Event> getEvents(Principal principal) {
        User user = mongoUserDetailsService.findByUsername(principal.getName());
        return eventService.getEvents(user);
    }

    private String cleanString(String str) {
        return str.trim().replaceAll("\\+"," ");
    }

}

package com.intrepidjava.symptomtracker.controllers;

import com.intrepidjava.symptomtracker.models.User;
import com.intrepidjava.symptomtracker.services.MongoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Set;


@RestController
public class ChartController {

    @Autowired
    private MongoUserDetailsService mongoUserDetailsService;


    @GetMapping("/chart")
    public ModelAndView getChart(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        User user = mongoUserDetailsService.findByUsername(principal.getName());
        Set<String> symptoms = user.getSymptoms();
        modelAndView.addObject("symptoms",symptoms);
        modelAndView.setViewName("chart");
        return modelAndView;
    }


}

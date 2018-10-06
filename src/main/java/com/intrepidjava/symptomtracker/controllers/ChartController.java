package com.intrepidjava.symptomtracker.controllers;

import com.intrepidjava.symptomtracker.services.MongoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class ChartController {

    @Autowired
    private MongoUserDetailsService mongoUserDetailsService;


    @GetMapping("/chart")
    public ModelAndView getChart() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("chart");
        return modelAndView;
    }


}

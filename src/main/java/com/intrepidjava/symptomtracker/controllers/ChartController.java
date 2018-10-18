package com.intrepidjava.symptomtracker.controllers;

import com.intrepidjava.symptomtracker.models.Event;
import com.intrepidjava.symptomtracker.models.charts.linechart.ChartData;
import com.intrepidjava.symptomtracker.models.charts.linechart.DataSet;
import com.intrepidjava.symptomtracker.models.charts.linechart.LineChart;
import com.intrepidjava.symptomtracker.models.User;
import com.intrepidjava.symptomtracker.services.ChartService;
import com.intrepidjava.symptomtracker.services.EventService;
import com.intrepidjava.symptomtracker.services.MongoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Set;


@RestController
public class ChartController {

    @Autowired
    private MongoUserDetailsService mongoUserDetailsService;

    @Autowired
    private EventService eventService;

    @Autowired
    private ChartService chartService;


    @GetMapping("/chart")
    public ModelAndView getChart(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        User user = mongoUserDetailsService.findByUsername(principal.getName());
        Set<String> symptoms = user.getSymptoms();
        modelAndView.addObject("symptoms",symptoms);
        modelAndView.setViewName("chart");
        return modelAndView;
    }

    @GetMapping("/chartData")
    @ResponseBody
    public ChartData getChartData(Principal principal) {
        User user = mongoUserDetailsService.findByUsername(principal.getName());
        List<Event> events = eventService.getEvents(user);
        List<Date> labelData = chartService.getLabelData(user,events);
        List<DataSet> dataSetData = chartService.getDataSetData(user,events);
        ChartData chartData = new ChartData();
        chartData.setLabels(labelData);
        chartData.setDataSets(dataSetData);
        return chartData;
    }

}

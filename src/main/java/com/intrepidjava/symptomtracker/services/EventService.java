package com.intrepidjava.symptomtracker.services;

import com.intrepidjava.symptomtracker.models.Event;
import com.intrepidjava.symptomtracker.models.User;
import com.intrepidjava.symptomtracker.models.charts.linechart.*;
import com.intrepidjava.symptomtracker.repositories.EventRepository;
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

    public LineChart getChartData(String symptoms, String range) {
        String type = "line";
        Data data = new Data();
        data.setLabels(getLabels());
        data.setDatasets(getDataSets());
        Options options = new Options();
        options.setTitle(getTitle());

        LineChart lineChart = new LineChart(type,data,options);
        return lineChart;
    }

    private Title getTitle() {
        Title title = new Title();
        title.setDispaly(true);
        title.setText("symptom data");
        return title;
    }


    private List<Integer> getLabels() {

        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(2);list.add(3);
        return list;

    }

    private List<DataSet> getDataSets() {
        DataSet dataSet = new DataSet();
        List<Integer> list = new ArrayList<>();
        list.add(0);list.add(1);
        dataSet.setData(list);
        dataSet.setLabel("Africa");
        dataSet.setBorderColor("green");
        dataSet.setFill(false);
        List<DataSet> dataSetList = new ArrayList<>();
        dataSetList.add(dataSet);
        return dataSetList;
    }

}

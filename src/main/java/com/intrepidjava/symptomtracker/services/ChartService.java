package com.intrepidjava.symptomtracker.services;

import com.intrepidjava.symptomtracker.models.Event;
import com.intrepidjava.symptomtracker.models.charts.linechart.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ChartService {

    @Autowired
    private EventService eventService;

    public List<Date> getLabelData(List<Event> events) {
        List<Date> labelData = new ArrayList<>();
        for(Event event: events) {
            labelData.add(event.getTimestamp());
        }
        return labelData;
    }

//    public List<DataSet> getDataSetData(List<Event> events) {
//        List<DataSet> dataSetData = new ArrayList<>();
//        for(int i = 0; i < events.size(); i++) {
//            Map.Entry<String,String> entry = events.get(0).getSymptom().entrySet().iterator().next();
//            DataSet dataSet = new DataSet();
//            dataSet.setLabel(entry.getKey());
//            List<String> data = new ArrayList<>();
//            for(int i = 0; i < events.size(); i++) {
//                entry
//                DataSet dataSet = new DataSet();
//                dataSet.
//            }
//        }
//
//    }

//    public List<DataSet> getDataSetData(List<Event> events) {
//
//
//    }





}

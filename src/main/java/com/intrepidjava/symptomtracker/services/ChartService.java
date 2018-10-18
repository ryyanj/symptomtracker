package com.intrepidjava.symptomtracker.services;

import com.intrepidjava.symptomtracker.models.Event;
import com.intrepidjava.symptomtracker.models.User;
import com.intrepidjava.symptomtracker.models.charts.linechart.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ChartService {

    @Autowired
    private EventService eventService;

    public List<Date> getLabelData(User user, List<Event> events) {
        List<Date> labelData = new ArrayList<>();
        for(Event event: events) {
            if(eventHasActiveSymptom(user.getSymptoms(), event))
                labelData.add(event.getTimestamp());
        }
        return labelData;
    }

    public List<DataSet> getDataSetData(User user, List<Event> events) {
        Set<String> userSymptoms = user.getSymptoms();
        List<DataSet> dataSets = new ArrayList<>();
        for(String symptom: userSymptoms) {
            DataSet dataSet = new DataSet();
            dataSet.setLabel(symptom);
            List<Integer> data = new ArrayList<>();
            for(int i = 0; i < events.size(); i++) {
                Map<String,String> symptomMap = events.get(i).getSymptom();
                if(eventHasActiveSymptom(user.getSymptoms(),events.get(i)))
                    if(symptomMap.containsKey(symptom))
                        data.add(Integer.parseInt(symptomMap.get(symptom)));
                    else
                        data.add(null);
            }
            dataSet.setData(data);
            dataSet.setHidden(true);
            dataSets.add(dataSet);
        }
        return dataSets;

    }

    private boolean eventHasActiveSymptom(Set<String> symptoms, Event event) {
        for(String symptom: symptoms) {
            if(event.getSymptom().containsKey(symptom)) return true;
        }
        return false;
    }





}

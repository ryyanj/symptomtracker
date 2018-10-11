package com.intrepidjava.symptomtracker.repositories;

import com.intrepidjava.symptomtracker.models.Event;

import java.util.Date;
import java.util.List;

public interface EventRepositoryCustom {

    List<Event> findByusernameAndtimestampBetween(String username, Date from, Date to);

}

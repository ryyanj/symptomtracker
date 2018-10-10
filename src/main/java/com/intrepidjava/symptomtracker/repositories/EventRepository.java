package com.intrepidjava.symptomtracker.repositories;

import com.intrepidjava.symptomtracker.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

    List<Event> findBytimestampBetween(Date from, Date to);



}

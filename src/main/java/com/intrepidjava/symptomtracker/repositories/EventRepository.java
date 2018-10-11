package com.intrepidjava.symptomtracker.repositories;

import com.intrepidjava.symptomtracker.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event, String>, EventRepositoryCustom {

}

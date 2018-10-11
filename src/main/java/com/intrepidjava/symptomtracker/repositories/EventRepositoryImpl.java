package com.intrepidjava.symptomtracker.repositories;

import com.intrepidjava.symptomtracker.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;

public class EventRepositoryImpl implements EventRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Event> findByusernameAndtimestampBetween(String username, Date from, Date to) {
        Criteria lte = Criteria.where("timestamp").lte(to);
        Criteria between = Criteria.where("timestamp").gte(from).andOperator(lte);
        Criteria criteria = Criteria.where("username").is(username).andOperator(between);
        Query query = new Query(criteria);
        return mongoTemplate.find(query,Event.class);

    }

}

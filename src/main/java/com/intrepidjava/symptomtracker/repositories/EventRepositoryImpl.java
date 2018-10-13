package com.intrepidjava.symptomtracker.repositories;

import com.intrepidjava.symptomtracker.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;

public class EventRepositoryImpl implements EventRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Event> findByusernameAndtimestampBetween(String userid, Date from, Date to) {
        Criteria lte = Criteria.where("timestamp").lte(to);
        Criteria between = Criteria.where("timestamp").gte(from).andOperator(lte);
        Criteria criteria = Criteria.where("userid").is(userid).andOperator(between);
        Query query = new Query(criteria);
        query.with(new Sort(Direction.ASC, "timestamp"));
        return mongoTemplate.find(query,Event.class);

    }

}

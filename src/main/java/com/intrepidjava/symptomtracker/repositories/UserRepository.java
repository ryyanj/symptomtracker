package com.intrepidjava.symptomtracker.repositories;

import com.intrepidjava.symptomtracker.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

      User findByEmail(String email);

}

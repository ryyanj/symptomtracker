package com.intrepidjava.symptomtracker.repositories;

import com.intrepidjava.symptomtracker.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);

}

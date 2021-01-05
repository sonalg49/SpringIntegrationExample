package com.example.sampleIntegrationApplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.sampleIntegrationApplication.model.Person;

public interface PersonRepository extends MongoRepository<Person, String>{

}

package com.example.sampleIntegrationApplication.service;

import java.util.List;

import com.example.sampleIntegrationApplication.model.Person;

public interface PersonService {

	Person getPerson(String id);
	
	List<Person> getAllPersons();
	
	Person updatePerson(Person person);
	
	Person insertPerson(Person person);
	
	void deletePerson(String id);
	
}

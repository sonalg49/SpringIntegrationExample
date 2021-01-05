package com.example.sampleIntegrationApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sampleIntegrationApplication.model.Person;
import com.example.sampleIntegrationApplication.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	PersonRepository personRepository;

	@Override
	public Person getPerson(String id) {
		Optional<Person> person = personRepository.findById(id);
		if(person.isPresent())	{
			return person.get();
		}
		else
			return null;
	}
	
	@Override
	public Person updatePerson(Person person) {
		Optional<Person> per = personRepository.findById(person.getId());
		if(per.isPresent()) {
			Person updatePerson = per.get();
			updatePerson.setName(person.getName());
			updatePerson.setAge(person.getAge());
			Person savedPerson = personRepository.save(updatePerson);
			return savedPerson;
		}
		else
			return null;
	}

	@Override
	public Person insertPerson(Person person) {
		
		Person savedPerson = personRepository.save(person);
		return savedPerson;
	}

	@Override
	public void deletePerson(String id) {
		
		Optional<Person> per = personRepository.findById(id);
		if(per.isPresent()) {
			personRepository.deleteById(id);
		}
	}

	@Override
	public List<Person> getAllPersons() {
		List<Person> personList = personRepository.findAll();
		if(!personList.isEmpty())
			return personList;
		else
			return null;
		
	}
	
	

}

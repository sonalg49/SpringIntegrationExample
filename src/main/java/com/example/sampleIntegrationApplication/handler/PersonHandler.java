package com.example.sampleIntegrationApplication.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.example.sampleIntegrationApplication.model.Person;
import com.example.sampleIntegrationApplication.service.PersonService;

@Component
public class PersonHandler {
	
	private static final String STATUSCODE_HEADER = "http_statusCode";
	
	@Autowired
	private PersonService service;
	
	public Message<?> get(Message<String> msg) {
		String id = msg.getPayload();
		Person person = service.getPerson(id);
		
		if (person == null) {
			return MessageBuilder.fromMessage(msg)
					.copyHeadersIfAbsent(msg.getHeaders())
					.setHeader(STATUSCODE_HEADER, HttpStatus.NOT_FOUND)
					.build(); 
		}
		
		return MessageBuilder.withPayload(person)
				.copyHeadersIfAbsent(msg.getHeaders())
				.setHeader(STATUSCODE_HEADER, HttpStatus.OK)
				.build();
	}
	
	public Message<?> put(Message<Person> msg) {
		
		Person person  = service.updatePerson(msg.getPayload());
		
		if (person == null) {
			return MessageBuilder.fromMessage(msg)
					.copyHeadersIfAbsent(msg.getHeaders())
					.setHeader(STATUSCODE_HEADER, HttpStatus.NOT_FOUND)
					.build();
			
		}	
			return MessageBuilder.withPayload(person)
					.copyHeadersIfAbsent(msg.getHeaders())
					.setHeader(STATUSCODE_HEADER, HttpStatus.OK)
					.build();
	}
	
	public Message<?> post(Message<Person> msg) {
		Person person = service.insertPerson(msg.getPayload());
//		if (person == null) {
//			return MessageBuilder.fromMessage(msg)
//					.copyHeadersIfAbsent(msg.getHeaders())
//					.setHeader(STATUSCODE_HEADER, HttpStatus.NOT_FOUND)
//					.build();
//			
//		}	
			return MessageBuilder.withPayload(person)
					.copyHeadersIfAbsent(msg.getHeaders())
					.setHeader(STATUSCODE_HEADER, HttpStatus.OK)
					.build();
		
	}
	
	public void delete(Message<String> msg) {
		String id = msg.getPayload();
		service.deletePerson(id);
	}
	
	public Message<?> getAll(Message<?> msg) {
		List<Person> personLst = service.getAllPersons();
		return MessageBuilder.withPayload(personLst).copyHeadersIfAbsent(msg.getHeaders())
				.setHeader("http_statusCode", HttpStatus.OK).build();
	}
	
}

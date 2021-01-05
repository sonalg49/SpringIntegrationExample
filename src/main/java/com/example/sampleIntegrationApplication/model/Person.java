package com.example.sampleIntegrationApplication.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "persons")
public class Person {
	  @Id
	  private String id;
	  private String name;
	  private int age;

	  public Person() {

	  }

	  public Person(String id, String name, int age) {
	    this.id = id;
	    this.name = name;
	    this.age = age;
	  }

	  public void setId(String id) {
		  this.id = id;
	  }
	  
	  public String getId() {
	    return id;
	  }

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public int getAge() {
	    return age;
	  }

	  public void setAge(int age) {
	    this.age = age;
	  }
	  
	  @Override
	  public String toString() {
	    return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
	  }
	}
	
	


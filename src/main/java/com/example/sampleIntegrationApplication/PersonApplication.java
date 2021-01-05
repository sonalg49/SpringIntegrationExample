package com.example.sampleIntegrationApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.dsl.Http;

import com.example.sampleIntegrationApplication.model.Person;

@SpringBootApplication
public class PersonApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonApplication.class, args);
	}
	
	@Bean
	 public IntegrationFlow inboundGetById() {
		  return IntegrationFlows.from(Http.inboundGateway("/getById/{id}")
				  							.requestMapping(m -> m.methods(HttpMethod.GET)
				  							.consumes("application/json").produces("application/json"))
				  							.payloadExpression("#pathVariables.id"))
				  				//.channel("httpGetChannel")
				  				.handle("personHandler", "get")
				  				.get();
	  }
	 
	
	@Bean
   public IntegrationFlow inboundAddPerson() {
      return IntegrationFlows.from(
              Http.inboundGateway("/addPerson")
                      .requestMapping(r -> r.methods(HttpMethod.POST)
                              .consumes("application/json"))
                      .requestPayloadType(Person.class))
    		  //.channel("httpPostChannel")
              .handle("personHandler", "post")
              .get();
  }
	
	@Bean
	public IntegrationFlow inBoundUpdatePerson() {
		return IntegrationFlows.from(
	              Http.inboundGateway("/updatePerson")
	                      .requestMapping(r -> r.methods(HttpMethod.PUT)
	                              .consumes("application/json"))
	                      .requestPayloadType(Person.class))
	    		 //.channel("httpPutChannel")
	              .handle("personHandler", "put")
	              .get();
	}

	
	@Bean
	public IntegrationFlow inBoundDeletePerson() {
		return IntegrationFlows.from(
	              Http.inboundGateway("/deletePerson/{id}")
	                      .requestMapping(r -> r.methods(HttpMethod.DELETE)
	                              .consumes("application/json").produces("application/json"))
	                      .payloadExpression("#pathVariables.id")
	                      .statusCodeExpression("T(org.springframework.http.HttpStatus).NO_CONTENT"))
	    		 //.channel("httpDelChannel")
	              .handle("personHandler", "delete")
	              .get();
	}
	
	@Bean
	 public IntegrationFlow inboundGetAll() {
		  return IntegrationFlows.from(Http.inboundGateway("/getall")
				  							.requestMapping(m -> m.methods(HttpMethod.GET)
				  							.consumes("application/json").produces("application/json")))
				  				//.channel("httpGetAllChannel")
				  				.handle("personHandler", "getAll")
				  				.get();
	  }
	
	

}

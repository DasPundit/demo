package com.my.playground.demo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.github.fakemongo.Fongo;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.Mongo;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;  

public class MongoInMemory {
	
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private CustomerRepository customerRepository;
 
    @Rule
    public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("demo-test");

    
	@Test
    @UsingDataSet(locations = "testData.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	public void firstNameIsAlice() {
        
		List<Customer> listOfAlices = customerRepository.findByFirstName("Alice"); 
		
		if (!listOfAlices.isEmpty()){                                                
			String actualFirstName = listOfAlices.get(0).getFirstName();             
			String expectedFirstName = "Alice";                                      
			assertEquals(expectedFirstName, actualFirstName);                        
		} else {                                                                     
			assertTrue("Alice was not found", false);                                
		}                                                                            
        
	}
	
	@Configuration
    @EnableMongoRepositories
    @ComponentScan(basePackageClasses = {CustomerRepository.class})
    static class CustomerRepositoryConfiguration extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "demo-test";
	}

	@Override
	public Mongo mongo() throws Exception {
        Fongo queued = new Fongo("testData");
        return queued.getMongo();
	}
 }
}

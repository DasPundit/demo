package com.my.playground.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoMock {

	@Mock 
	private CustomerRepository customerRepository;
	
	private List<Customer> customerList = new ArrayList<>();
	private Customer aliceList = new Customer("Alice", "Smith","1234 Streed Dr");
	private Customer bobList = new Customer("Bob",   "Smith","4321 Drive St.");
	
	private List<Customer> getCustListAlice(){
		customerList.add(aliceList);
		return customerList;
	}
	
	private List<Customer> getCustSmiths(){
		customerList.add(aliceList);
		customerList.add(bobList);
		return customerList;
	}
	
	@Test
	public void firstNameIsAliceMock() {
		String alice = "Alice";

		when(customerRepository.findByFirstName(alice)).thenReturn(getCustListAlice());
	
		List<Customer> listOfAlices = customerRepository.findByFirstName(alice);
		if (!listOfAlices.isEmpty()){
			String actualFirstName = listOfAlices.get(0).getFirstName();
			String expectedFirstName = "Alice";
			assertEquals(expectedFirstName, actualFirstName);
		} else {
			assertTrue("Alice was not found", false);
		}
	}
	@Test
	public void foundTwoSmiths() {
		String smith = "Smith";

		when(customerRepository.findByLastName(smith)).thenReturn(getCustSmiths());
	
		List<Customer> listOfSmiths = customerRepository.findByLastName(smith);
		Integer actualNumberofSmiths = listOfSmiths.size();
		
		if (actualNumberofSmiths != null){
			int twoExpected = 2;
			assertTrue(twoExpected == actualNumberofSmiths);
		} else {
			assertTrue("No Smiths found", false);
		}
	}	
}

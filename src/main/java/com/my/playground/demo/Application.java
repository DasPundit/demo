package com.my.playground.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		repository.deleteAll();
		System.out.println("---- _   _    _____   _____    _   _  ----");
		System.out.println("----| | | |  |  _  | |_   _|  | | / | ----");
		System.out.println("----| \\_/ |  | |_| |  _| |_   |  /| | ---");
		System.out.println("----|_| |_|  |_| |_| |_____|  |_| |_| ----");

		// save a couple of customers
//		repository.save(new Customer("Alice", "Smith","1234 Streed Dr"));
//		repository.save(new Customer("Bob", "Smith", "4321 Drive St."));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));
		
		System.out.println();

		System.out.println("Customers found with findByLastName('Smithy'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smithy")) {
			System.out.println(customer);
		}
		System.out.println();

	}

}
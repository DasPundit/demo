package com.my.playground.demo;

import org.springframework.data.annotation.Id;

public class Customer {

	@Id
	private String databaseKey;

	private String firstName;
	private String lastName;
	private String customerAddress;
	
	public Customer() {}
	
    public Customer(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerAddress = address;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Customer[databaseKey=%s, firstName=%s, lastName=%s, address=%s]",
                getDatabaseKey(), getFirstName(), getLastName(), getCustomerAddress());
    }
    
    /*
     *  Getters & Setters
     */
	public String getDatabaseKey() {
		return databaseKey;
	}

	public void setDatabaseKey(String databaseKey) {
		this.databaseKey = databaseKey;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
}

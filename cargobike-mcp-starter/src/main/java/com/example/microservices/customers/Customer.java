
package com.example.microservices.customers;

public class Customer {
    public String customerId;

    public String fullName;

    public String email;

    public Address hasAddress;

    public Customer() {
    }

    public Customer(String customerId, String fullName, String email, Address hasAddress) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.hasAddress = hasAddress;
    }
}


package com.example.microservices.orders;

public class Customer {
    public String customerId;

    public String email;

    public Customer() {
    }

    public Customer(String customerId, String email) {
        this.customerId = customerId;
        this.email = email;
    }
}

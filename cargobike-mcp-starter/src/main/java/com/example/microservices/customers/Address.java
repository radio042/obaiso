
package com.example.microservices.customers;

public class Address {
    public String street;

    public String city;

    public String postalCode;

    public String countryCode;

    public Address() {
    }

    public Address(String street, String city, String postalCode, String countryCode) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
    }
}

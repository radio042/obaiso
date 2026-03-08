
package com.example.microservices.shipping;

public class Address {
    public String postalCode;

    public String countryCode;

    public Address() {
    }

    public Address(String postalCode, String countryCode) {
        this.postalCode = postalCode;
        this.countryCode = countryCode;
    }
}

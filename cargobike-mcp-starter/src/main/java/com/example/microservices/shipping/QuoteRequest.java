
package com.example.microservices.shipping;

public class QuoteRequest {
    public String postalCode;

    public String countryCode;

    public Float weightKg;

    public QuoteRequest() {
    }

    public QuoteRequest(String postalCode, String countryCode, Float weightKg) {
        this.postalCode = postalCode;
        this.countryCode = countryCode;
        this.weightKg = weightKg;
    }
}

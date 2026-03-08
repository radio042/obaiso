
package com.example.microservices.orders;

public class Price {
    public Float amount;

    public String currency;

    public Price() {
    }

    public Price(Float amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
}


package com.example.microservices.shipping;

public class ShipmentQuote {
    public Address shipsTo;

    public Float totalWeightKg;

    public Price hasTotalPrice;

    public Integer estimatedDays;

    public ShipmentQuote() {
    }

    public ShipmentQuote(Address shipsTo, Float totalWeightKg, Price hasTotalPrice, Integer estimatedDays) {
        this.shipsTo = shipsTo;
        this.totalWeightKg = totalWeightKg;
        this.hasTotalPrice = hasTotalPrice;
        this.estimatedDays = estimatedDays;
    }
}

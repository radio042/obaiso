
package com.example.microservices.orders;

import java.util.List;

public class Order {
    public String orderId;

    public String hasStatus;

    public Customer orderedBy;

    public List<OrderItem> hasItem;

    public Price hasTotalPrice;

    public Order() {
    }

    public Order(String orderId, String hasStatus, Customer orderedBy, List<OrderItem> hasItem,
        Price hasTotalPrice) {
        this.orderId = orderId;
        this.hasStatus = hasStatus;
        this.orderedBy = orderedBy;
        this.hasItem = hasItem;
        this.hasTotalPrice = hasTotalPrice;
    }
}

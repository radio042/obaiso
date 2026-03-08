
package com.example.microservices.orders;

public class OrderItem {
    public String sku;

    public Integer quantity;

    public Price unitPrice;

    public OrderItem() {
    }

    public OrderItem(String sku, Integer quantity, Price unitPrice) {
        this.sku = sku;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}

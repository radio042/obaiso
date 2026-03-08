
package com.example.microservices.orders;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/api/orders")
public class OrdersResource {

    @GET
    @Path("/{orderId}")
    @Produces("application/ld+json")
    public Order getOrder(@PathParam("orderId") String orderId) {
        // Static example data, echoing the provided orderId
        Customer customer = new Customer("C-123", "jane.doe@example.com");
        OrderItem item1 = new OrderItem("CB-1000", 1, new Price(2499.0f, "EUR"));
        OrderItem item2 = new OrderItem("ACC-LOCK-01", 2, new Price(39.9f, "EUR"));
        Price total = new Price(2499.0f + 2 * 39.9f, "EUR");

        return new Order(
            orderId,
            "CONFIRMED",
            customer,
            List.of(item1, item2),
            total);
    }
}


package com.example.microservices.customers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/api/customers")
public class CustomersResource {

    @GET
    @Path("/{customerId}")
    @Produces("application/ld+json")
    public Customer getCustomer(@PathParam("customerId") String customerId) {
        // Static example data, echoing the provided path parameter
        Address addr = new Address(
            "Main Street 1",
            "Nürnberg",
            "90402",
            "DE");
        return new Customer(
            customerId,
            "Jane Doe",
            "jane.doe@example.com",
            addr);
    }
}

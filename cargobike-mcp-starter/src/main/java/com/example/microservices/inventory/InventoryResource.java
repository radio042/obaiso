
package com.example.microservices.inventory;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/api/inventory")
public class InventoryResource {

    @GET
    @Path("/{sku}")
    @Produces("application/ld+json")
    public InventoryItem getInventory(@PathParam("sku") String sku) {
        // Static example data, echoing the provided SKU
        return new InventoryItem(
            sku,
            42,
            "NUE-01");
    }
}

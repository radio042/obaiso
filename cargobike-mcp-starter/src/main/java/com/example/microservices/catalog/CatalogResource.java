
package com.example.microservices.catalog;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/api/catalog/cargo-bikes")
public class CatalogResource {

    @GET
    @Produces("application/ld+json")
    public CargoBikeListResponse list() {
        CargoBike cargo = new CargoBike(
            "CB-1000",
            "Bullitt Classic",
            28.5f,
            100.0f,
            2);

        EbikeCargoBike ebike = new EbikeCargoBike(
            "EB-2000",
            "Urban Arrow Performance",
            33.0f,
            125.0f,
            2,
            500);

        return new CargoBikeListResponse(List.of(cargo, ebike));
    }
}


package com.example.microservices.shipping;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/api/shipping/quote")
public class ShippingResource {

    @POST
    @Consumes("application/json")
    @Produces("application/ld+json")
    public ShipmentQuote getShippingQuote(QuoteRequest req) {
        // Minimal static pricing rule: base 5.0 + 1.0 per kg, default currency EUR
        float weight = req != null && req.weightKg != null ? req.weightKg : 0f;
        float amount = 5.0f + weight * 1.0f;
        int days = (weight <= 10.0f) ? 2 : 4; // simplistic ETA rule

        Address dest = new Address(req != null ? req.postalCode : null, req != null ? req.countryCode : null);
        Price price = new Price(amount, "EUR");
        return new ShipmentQuote(dest, weight, price, days);
    }
}

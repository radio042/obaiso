
        package com.example.mcp;

        import io.quarkiverse.mcp.server.Tool;
        import io.quarkiverse.mcp.server.ToolArg;
        import io.quarkiverse.mcp.server.ToolResponse;
        import io.quarkiverse.mcp.server.TextContent;

        import jakarta.inject.Singleton;

        @Singleton
        public class ShippingToolsPlain {

            @Tool(description = "Get shipping quote (plain JSON, no ontology)")
            public ToolResponse getShippingQuote(
                    @ToolArg(description = "Destination postal code", required = true) String postalCode,
                    @ToolArg(description = "Two-letter ISO country code (optional)") String countryCode,
                    @ToolArg(description = "Total weight in kilograms", required = true) double weightKg
            ) {
                double amount = 5.0 + weightKg * 1.0; // base 5 + 1 per kg
                int days = (weightKg <= 10.0) ? 2 : 4;

                String json = String.format(
                        "{
  "shipsTo": { "postalCode": "%s", "countryCode": %s },
  "totalWeightKg": %.2f,
  "hasTotalPrice": { "amount": %.2f, "currency": "EUR" },
  "estimatedDays": %d
}",
                        postalCode,
                        countryCode == null ? "null" : (""" + countryCode + """),
                        weightKg,
                        amount,
                        days
                );

                return ToolResponse.success(new TextContent(json));
            }
        }

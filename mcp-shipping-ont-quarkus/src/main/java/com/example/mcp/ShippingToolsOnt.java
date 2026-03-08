
        package com.example.mcp;

        import io.quarkiverse.mcp.server.Tool;
        import io.quarkiverse.mcp.server.ToolArg;
        import io.quarkiverse.mcp.server.ToolResponse;
        import io.quarkiverse.mcp.server.TextContent;

        import jakarta.inject.Singleton;

        @Singleton
        public class ShippingToolsOnt {
            private static final String CB = "https://example.com/ont/cargobike#";

            @Tool(description = "Get shipping quote (ontology-aware, JSON-LD)")
            public ToolResponse cb_getShippingQuote(
                    @ToolArg(description = "cb:postalCode OR alias 'zip' OR 'plz'") String postalCode,
                    @ToolArg(description = "alias of cb:postalCode") String zip,
                    @ToolArg(description = "alias of cb:postalCode (de-DE)") String plz,
                    @ToolArg(description = "cb:countryCode (ISO-3166 alpha-2)") String countryCode,
                    @ToolArg(description = "cb:hasWeightKg OR alias 'kg'") Double weightKg,
                    @ToolArg(description = "alias of cb:hasWeightKg") Double kg
            ) {
                String normalizedPostal = firstNonNull(postalCode, zip, plz);
                double w = firstNonNull(weightKg, kg, 0.0);
                double amount = 5.0 + w * 1.0;
                int days = (w <= 10.0) ? 2 : 4;

                if (normalizedPostal == null) {
                    return ToolResponse.error("One of postalCode|zip|plz is required");
                }
                if (w == 0.0 && weightKg == null && kg == null) {
                    return ToolResponse.error("One of weightKg|kg is required");
                }

                // JSON-LD with @context and @type
                String jsonld = String.format(
                        "{
  "@context": [ "%s" ],
  "@type": "ShipmentQuote",
  "shipsTo": { "@type": "Address", "postalCode": "%s", "countryCode": %s },
  "totalWeightKg": %.2f,
  "hasTotalPrice": { "@type": "Price", "amount": %.2f, "currency": "EUR" },
  "estimatedDays": %d
}",
                        CB,
                        normalizedPostal,
                        countryCode == null ? "null" : (""" + countryCode + """),
                        w,
                        amount,
                        days
                );

                return ToolResponse.success(new TextContent(jsonld));
            }

            private static String firstNonNull(String a, String b, String c) {
                if (a != null && !a.isEmpty()) return a;
                if (b != null && !b.isEmpty()) return b;
                if (c != null && !c.isEmpty()) return c;
                return null;
            }
            private static double firstNonNull(Double a, Double b, double defaultVal) {
                if (a != null) return a;
                if (b != null) return b;
                return defaultVal;
            }
        }

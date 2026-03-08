
package com.example.microservices.catalog.mcp;

import java.util.List;
import java.util.Map;

public class SampleData {
    public static Map<String, Object> plainList() {
        return Map.of(
            "items", List.of(
                Map.of(
                    "sku", "CB-001",
                    "modelName", "Bakfiets Classic",
                    "weightKg", 32.5,
                    "maxPayloadKg", 100.0,
                    "wheelCount", 2),
                Map.of(
                    "sku", "CB-002",
                    "modelName", "Urban Arrow Family",
                    "weightKg", 43.2,
                    "maxPayloadKg", 125.0,
                    "wheelCount", 2,
                    "batteryCapacityWh", 500)));
    }

    public static Map<String, Object> semanticList() {
        return Map.of(
            "@context", Map.of("cb", "https://example.com/ont/cargobike#"),
            "items", List.of(
                Map.of(
                    "@type", List.of("cb:CargoBike"),
                    "sku", "CB-001",
                    "modelName", "Bakfiets Classic",
                    "weightKg", 32.5,
                    "maxPayloadKg", 100.0,
                    "wheelCount", 2),
                Map.of(
                    "@type", List.of("cb:CargoBike", "cb:EbikeCargoBike"),
                    "sku", "CB-002",
                    "modelName", "Urban Arrow Family",
                    "weightKg", 43.2,
                    "maxPayloadKg", 125.0,
                    "wheelCount", 2,
                    "batteryCapacityWh", 500)));
    }
}

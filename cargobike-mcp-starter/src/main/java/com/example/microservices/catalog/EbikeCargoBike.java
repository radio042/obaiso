
package com.example.microservices.catalog;

public class EbikeCargoBike extends CargoBike {
    public Integer batteryCapacityWh;

    public EbikeCargoBike() {
    }

    public EbikeCargoBike(String sku,
        String modelName,
        Float weightKg,
        Float maxPayloadKg,
        Integer wheelCount,
        Integer batteryCapacityWh) {
        super(sku, modelName, weightKg, maxPayloadKg, wheelCount);
        this.batteryCapacityWh = batteryCapacityWh;
    }
}

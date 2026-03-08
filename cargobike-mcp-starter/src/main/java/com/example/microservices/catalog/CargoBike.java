
package com.example.microservices.catalog;

public class CargoBike {
    public String sku;

    public String modelName;

    public Float weightKg;

    public Float maxPayloadKg;

    public Integer wheelCount;

    public CargoBike() {
    }

    public CargoBike(String sku, String modelName, Float weightKg, Float maxPayloadKg, Integer wheelCount) {
        this.sku = sku;
        this.modelName = modelName;
        this.weightKg = weightKg;
        this.maxPayloadKg = maxPayloadKg;
        this.wheelCount = wheelCount;
    }
}

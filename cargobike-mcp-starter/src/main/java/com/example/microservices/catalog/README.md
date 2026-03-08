
# Catalog Service (Quarkus, static data)

Minimal runnable Quarkus app implementing the given OpenAPI path with static response data and `application/ld+json`.

## Requirements
- Java 17+
- Maven 3.8+

## Run (dev mode)
```bash
mvn quarkus:dev
```

## Call the API
```bash
curl -H "Accept: application/ld+json" http://localhost:8080/api/catalog/cargo-bikes
```

Expected response:
```json
{
  "items": [
    {
      "sku": "CB-1000",
      "modelName": "Bullitt Classic",
      "weightKg": 28.5,
      "maxPayloadKg": 100.0,
      "wheelCount": 2
    },
    {
      "sku": "EB-2000",
      "modelName": "Urban Arrow Performance",
      "weightKg": 33.0,
      "maxPayloadKg": 125.0,
      "wheelCount": 2,
      "batteryCapacityWh": 500
    }
  ]
}
```

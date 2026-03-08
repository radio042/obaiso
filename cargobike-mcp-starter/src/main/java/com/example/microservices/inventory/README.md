
# Inventory Service (Quarkus, static data)

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
curl -H "Accept: application/ld+json" http://localhost:8080/api/inventory/CB-1000
```

Expected response:
```json
{
  "sku": "CB-1000",
  "hasQuantity": 42,
  "warehouseCode": "NUE-01"
}
```

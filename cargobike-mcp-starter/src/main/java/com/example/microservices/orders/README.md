
# Orders Service (Quarkus, static data)

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
curl -H "Accept: application/ld+json" http://localhost:8080/api/orders/ORD-1001
```

Expected response (example):
```json
{
  "orderId": "ORD-1001",
  "hasStatus": "CONFIRMED",
  "orderedBy": {
    "customerId": "C-123",
    "email": "jane.doe@example.com"
  },
  "hasItem": [
    {
      "sku": "CB-1000",
      "quantity": 1,
      "unitPrice": { "amount": 2499.0, "currency": "EUR" }
    },
    {
      "sku": "ACC-LOCK-01",
      "quantity": 2,
      "unitPrice": { "amount": 39.9, "currency": "EUR" }
    }
  ],
  "hasTotalPrice": { "amount": 2578.8, "currency": "EUR" }
}
```

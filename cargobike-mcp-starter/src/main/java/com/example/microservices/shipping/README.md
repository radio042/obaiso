
# Shipping Service (Quarkus, static data)

Minimal runnable Quarkus app implementing the given OpenAPI path with static response data.

## Requirements
- Java 17+
- Maven 3.8+

## Run (dev mode)
```bash
mvn quarkus:dev
```

## Call the API
```bash
curl -H "Content-Type: application/json" -H "Accept: application/ld+json"   -d '{"postalCode":"90402","countryCode":"DE","weightKg":12.5}'   http://localhost:8080/api/shipping/quote
```

Expected response (example):
```json
{
  "shipsTo": { "postalCode": "90402", "countryCode": "DE" },
  "totalWeightKg": 12.5,
  "hasTotalPrice": { "amount": 17.5, "currency": "EUR" },
  "estimatedDays": 2
}
```

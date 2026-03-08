
# Customers Service (Quarkus, static data)

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
curl -H "Accept: application/ld+json" http://localhost:8080/api/customers/12345
```

Expected response:
```json
{
  "customerId": "12345",
  "fullName": "Jane Doe",
  "email": "jane.doe@example.com",
  "hasAddress": {
    "street": "Main Street 1",
    "city": "Nürnberg",
    "postalCode": "90402",
    "countryCode": "DE"
  }
}
```

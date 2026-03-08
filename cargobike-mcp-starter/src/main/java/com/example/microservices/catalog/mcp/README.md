# Quarkus MCP Catalog (Java 25)

A **very simple** Quarkus project (Java 25) that:

- Serves a **Catalog** REST endpoint at `GET /api/catalog/cargo-bikes` returning `application/ld+json`.
- Exposes **two MCP-style JSON-RPC servers** over HTTP:
  - `POST /mcp/plain` – plain JSON output (no ontology).
  - `POST /mcp/semantic` – JSON-LD output with ontology context `https://example.com/ont/cargobike#`.
- Publishes a static **OpenAPI 3.1** document at `/openapi` (disabled scanning; file at
  `src/main/resources/openapi.yaml`).

> Note: The MCP servers here use **JSON-RPC 2.0 over HTTP POST** for simplicity. They demonstrate the shape of requests
> and responses and how one server can reference an ontology. Adjust transport (e.g., stdio or WebSocket) if your MCP
> client expects a different channel.

## Prerequisites

- **JDK 25**
- **Maven 3.9+**

## Run

```bash
mvn quarkus:dev
```

The app starts on <http://localhost:8080>.

- REST endpoint (sample data as JSON-LD):
  ```bash
  curl -s http://localhost:8080/api/catalog/cargo-bikes | jq
  ```
- OpenAPI (static):
  ```bash
  curl -s http://localhost:8080/openapi
  ```

## MCP server usage (JSON-RPC over HTTP)

### 1) List tools

- **Plain** server:
  ```bash
  curl -s -X POST http://localhost:8080/mcp/plain     -H 'Content-Type: application/json'     -d '{"jsonrpc":"2.0","id":"1","method":"tools/list"}' | jq
  ```
- **Semantic** server:
  ```bash
  curl -s -X POST http://localhost:8080/mcp/semantic     -H 'Content-Type: application/json'     -d '{"jsonrpc":"2.0","id":"1","method":"tools/list"}' | jq
  ```

### 2) Call the tool `listCargoBikes`

- **Plain** server (returns plain JSON):
  ```bash
  curl -s -X POST http://localhost:8080/mcp/plain     -H 'Content-Type: application/json'     -d '{"jsonrpc":"2.0","id":"2","method":"tools/call","params":{"name":"listCargoBikes","arguments":{}}}' | jq
  ```

- **Semantic** server (returns JSON-LD with `@context` and `@type`):
  ```bash
  curl -s -X POST http://localhost:8080/mcp/semantic     -H 'Content-Type: application/json'     -d '{"jsonrpc":"2.0","id":"2","method":"tools/call","params":{"name":"listCargoBikes","arguments":{}}}' | jq
  ```

### 3) Semantic server capabilities

```bash
curl -s -X POST http://localhost:8080/mcp/semantic   -H 'Content-Type: application/json'   -d '{"jsonrpc":"2.0","id":"3","method":"capabilities/get"}' | jq
```

## Notes

- This sample aims to be **minimal**. For production-grade MCP transports and schemas, wire up the transport your MCP
  client expects (stdio/WebSocket) and extend the schema definitions accordingly.
- The REST endpoint and the MCP semantic server both **reuse** the same ontology base IRI:
  `https://example.com/ont/cargobike#`.

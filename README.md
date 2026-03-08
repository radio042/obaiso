
# Quarkus MCP Servers (Plain vs Ontology-aware)

Two Quarkus-based MCP servers using the **STDIO** transport:

- `mcp-shipping-plain-quarkus` — plain JSON response.
- `mcp-shipping-ont-quarkus` — JSON-LD with `@context: ["https://example.com/ont/cargobike#"]`, accepts input synonyms (`zip`, `plz`, `kg`).

## Build & Run (example)
```bash
cd mcp-shipping-ont-quarkus
./mvnw -q package
java -jar target/quarkus-app/quarkus-run.jar
```

Then wire it into your MCP-compatible client (e.g., Claude Desktop) as a **stdio** server.

## Notes
- Logging is redirected to **stderr** so MCP messages on **stdout** remain clean.
- Uses Quarkiverse MCP Server extension with `@Tool` and `@ToolArg` annotations.

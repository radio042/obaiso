
# mcp-shipping-ont-quarkus

Minimal Quarkus MCP Server exposing a Shipping Quote tool.

- **STDIO transport** via Quarkiverse MCP extension.
- Plain variant returns JSON; ontology variant returns JSON-LD with `@context`.

## Build & Run
```bash
./mvnw -q package
java -jar target/quarkus-app/quarkus-run.jar
```

The process will wait for an MCP client over **stdio** (no visible output).

## Connect from Claude Desktop (example)
Add to your `claude_desktop_config.json`:
```json
{
  "mcpServers": {
    "mcp-shipping-ont-quarkus": {
      "command": "java",
      "args": ["-jar", "/absolute/path/to/mcp-shipping-ont-quarkus/target/quarkus-app/quarkus-run.jar"]
    }
  }
}
```
```
```

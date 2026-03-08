
package com.example.microservices.catalog.mcp;

import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/mcp/plain")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class McpPlainResource {

    @POST
    public Object handle(JsonRpcRequest req) {
        if (req == null || req.method == null) {
            return JsonRpcResponse.error(req != null ? req.id : null, -32600, "Invalid Request");
        }
        return switch (req.method) {
        case "tools/list" -> JsonRpcResponse.ok(req.id, Map.of(
            "tools", List.of(
                Map.of(
                    "name", "listCargoBikes",
                    "description", "List cargo bikes (plain JSON)",
                    "inputSchema", Map.of("type", "object", "properties", Map.of()),
                    "outputSchema", Map.of("type", "object", "properties", Map.of(
                        "items", Map.of(
                            "type", "array",
                            "items", Map.of("anyOf", List.of(
                                Map.of("type", "object"),
                                Map.of("type", "object"))))))))));
        case "tools/call" -> {
            Map<String, Object> params = req.params == null ? Map.of() : req.params;
            Object toolName = params.get("name");
            if (!"listCargoBikes".equals(toolName)) {
                yield JsonRpcResponse.error(req.id, -32601, "Unknown tool: " + toolName);
            }
            yield JsonRpcResponse.ok(req.id, Map.of(
                "content", List.of(Map.of(
                    "type", "json",
                    "data", SampleData.plainList()))));
        }
        default -> JsonRpcResponse.error(req.id, -32601, "Method not found: " + req.method);
        };
    }
}

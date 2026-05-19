
package com.example.mcp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.InputStream;
import java.util.*;

class OpenApiToolLoader {

    private static final String OPENAPI_DIR = "assets/openapi/";
    private static final String[] SPEC_FILES = {
        "catalog.yaml", "customers.yaml", "inventory.yaml", "orders.yaml", "shipping.yaml"
    };
    private static final ObjectMapper YAML = new ObjectMapper(new YAMLFactory());

    static List<Map<String, Object>> loadAll() {
        List<Map<String, Object>> tools = new ArrayList<>();
        for (String file : SPEC_FILES) {
            String resourceUri = "classpath:" + OPENAPI_DIR + file;
            try (InputStream is = OpenApiToolLoader.class.getClassLoader()
                    .getResourceAsStream(OPENAPI_DIR + file)) {
                if (is == null) continue;
                JsonNode spec = YAML.readTree(is);
                extractTools(spec, resourceUri, tools);
            } catch (Exception ignored) {}
        }
        return tools;
    }

    private static void extractTools(JsonNode spec, String resourceUri, List<Map<String, Object>> out) {
        spec.path("paths").fields().forEachRemaining(pathEntry ->
            pathEntry.getValue().fields().forEachRemaining(methodEntry -> {
                JsonNode op = methodEntry.getValue();
                if (!op.isObject()) return;
                String operationId = op.path("operationId").asText(null);
                if (operationId == null) return;

                Map<String, Object> tool = new LinkedHashMap<>();
                tool.put("name", operationId);
                tool.put("description", "Call operation of API as specified by referenced OpenAPI specification");
                tool.put("x-openapi", Map.of("resourceUri", resourceUri, "operationId", operationId));
                tool.put("inputSchema", buildInputSchema(op, spec));
                out.add(tool);
            })
        );
    }

    private static Map<String, Object> buildInputSchema(JsonNode op, JsonNode spec) {
        // Parameters (GET with path / query params)
        JsonNode params = op.path("parameters");
        if (params.isArray() && !params.isEmpty()) {
            Map<String, Object> props = new LinkedHashMap<>();
            List<String> required = new ArrayList<>();
            params.forEach(p -> {
                String name = p.path("name").asText();
                props.put(name, propertySchema(p.path("schema")));
                if (p.path("required").asBoolean(false)) required.add(name);
            });
            Map<String, Object> schema = new LinkedHashMap<>();
            schema.put("type", "object");
            schema.put("properties", props);
            if (!required.isEmpty()) schema.put("required", required);
            return schema;
        }

        // RequestBody (POST / PUT)
        JsonNode content = op.path("requestBody").path("content");
        if (!content.isMissingNode()) {
            Iterator<JsonNode> mediaTypes = content.elements();
            if (mediaTypes.hasNext()) {
                JsonNode schemaNode = resolveRef(mediaTypes.next().path("schema"), spec);
                return flattenToInputSchema(schemaNode);
            }
        }

        return Map.of("type", "object", "properties", Map.of());
    }

    private static Map<String, Object> flattenToInputSchema(JsonNode schema) {
        Map<String, Object> props = new LinkedHashMap<>();
        schema.path("properties").fields().forEachRemaining(e ->
            props.put(e.getKey(), propertySchema(e.getValue()))
        );
        List<String> required = new ArrayList<>();
        schema.path("required").forEach(r -> required.add(r.asText()));
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("type", "object");
        result.put("properties", props);
        if (!required.isEmpty()) result.put("required", required);
        return result;
    }

    private static Map<String, Object> propertySchema(JsonNode schema) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("type", schema.path("type").asText("string"));
        String format = schema.path("format").asText(null);
        if (format != null) m.put("format", format);
        return m;
    }

    private static JsonNode resolveRef(JsonNode node, JsonNode spec) {
        String ref = node.path("$ref").asText(null);
        if (ref == null || !ref.startsWith("#/")) return node;
        JsonNode resolved = spec;
        for (String part : ref.substring(2).split("/")) {
            resolved = resolved.path(part);
        }
        return resolved;
    }
}
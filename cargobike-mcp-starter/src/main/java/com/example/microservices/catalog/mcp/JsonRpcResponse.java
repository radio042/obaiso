
package com.example.microservices.catalog.mcp;

import java.util.Map;

public class JsonRpcResponse {
    public String jsonrpc = "2.0";

    public String id;

    public Object result;

    public Map<String, Object> error;

    public static JsonRpcResponse ok(String id, Object result) {
        JsonRpcResponse r = new JsonRpcResponse();
        r.id = id;
        r.result = result;
        return r;
    }

    public static JsonRpcResponse error(String id, int code, String message) {
        JsonRpcResponse r = new JsonRpcResponse();
        r.id = id;
        r.error = Map.of("code", code, "message", message);
        return r;
    }
}

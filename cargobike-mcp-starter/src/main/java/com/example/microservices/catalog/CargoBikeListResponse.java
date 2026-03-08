
package com.example.microservices.catalog;

import java.util.List;

public class CargoBikeListResponse {
    public List<Object> items;

    public CargoBikeListResponse() {
    }

    public CargoBikeListResponse(List<Object> items) {
        this.items = items;
    }
}

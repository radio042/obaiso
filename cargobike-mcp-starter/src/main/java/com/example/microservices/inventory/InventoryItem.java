
package com.example.microservices.inventory;

public class InventoryItem {
    public String sku;

    public Integer hasQuantity;

    public String warehouseCode;

    public InventoryItem() {
    }

    public InventoryItem(String sku, Integer hasQuantity, String warehouseCode) {
        this.sku = sku;
        this.hasQuantity = hasQuantity;
        this.warehouseCode = warehouseCode;
    }
}

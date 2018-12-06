package com.example.android.inventory.data;

public class Inventory {

    private int mInventoryId;
    private int mInventoryQuantity;

    public Inventory(int mInventoryId, int mInventoryQuantity) {
        this.mInventoryId = mInventoryId;
        this.mInventoryQuantity = mInventoryQuantity;
    }

    public int getmInventoryId() {
        return mInventoryId;
    }

    public int getmInventoryQuantity() {
        return mInventoryQuantity;
    }
}

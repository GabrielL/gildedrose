package com.gildedrose.items;

public class VintageItem extends InventoryItem {
    public VintageItem(final String name, final int sellIn, final int quality) {
        super(name, sellIn, quality);
    }

    @Override
    int agingFactor() {
        return 1;
    }

    @Override
    int agingFactorAfterExpiration() {
        return 2;
    }
}

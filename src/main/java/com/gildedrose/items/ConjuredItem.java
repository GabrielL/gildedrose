package com.gildedrose.items;

public class ConjuredItem extends InventoryItem {
    public ConjuredItem(final String name, final int sellIn, final int quality) {
        super(name, sellIn, quality);
    }

    @Override
    int agingFactor() {
        return super.agingFactor() * 2;
    }

    @Override
    int agingFactorAfterExpiration() {
        return super.agingFactorAfterExpiration() * 2;
    }
}

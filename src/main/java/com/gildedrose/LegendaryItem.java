package com.gildedrose;

/**
 * Legendary items don't expire and don't lose quality
 */
public class LegendaryItem extends InventoryItem {
    public static final int LEGENDARY_QUALITY = 80;

    public LegendaryItem(final String name, final int sellIn) {
        super(name, sellIn, LEGENDARY_QUALITY);
    }

    @Override
    InventoryItem update() {
        return this;
    }
}

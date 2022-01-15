package com.gildedrose.items;

public class VintageItem extends InventoryItem {
    public VintageItem(final String name, final int sellIn, final int quality) {
        super(name, sellIn, quality);
    }

    @Override
    int updateQuality(final int sellIn) {
        if (isExpired(sellIn)) {
            return changeQuality(quality + 2);
        } else {
            return changeQuality(quality + 1);
        }
    }
}

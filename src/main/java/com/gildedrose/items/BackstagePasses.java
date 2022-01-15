package com.gildedrose.items;

public class BackstagePasses extends InventoryItem {
    public BackstagePasses(final String name, final int sellIn, final int quality) {
        super(name, sellIn, quality);
    }

    @Override
    int updateQuality(final int sellIn) {
        if (isExpired(sellIn)) {
            return LOWEST_QUALITY;
        } else if (sellIn < 5) {
            return changeQuality(quality + 3);
        } else if (sellIn < 10) {
            return changeQuality(quality + 2);
        } else {
            return changeQuality(quality + 1);
        }
    }
}

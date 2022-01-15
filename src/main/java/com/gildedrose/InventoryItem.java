package com.gildedrose;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class InventoryItem {
    static final int LOWEST_QUALITY = 0;
    static final int HIGHEST_QUALITY = 50;

    @Getter String name;
    @Getter int sellIn;
    @Getter int quality;

    InventoryItem update() {
        return new InventoryItem(name, sellIn - 1, updateQuality(sellIn));
    }

    boolean isExpired(int sellIn) {
        return sellIn < 0;
    }

    static int changeQuality(int newQuality) {
        return Math.min(HIGHEST_QUALITY, Math.max(LOWEST_QUALITY, newQuality));
    }

    int updateQuality(int sellIn) {
        if (isExpired(sellIn)) {
            return changeQuality(quality - 2);
        } else {
            return changeQuality(quality - 1);
        }
    }
}

package com.gildedrose.items;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class InventoryItem {
    static final int LOWEST_QUALITY = 0;
    static final int HIGHEST_QUALITY = 50;

    @Getter String name;
    @Getter int sellIn;
    @Getter int quality;

    public InventoryItem update() {
        return new InventoryItem(name, sellIn - 1, updateQuality(sellIn - 1));
    }

    boolean isExpired(int sellIn) {
        return sellIn < 0;
    }

    static int changeQuality(int newQuality) {
        return Math.min(HIGHEST_QUALITY, Math.max(LOWEST_QUALITY, newQuality));
    }

    int updateQuality(int sellIn) {
        if (isExpired(sellIn)) {
            return changeQuality(quality + agingFactorAfterExpiration());
        } else {
            return changeQuality(quality + agingFactor());
        }
    }

    int agingFactor() {
        return -1;
    }

    int agingFactorAfterExpiration() {
        return -2;
    }
}

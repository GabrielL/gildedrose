package com.gildedrose;

public class LegacyInventoryItem extends InventoryItem {
    public LegacyInventoryItem(final String name, final int sellIn, final int quality) {
        super(name, sellIn, quality);
    }

    @Override
    InventoryItem update() {
        updateItemQuality(this);
        return this;
    }

    static boolean isExpired(LegacyInventoryItem item) {
        return item.sellIn < 0;
    }

    static void age(LegacyInventoryItem item) {
        item.sellIn -= 1;
    }

    static int changeQuality(int newQuality) {
        return Math.min(HIGHEST_QUALITY, Math.max(LOWEST_QUALITY, newQuality));
    }

    private static void updateItemQuality(final LegacyInventoryItem item) {
        switch (item.name) {
            case Catalogue.SULFURAS_HAND_OF_RAGNAROS:
                return;
            case Catalogue.AGED_BRIE:
                age(item);

                if (isExpired(item)) {
                    item.quality = changeQuality(item.quality + 2);
                } else {
                    item.quality = changeQuality(item.quality + 1);
                }
                break;
            case Catalogue.BACKSTAGE_PASSES:
                age(item);

                if (isExpired(item)) {
                    item.quality = LOWEST_QUALITY;
                } else if (item.sellIn < 5) {
                    item.quality = changeQuality(item.quality + 3);
                } else if (item.sellIn < 10) {
                    item.quality = changeQuality(item.quality + 2);
                } else {
                    item.quality = changeQuality(item.quality + 1);
                }
                break;
            default:
                age(item);

                if (isExpired(item)) {
                    item.quality = changeQuality(item.quality - 2);
                } else {
                    item.quality = changeQuality(item.quality - 1);
                }
                break;
        }
    }
}

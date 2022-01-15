package com.gildedrose;

import java.util.Arrays;
import java.util.stream.Collectors;

class GildedRose {
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    static final int LOWEST_QUALITY = 0;
    static final int HIGHEST_QUALITY = 50;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
                .map(it -> {
                    updateItemQuality(it);
                    return it;
                }).collect(Collectors.toList()).toArray(items);
    }

    static boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    static void age(Item item) {
        item.sellIn -= 1;
    }

    static int changeQuality(int newQuality) {
        return Math.min(HIGHEST_QUALITY, Math.max(LOWEST_QUALITY, newQuality));
    }

    private static void updateItemQuality(final Item item) {
        switch (item.name) {
            case SULFURAS_HAND_OF_RAGNAROS:
                return;
            case AGED_BRIE:
                age(item);

                if (isExpired(item)) {
                    item.quality = changeQuality(item.quality + 2);
                } else {
                    item.quality = changeQuality(item.quality + 1);
                }
                break;
            case BACKSTAGE_PASSES:
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

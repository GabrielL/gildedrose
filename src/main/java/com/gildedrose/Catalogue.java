package com.gildedrose;

public class Catalogue {
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    public static InventoryItem fromItem(Item item) {
        if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
            return new LegendaryItem(SULFURAS_HAND_OF_RAGNAROS, item.sellIn);
        } else {
            return new LegacyInventoryItem(item.name, item.sellIn, item.quality);
        }
    }
}

package com.gildedrose;

import com.gildedrose.items.BackstagePasses;
import com.gildedrose.items.InventoryItem;
import com.gildedrose.items.LegendaryItem;
import com.gildedrose.items.VintageItem;

public class Catalogue {
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    public static InventoryItem fromItem(Item item) {
        switch (item.name) {
            case SULFURAS_HAND_OF_RAGNAROS:
                return new LegendaryItem(SULFURAS_HAND_OF_RAGNAROS, item.sellIn);
            case AGED_BRIE:
                return new VintageItem(AGED_BRIE, item.sellIn, item.quality);
            case BACKSTAGE_PASSES:
                return new BackstagePasses(BACKSTAGE_PASSES, item.sellIn, item.quality);
            default:
                return new InventoryItem(item.name, item.sellIn, item.quality);
        }
    }
}

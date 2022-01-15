package com.gildedrose;

import java.util.Arrays;
import java.util.stream.Collectors;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
                .map(Catalogue::fromItem)
                .map(InventoryItem::update)
                .map(it -> new Item(it.getName(), it.getSellIn(), it.getQuality()))
                .collect(Collectors.toList()).toArray(items);
    }
}

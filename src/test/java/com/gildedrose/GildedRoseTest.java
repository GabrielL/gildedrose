package com.gildedrose;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static com.gildedrose.GildedRose.AGED_BRIE;
import static com.gildedrose.GildedRose.BACKSTAGE_PASSES;
import static com.gildedrose.GildedRose.SULFURAS_HAND_OF_RAGNAROS;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Approvals.verifyAll("items", app.items);
    }

    @Test
    void approvalOriginalMain() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream, true, StandardCharsets.UTF_8);
        GildedRoseMain.gildedRoseMain(out, 2);
        Approvals.verify(outStream);
    }

    @Test
    void approvalOriginalMainLongTest() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream, true, StandardCharsets.UTF_8);
        GildedRoseMain.gildedRoseMain(out, 20);
        Approvals.verify(outStream);
    }


    @Test
    void matchLine26() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 0, 0)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Approvals.verifyAll("items", app.items);
    }

    @Test
    void sulfurasNeverChange() {
        var items = new Item[]{
                new Item(SULFURAS_HAND_OF_RAGNAROS, 12, 80)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assertions.assertEquals(80, app.items[0].quality);
        Assertions.assertEquals(SULFURAS_HAND_OF_RAGNAROS, app.items[0].name);
        Assertions.assertEquals(12, app.items[0].sellIn);
    }

    @Test
    void agedBrieIncreaseQuality() {
        Item[] items = {new Item(AGED_BRIE, 2, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assertions.assertEquals(1, app.items[0].sellIn);
        Assertions.assertEquals(1, app.items[0].quality);
    }

    @Test
    void agedBrieIncreaseQualityButNotOver50() {
        Item[] items = {new Item(AGED_BRIE, 2, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assertions.assertEquals(1, app.items[0].sellIn);
        Assertions.assertEquals(50, app.items[0].quality);
    }

    @Test
    void agedBrieSellin() {
        Item[] items = {new Item(AGED_BRIE, -1, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Assertions.assertEquals(2, app.items[0].quality);
        Assertions.assertEquals(-2, app.items[0].sellIn);
    }

}

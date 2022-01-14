package com.gildedrose;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

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
    void matchLine26() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 0)};
        
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Approvals.verifyAll("items", app.items);
    }

}

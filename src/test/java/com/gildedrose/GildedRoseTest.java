package com.gildedrose;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        //assertEquals("foo", app.items[0].name);
        Approvals.verifyAll("items", app.items);
    }

    @Test
    void approvalOriginalMain() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream, true, StandardCharsets.UTF_8);
        GildedRoseMain.gildedRoseMain(out, 2);
        Approvals.verify(outStream);
    }

}

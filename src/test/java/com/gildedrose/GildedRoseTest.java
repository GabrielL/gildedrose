package com.gildedrose;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

class GildedRoseTest {

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

}

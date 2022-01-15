package com.gildedrose;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class GildedRoseMainTest {
    @Test
    void testMain() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream, true, StandardCharsets.UTF_8);
        System.setOut(out);
        GildedRoseMain.main(new String[]{"2"});
        Approvals.verify(outStream);
    }
}

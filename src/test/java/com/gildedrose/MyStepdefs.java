package com.gildedrose;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class MyStepdefs {
    Item item;

    @Given("an {string} with an initial quality of {int} and a sell in date of {int}")
    public void anItemWithAnInitialQualityOfQualityAndASellInDateOfSellIn(String name, int quality, int sellIn) {
        item = new Item(name, sellIn, quality);

    }

    @When("{int} day(s) passes")
    public void aDayPasses(int days) {
        var app = new GildedRose(new Item[]{item});
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
    }

    @Then("quality should be at {int} and sellIn should decreases to {int}")
    public void qualityShouldBeAtNewQualityAndSellInShouldDecreasesToNewSellin(int quality, int sellIn) {
        Assertions.assertEquals(quality, item.quality);
        Assertions.assertEquals(sellIn, item.sellIn);
    }
}

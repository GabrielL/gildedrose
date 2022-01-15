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
        var items = new Item[]{item};
        var app = new GildedRose(items);
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        item = items[0];
    }

    @Then("quality should be at {int} and sellIn should decreases to {int}")
    public void qualityShouldBeAtNewQualityAndSellInShouldDecreasesToNewSellin(int quality, int sellIn) {
        Assertions.assertEquals(quality, item.quality);
        Assertions.assertEquals(sellIn, item.sellIn);
    }

}

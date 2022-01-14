package com.gildedrose;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class MyStepdefs {
    private GildedRose app;

    @Given("an {string} with an initial quality of {int} and a sell in date of {int}")
    public void anItemWithAnInitialQualityOfQualityAndASellInDateOfSellIn(String name, int quality, int sellIn) {
        Item[] items = {
            new Item(name, sellIn, quality)
        };
        app = new GildedRose(items);
    }

    @When("a day passes")
    public void aDayPasses() {
        app.updateQuality();
    }

    @Then("quality should be at {int} and sellIn should decreases to {int}")
    public void qualityShouldBeAtNewQualityAndSellInShouldDecreasesToNewSellin(int quality, int sellIn) {
        Assertions.assertEquals(quality, app.items[0].quality);
        Assertions.assertEquals(sellIn, app.items[0].sellIn);
    }
}

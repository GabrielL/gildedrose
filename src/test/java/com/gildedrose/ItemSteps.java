package com.gildedrose;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ItemSteps {
    InventoryItem item;

    @Given("a Legendary Item with a selling date of {int}")
    public void aLegendaryItemWithASellingDateOf(int sellIn) {
        item = new LegendaryItem("I am Legend", sellIn);
    }

    @Then("quality should be Legendary")
    public void qualityShouldBeLegendary() {
        Assertions.assertEquals(LegendaryItem.LEGENDARY_QUALITY, item.getQuality());
    }

    @When("item ages {int} days")
    public void itemAgesDaysDays(int days) {
        for (int i = 0; i < days; i++) {
            item = item.update();
        }
    }
}

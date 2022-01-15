package com.gildedrose;

import com.gildedrose.items.BackstagePasses;
import com.gildedrose.items.ConjuredItem;
import com.gildedrose.items.InventoryItem;
import com.gildedrose.items.LegendaryItem;
import com.gildedrose.items.VintageItem;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.stream.Collectors;

public class ItemSteps {
    Item rawItem;
    InventoryItem item;
    List<InventoryItem> items;

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

    @And("selling date is still {int}")
    public void sellingDateIsStill(int expectedSellIn) {
        Assertions.assertEquals(expectedSellIn, item.getSellIn());
    }

    @Given("an Inventory")
    public void anInventory(DataTable dataTable) {
        items = buildInventoryFromDataTable(dataTable);
    }

    private List<InventoryItem> buildInventoryFromDataTable(final DataTable dataTable) {
        return dataTable.asMaps().stream()
                .map(it -> new InventoryItem(it.get("name"), Integer.parseInt(it.get("sellIn")), Integer.parseInt(it.get("quality"))))
                .collect(Collectors.toList());
    }

    @When("items ages in the inventory for {int} day")
    public void aDayPasses(int days) {
        for (int i = 0; i < days; i++) {
            items = items.stream().map(InventoryItem::update).collect(Collectors.toList());
        }
    }

    @Then("inventory degrades")
    public void inventoryDegrades(DataTable dataTable) {
        var expected = buildInventoryFromDataTable(dataTable);
        Assertions.assertEquals(expected, items);
    }

    @Then("quality should be at {int}")
    public void qualityIncreaseToAgedQuality(int quality) {
        Assertions.assertEquals(quality, item.getQuality());
    }

    @Given("a Vintage Item of quality {int} and sellIn of {int}")
    public void aVintageItemOfQualityQualityAndSellInOfSellIn(int quality, int sellIn) {
        item = new VintageItem(Catalogue.AGED_BRIE, sellIn, quality);
    }

    @Given("a Backstage Pass of quality {int} and sellIn of {int}")
    public void aBackstagePassOfQualityQualityAndSellInOfSellIn(int quality, int sellIn) {
        item = new BackstagePasses(Catalogue.BACKSTAGE_PASSES, sellIn, quality);
    }

    @Given("a Conjured Item of quality {int} and sellIn of {int}")
    public void aConjuredItemOfQualityQualityAndSellInOfSellIn(int quality, int sellIn) {
        item = new ConjuredItem(Catalogue.CONJURED_MANA_CAKE, sellIn, quality);
    }

    @Given("an Item")
    public void anItem(DataTable dataTable) {
        var map = dataTable.asMaps()
                .stream()
                .map(it -> new Item(it.get("name"), Integer.parseInt(it.get("sellIn")), Integer.parseInt(it.get("quality"))))
                .collect(Collectors.toList());
        Assertions.assertEquals(1, map.size());
        rawItem = map.get(0);
    }

    @When("creating it")
    public void creatingIt() {
        item = Catalogue.fromItem(rawItem);
    }

    @Then("item is Legendary")
    public void itemIsLegendary() {
        Assertions.assertInstanceOf(LegendaryItem.class, item);
    }

    @Then("item is Vintage")
    public void itemIsVintage() {
        Assertions.assertInstanceOf(VintageItem.class, item);
    }

    @Then("item is a Backstage Pass")
    public void itemIsABackstagePass() {
        Assertions.assertInstanceOf(BackstagePasses.class, item);
    }

    @Then("item is an Item")
    public void itemIsAnItem() {
        Assertions.assertInstanceOf(InventoryItem.class, item);
    }

    @Then("item is Conjured")
    public void itemIsConjured() {
        Assertions.assertInstanceOf(ConjuredItem.class, item);
    }
}

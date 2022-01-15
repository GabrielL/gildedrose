package com.gildedrose;

import com.gildedrose.items.BackstagePasses;
import com.gildedrose.items.ConjuredItem;
import com.gildedrose.items.InventoryItem;
import com.gildedrose.items.LegendaryItem;
import com.gildedrose.items.VintageItem;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemSteps {
    Item rawItem;
    InventoryItem item;
    List<InventoryItem> items;

    @DataTableType
    public InventoryItem inventoryItemTransformer(Map<String, String> entry) {
        return new InventoryItem(
                entry.get("name"),
                Integer.parseInt(entry.get("sellIn")),
                Integer.parseInt(entry.get("quality"))
        );
    }

    @DataTableType
    public Item itemTransformer(Map<String, String> entry) {
        return new Item(
                entry.get("name"),
                Integer.parseInt(entry.get("sellIn")),
                Integer.parseInt(entry.get("quality"))
        );
    }

    @ParameterType("Legendary|Vintage|a Backstage Pass|an Item|Conjured")
    public Class<?> itemKind(String kindName) {
        switch (kindName) {
            case "Legendary":
                return LegendaryItem.class;
            case "Vintage":
                return VintageItem.class;
            case "a Backstage Pass":
                return BackstagePasses.class;
            case "an Item":
                return InventoryItem.class;
            case "Conjured":
                return ConjuredItem.class;
            default:
                throw new AssertionError(String.format("itemKind %s does not exists", kindName));
        }
    }

    @Given("a Legendary Item with a selling date of {int}")
    public void aLegendaryItemWithASellingDateOf(int sellIn) {
        item = new LegendaryItem("I am Legend", sellIn);
    }

    @Given("an Inventory")
    public void anInventory(List<InventoryItem> items) {
        this.items = items;
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
    public void anItem(List<Item> items) {
        Assertions.assertEquals(1, items.size());
        rawItem = items.get(0);
    }

    @When("item ages {int} day(s)")
    public void itemAgesDaysDays(int days) {
        for (int i = 0; i < days; i++) {
            item = item.update();
        }
    }

    @When("items ages in the inventory for {int} day")
    public void aDayPasses(int days) {
        for (int i = 0; i < days; i++) {
            items = items.stream().map(InventoryItem::update).collect(Collectors.toList());
        }
    }

    @When("creating it")
    public void creatingIt() {
        item = Catalogue.fromItem(rawItem);
    }


    @Then("quality should be Legendary")
    public void qualityShouldBeLegendary() {
        Assertions.assertEquals(LegendaryItem.LEGENDARY_QUALITY, item.getQuality());
    }

    @And("selling date is still {int}")
    public void sellingDateIsStill(int expectedSellIn) {
        Assertions.assertEquals(expectedSellIn, item.getSellIn());
    }

    @Then("quality should be at {int}")
    public void qualityIncreaseToAgedQuality(int quality) {
        Assertions.assertEquals(quality, item.getQuality());
    }

    @Then("inventory degrades")
    public void inventoryDegrades(List<InventoryItem> expectedItems) {
        Assertions.assertEquals(expectedItems, items);
    }

    @Then("item is {itemKind}")
    public void itemIsLegendary(Class<?> expectedClass) {
        Assertions.assertInstanceOf(expectedClass, item);
    }
}

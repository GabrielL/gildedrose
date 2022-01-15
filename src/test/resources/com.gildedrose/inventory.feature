Feature: building objects with proper behavior

  Scenario: Sulfuras is legendary
    Given an Item
      | name                       | sellIn | quality |
      | Sulfuras, Hand of Ragnaros | 10     | 50      |
    When creating it
    Then item is Legendary
    And quality should be Legendary

  Scenario: Aged brie is Vintage
    Given an Item
      | name      | sellIn | quality |
      | Aged Brie | 10     | 50      |
    When creating it
    Then item is Vintage

  Scenario: Backstage passes are legit
    Given an Item
      | name                                      | sellIn | quality |
      | Backstage passes to a TAFKAL80ETC concert | 10     | 50      |
    When creating it
    Then item is a Backstage Pass

  Scenario: Random item is random
    Given an Item
      | name        | sellIn | quality |
      | Some Object | 10     | 50      |
    When creating it
    Then item is an Item


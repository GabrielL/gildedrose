Feature: Item rules

  Rule: item degrades in time

    Scenario: item degrades in time
      Given an Inventory
        | name                   | sellIn | quality |
        | +5 Dexterity Vest      | 10     | 20      |
        | Elixir of the Mongoose | 5      | 7       |
      When items ages in the inventory for 1 day
      Then inventory degrades
        | name                   | sellIn | quality |
        | +5 Dexterity Vest      | 9      | 19      |
        | Elixir of the Mongoose | 4      | 6       |

    Scenario: expired item degrades twice as fast
      Given an Inventory
        | name                   | sellIn | quality |
        | +5 Dexterity Vest      | 1      | 20      |
        | Elixir of the Mongoose | 0      | 7       |
      When items ages in the inventory for 1 day
      Then inventory degrades
        | name                   | sellIn | quality |
        | +5 Dexterity Vest      | 0      | 19      |
        | Elixir of the Mongoose | -1     | 5       |


  Rule: Legendary items don't age

    Scenario Outline: Legendary items don't expire
      Given a Legendary Item with a selling date of 12
      When item ages <days> days
      Then quality should be Legendary
      And selling date is still 12

      Examples:
        | days |
        | 0    |
        | 1    |
        | 200  |

  Rule: Vintage items are better with Time

    Scenario Outline: Better with time
      Given a Vintage Item of quality <quality> and sellIn of <sellIn>
      When item ages 1 days
      Then quality should be at <agedQuality>
      Examples:
        | quality | sellIn | agedQuality |
        | 10      | 5      | 11          |
        | 10      | 0      | 12          |

  Rule: Backstage passes

  "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
  Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
  Quality drops to 0 after the concert

    Scenario Outline: Backstage passes increase in value
      Given a Backstage Pass of quality <quality> and sellIn of <sellIn>
      When item ages 1 days
      Then quality should be at <agedQuality>
      Examples:
        | quality | sellIn | agedQuality |
        | 10      | 12     | 11          |
        | 10      | 8      | 12          |
        | 10      | 3      | 13          |

    Scenario: Backstage pass are worthless after the concert
      Given a Backstage Pass of quality 50 and sellIn of 0
      When item ages 1 days
      Then quality should be at 0

  Rule: Conjured items degrade twice as fast as normal Items
    Scenario Outline: Conjured items degrade fast
      Given a Conjured Item of quality <quality> and sellIn of <sellIn>
      When item ages 1 days
      Then quality should be at <agedQuality>
      Examples:
        | quality | sellIn | agedQuality |
        | 10      | 5      | 8           |

Feature: Legendary Items
  Legendary Items never has to be sold, nor decreases in Quality

  Rule: items are degrading

    Scenario Outline: items loses their value
      Given an "<item>" with an initial quality of <quality> and a sell in date of <sellIn>
      When a day passes
      Then quality should be at <newQuality> and sellIn should decreases to <newSellin>

      Examples:
        | item                   | sellIn | quality | newSellin | newQuality |
        | +5 Dexterity Vest      | 10     | 20      | 9         | 19         |
        | Elixir of the Mongoose |  5     | 7       | 4         | 6          |


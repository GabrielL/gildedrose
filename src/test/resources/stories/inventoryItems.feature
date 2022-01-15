Feature: Item rules

  Rule: Legendary items don't age

    Scenario Outline: Legendary items don't expire
      Given a Legendary Item with a selling date of 12
      When item ages <days> days
      Then quality should be Legendary

      Examples:
        | days |
        | 0    |
        | 1    |
        | 200  |

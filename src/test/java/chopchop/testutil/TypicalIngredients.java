package chopchop.testutil;

import chopchop.model.ingredient.Ingredient;

public class TypicalIngredients {

    public static final Ingredient APRICOT = new IngredientBuilder().withName("Apricot")
        .withDate("2020-12-20").withQuantity(3)
        .build();
    public static final Ingredient BANANA = new IngredientBuilder().withName("Banana")
        .withDate("2020-12-21").withQuantity(2)
        .build();
}

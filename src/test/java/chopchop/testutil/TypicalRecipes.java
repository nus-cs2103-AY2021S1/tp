package chopchop.testutil;

import chopchop.model.recipe.Recipe;

public class TypicalRecipes {

    public static final Recipe APRICOT = new RecipeBuilder().withName("Apricot")
        .withDate("2020-12-20").withQuantity(3)
        .build();
    public static final Recipe BANANA = new RecipeBuilder().withName("Banana")
        .withDate("2020-12-21").withQuantity(2)
        .build();
}

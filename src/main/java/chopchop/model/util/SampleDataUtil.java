package chopchop.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

import chopchop.model.EntryBook;
import chopchop.model.ReadOnlyEntryBook;
import chopchop.model.attributes.ExpiryDate;
import chopchop.model.attributes.Step;
import chopchop.model.attributes.Tag;
import chopchop.model.attributes.units.Count;
import chopchop.model.attributes.units.Mass;
import chopchop.model.attributes.units.Volume;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.ingredient.IngredientReference;
import chopchop.model.recipe.Recipe;

public class SampleDataUtil {
    public static Ingredient[] getSampleIngredients() {
        return new Ingredient[] {
            new Ingredient("Apple", Count.of(8), new ExpiryDate("2020-12-15"),
                new HashSet<>(Arrays.asList(new Tag("fruit"), new Tag("low calorie")))),
            new Ingredient("Banana", Count.of(5), new ExpiryDate("2020-11-18"),
                new HashSet<>(Arrays.asList(new Tag("fruit"), new Tag("low calorie")))),
            new Ingredient("Egg", Count.of(12), new ExpiryDate("2020-12-01"),
                new HashSet<>(Arrays.asList(
                    new Tag("high protein"), new Tag("breakfast"), new Tag("frequently used")))),
            new Ingredient("French Vinaigrette", Volume.millilitres(300), new ExpiryDate("2021-05-01"),
                new HashSet<>(Arrays.asList(new Tag("high calorie"), new Tag("salad dressing")))),
            new Ingredient("Pineapple Juice", Volume.litres(2), new ExpiryDate("2020-12-31"),
                new HashSet<>(Arrays.asList(new Tag("high sugar level"), new Tag("drink"), new Tag("fruit")))),
            new Ingredient("Granulated Sugar", Mass.grams(200), new ExpiryDate("2021-10-10"),
                new HashSet<>(Arrays.asList(new Tag("sweet"), new Tag("kitchen"), new Tag("baking")))),
            new Ingredient("Honey", Mass.grams(650), new ExpiryDate("2021-07-06"),
                new HashSet<>(Arrays.asList(new Tag("sweet"), new Tag("high sugar level"), new Tag("baking")))),
            new Ingredient("Flour", Mass.kilograms(3.5), null,
                new HashSet<>(Arrays.asList(new Tag("staple food"), new Tag("baking"), new Tag("carbohydrate")))),
            new Ingredient("Olive Oil", Volume.litres(1.5), new ExpiryDate("2022-05-07"),
                new HashSet<>(Arrays.asList(new Tag("kitchen"), new Tag("healthy"), new Tag("salad dressing")))),
            new Ingredient("Ginger Root", Mass.grams(200), new ExpiryDate("2021-01-02"),
                new HashSet<>(Arrays.asList(new Tag("baking"), new Tag("healthy")))),
        };
    }

    public static ReadOnlyEntryBook<Ingredient> getSampleIngredientBook() {
        EntryBook<Ingredient> sampleIngredientBook = new EntryBook<>();
        for (Ingredient sampleIngredient : getSampleIngredients()) {
            sampleIngredientBook.add(sampleIngredient);
        }
        return sampleIngredientBook;
    }

    public static Recipe[] getSampleRecipe() {
        return new Recipe[] {
            new Recipe("Apple blue mix",
                Arrays.stream(getSampleIngredients())
                    .map((x)-> new IngredientReference(x.getName(), x.getQuantity()))
                    .collect(Collectors.toList()),
                new ArrayList<>(Arrays.asList(new Step("Put them on a table."),
                    new Step("Cut and chop them."),
                    new Step("Mix them."))),
                new HashSet<>(Arrays.asList(new Tag("fruit"), new Tag("simple")))),
            new Recipe("Blue custard cream",
                Arrays.stream(getSampleIngredients())
                    .map((x)-> new IngredientReference(x.getName(), x.getQuantity()))
                    .collect(Collectors.toList()),
                new ArrayList<>(Arrays.asList(new Step("Blend them."),
                    new Step("Stir them well."),
                    new Step("Leave it in the refrigerator for 8 hours"))),
                new HashSet<>(Arrays.asList(new Tag("sweet"), new Tag("cold"))))
        };
    }

    public static ReadOnlyEntryBook<Recipe> getSampleRecipeBook() {
        EntryBook<Recipe> sampleRecipeBook = new EntryBook<>();
        for (Recipe sampleRecipe : getSampleRecipe()) {
            sampleRecipeBook.add(sampleRecipe);
        }
        return sampleRecipeBook;
    }
}

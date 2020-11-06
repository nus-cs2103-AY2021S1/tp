package chopchop.model.util;

import java.util.Arrays;
import java.util.HashSet;

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
            new Ingredient("Apple", Count.of(8), null,
                new HashSet<>(Arrays.asList(new Tag("fruit"), new Tag("low calorie")))),
            new Ingredient("Apricot Preserves", Volume.millilitres(100), new ExpiryDate("2020-12-30"),
                new HashSet<>(Arrays.asList(new Tag("fruit"), new Tag("bakery")))),
            new Ingredient("Banana", Count.of(5), null,
                new HashSet<>(Arrays.asList(new Tag("fruit"), new Tag("low calorie")))),
            new Ingredient("Butter", Mass.grams(200), new ExpiryDate("2020-12-01"),
                new HashSet<>(Arrays.asList(new Tag("dairy"), new Tag("high calorie")))),
            new Ingredient("Brown Sugar", Mass.grams(300), new ExpiryDate("2021-07-03"),
                    new HashSet<>(Arrays.asList(new Tag("sweet"), new Tag("kitchen"), new Tag("bakery")))),
            new Ingredient("Cream", Mass.grams(460), new ExpiryDate("2020-11-15"),
                new HashSet<>(Arrays.asList(new Tag("high calorie"), new Tag("high sugar level")))),
            new Ingredient("Egg", Count.of(12), new ExpiryDate("2020-12-01"),
                new HashSet<>(Arrays.asList(
                    new Tag("high protein"), new Tag("breakfast"), new Tag("frequently used")))),
            new Ingredient("Flour", Mass.kilograms(3.5), null,
                new HashSet<>(Arrays.asList(new Tag("staple food"), new Tag("bakery"), new Tag("carbohydrate")))),
            new Ingredient("French Vinaigrette", Volume.millilitres(300), new ExpiryDate("2021-05-01"),
                new HashSet<>(Arrays.asList(new Tag("high calorie"), new Tag("salad dressing")))),
            new Ingredient("Ginger Root", Mass.grams(200), new ExpiryDate("2021-01-02"),
                new HashSet<>(Arrays.asList(new Tag("bakery"), new Tag("healthy")))),
            new Ingredient("Granulated Sugar", Mass.grams(200), new ExpiryDate("2021-10-10"),
                new HashSet<>(Arrays.asList(new Tag("sweet"), new Tag("kitchen"), new Tag("bakery")))),
            new Ingredient("Ground Cinnamon", Mass.grams(100), new ExpiryDate("2020-12-29"),
                new HashSet<>(Arrays.asList(new Tag("kitchen"), new Tag("bakery")))),
            new Ingredient("Honey", Volume.millilitres(650), new ExpiryDate("2021-07-06"),
                new HashSet<>(Arrays.asList(new Tag("sweet"), new Tag("high sugar level"), new Tag("bakery")))),
            new Ingredient("Olive Oil", Volume.litres(1.5), new ExpiryDate("2022-05-07"),
                new HashSet<>(Arrays.asList(new Tag("kitchen"), new Tag("healthy"), new Tag("salad dressing")))),
            new Ingredient("Pineapple Juice", Volume.litres(2), new ExpiryDate("2020-12-31"),
                new HashSet<>(Arrays.asList(new Tag("high sugar level"), new Tag("drink"), new Tag("fruit")))),
            new Ingredient("Salt", Mass.grams(200), new ExpiryDate("2021-06-24"),
                new HashSet<>(Arrays.asList(new Tag("kitchen"), new Tag("salty")))),
            new Ingredient("Vanilla Extract", Mass.grams(150), new ExpiryDate("2020-12-28"),
                new HashSet<>(Arrays.asList(new Tag("bakery"), new Tag("sweet")))),
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
            new Recipe("Apple Tart",
                Arrays.asList(
                    new IngredientReference("Apple", Count.of(5)),
                    new IngredientReference("Apricot Preserves", Volume.teaspoons(2)),
                    new IngredientReference("Butter", Volume.tablespoons(12)),
                    new IngredientReference("Brown Sugar", Volume.cups(0.6)),
                    new IngredientReference("Flour", Volume.cups(1.5)),
                    new IngredientReference("Granulated Sugar", Volume.tablespoons(1)),
                    new IngredientReference("Ground Cinnamon", Volume.teaspoons(1.25)),
                    new IngredientReference("Salt", Volume.teaspoons(0.5)),
                    new IngredientReference("Vanilla Extract", Volume.teaspoons(1))),
                Arrays.asList(
                    new Step("Peel, core and slice the apples."),
                    new Step("Preheat the oven to 350 Celsius Degree."),
                    new Step("In a large bowl, toss all apples, 1/3 cup of brown sugar, 1 teaspoon of cinnamon "
                        + "and all the vanilla together"),
                    new Step("In another large bowl, whisk together flour, all granulated sugar, all salt, and all "
                        + "the leftover cinnamon and brown sugar. Melt 10 tablespoons of butter and add it in. "
                        + "Stir until dough forms. Press mixture into a 10'' or 11'' tart pan with a removable bottom"
                        + ", pressing until dough is smooth."),
                    new Step("Arrange apples over the crust, sprinkle with granulated sugar and dot top with the "
                        + "leftover butter cut into small cubes. Bake until crust is golden and apples are tender, "
                        + "about one hour."),
                    new Step("Brush with melted apricot preserves and let cool slightly before slicing and serving.")),
                new HashSet<>(Arrays.asList(new Tag("fruit"), new Tag("bakery"), new Tag("Family's Favourite")))),

            new Recipe("Scrambled Eggs",
                Arrays.asList(
                    new IngredientReference("Butter", Mass.grams(20)),
                    new IngredientReference("Cream", Mass.grams(20)),
                    new IngredientReference("Egg", Count.of(2)),
                    new IngredientReference("Salt", Mass.grams(0.5))),
                Arrays.asList(
                    new Step("Lightly whisk 2 eggs, 20g of cream and a pinch of salt together until the "
                            + "mixture has just one consistency."),
                    new Step("Heat a small non-stick frying pan for a minute or so, then add the butter and "
                            + "let it melt. Don't allow the butter to brown or it will discolour the eggs."),
                    new Step("Pour in the egg mixture and let it sit, without stirring, for 20 seconds. "
                            + "Stir with a wooden spoon, lifting and folding it over from the bottom of the pan."),
                    new Step("Let it sit for another 10 seconds then sitr and fold again."),
                    new Step("Repeat until the eggs are softly set and slightly runny in places. Remove from "
                            + "the heat and leave for a moment to finish cooking."),
                    new Step("Give a final stir and serve the velvety scramble without delay.")),
                new HashSet<>(Arrays.asList(
                    new Tag("simple recipe"), new Tag("healthy"), new Tag("high protein")))),
            new Recipe("Sweet Banana Salad",
                Arrays.asList(
                    new IngredientReference("Banana", Count.of(2)),
                    new IngredientReference("French Vinaigrette", Volume.millilitres(20)),
                    new IngredientReference("Honey", Volume.millilitres(15))),
                Arrays.asList(
                    new Step("Cut the banana and put it into a bowl."),
                    new Step("Pour the French Vinaigrette into the bowl, and mix them well."),
                    new Step("Add honey at the top.")),
                new HashSet<>(Arrays.asList(
                    new Tag("simple recipe"), new Tag("healthy"), new Tag("fruit"))))
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

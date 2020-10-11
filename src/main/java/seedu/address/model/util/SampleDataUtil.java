package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyWishfulShrinking;
import seedu.address.model.WishfulShrinking;
import seedu.address.model.commons.Calories;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code WishfulShrinking} with sample data.
 */
public class SampleDataUtil {
    public static Recipe[] getSampleRecipes() {
        return new Recipe[] {
            new Recipe(new Name("Alex Yeoh"),
                    "instructions", "images/healthy1.jpg",
                    new ArrayList<>(Arrays.asList(new Ingredient[]{new Ingredient("87438807", "1 cup")})),
                    new Calories(10), getTagSet("healthy", "fast")),
            new Recipe(new Name("Bernice Yu"),
                    "instructions", "images/healthy2.jpg",
                    new ArrayList<>(Arrays.asList(new Ingredient[]{new Ingredient("87438807", "1 teaspoon")})),
                            new Calories(10), getTagSet("yummy")),
            new Recipe(new Name("Charlotte Oliveiro"),
                    "instructions", "images/healthy3.jpg",
                    new ArrayList<>(Arrays.asList(new Ingredient[]{new Ingredient("87438807", "250g")})),
                    new Calories(10), getTagSet("can heat up")),
            new Recipe(new Name("David Li"),
                    "instructions", "images/healthy4.jpg",
                    new ArrayList<>(Arrays.asList(new Ingredient[]{new Ingredient("87438807", "a pinch")})),
                    new Calories(10), getTagSet("full")),
            new Recipe(new Name("Irfan Ibrahim"),
                    "instructions", "images/healthy5.jpg",
                    new ArrayList<>(Arrays.asList(new Ingredient[]{new Ingredient("87438807", "3 tablespoons")})),
                    new Calories(10), getTagSet("can heat up")),
            new Recipe(new Name("Roy Balakrishnan"),
                    "instructions", "images/healthy6.jpg",
                    new ArrayList<>(Arrays.asList(new Ingredient[]{new Ingredient("87438807", "1 cup")})),
                    new Calories(10), getTagSet("healthy"))
        };
    }

    public static ReadOnlyWishfulShrinking getSampleWishfulShrinking() {
        WishfulShrinking sampleAb = new WishfulShrinking();
        for (Recipe sampleRecipe : getSampleRecipes()) {
            sampleAb.addRecipe(sampleRecipe);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}

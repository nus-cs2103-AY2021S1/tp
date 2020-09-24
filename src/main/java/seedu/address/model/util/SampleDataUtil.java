package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyWishfulShrinking;
import seedu.address.model.WishfulShrinking;
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
            new Recipe(new Name("Alex Yeoh"), new Ingredient[]{new Ingredient("87438807")}),
            new Recipe(new Name("Bernice Yu"), new Ingredient[]{new Ingredient("87438807")}),
            new Recipe(new Name("Charlotte Oliveiro"), new Ingredient[]{new Ingredient("87438807")}),
            new Recipe(new Name("David Li"), new Ingredient[]{new Ingredient("87438807")}),
            new Recipe(new Name("Irfan Ibrahim"), new Ingredient[]{new Ingredient("87438807")}),
            new Recipe(new Name("Roy Balakrishnan"), new Ingredient[]{new Ingredient("87438807")})
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

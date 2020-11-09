package seedu.address.testutil;

import seedu.address.model.RecipeList;
import seedu.address.model.recipe.Recipe;

/**
 * A utility class to help with building RecipeList objects.
 * Example usage: <br>
 *     {@code RecipeList ab = new RecipeListBuilder().withRecipe("Apple Pie").build();}
 */
public class RecipeListBuilder {

    private RecipeList recipeList;

    public RecipeListBuilder() {
        recipeList = new RecipeList();
    }

    public RecipeListBuilder(RecipeList recipeList) {
        this.recipeList = recipeList;
    }

    /**
     * Adds a new {@code Location} to the {@code LocationList} that we are building.
     */
    public RecipeListBuilder withRecipe(Recipe recipe) {
        recipeList.addRecipe(new RecipeBuilder(recipe).build());
        return this;
    }

    public RecipeList build() {
        return recipeList;
    }

}

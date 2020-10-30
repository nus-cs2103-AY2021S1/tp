// StubbedModel.java

package chopchop.testutil;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import chopchop.model.EntryBook;
import chopchop.model.ReadOnlyEntryBook;
import chopchop.model.attributes.Step;
import chopchop.model.attributes.Tag;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.ingredient.IngredientReference;
import chopchop.model.recipe.Recipe;

public class StubbedModel extends chopchop.model.ModelStub {

    private final EntryBook<Recipe> recipes;
    private final EntryBook<Ingredient> ingredients;

    /**
     * Makes a new stubbed model that has some functionality to contain and replace recipes.
     */
    public StubbedModel() {
        this.recipes = new EntryBook<>();
        this.ingredients = new EntryBook<>();

        this.recipes.setAll(List.of(
            TypicalRecipes.APRICOT_SALAD,
            TypicalRecipes.BANANA_SALAD,
            TypicalRecipes.CUSTARD_SALAD
        ));

        this.recipes.add(new Recipe(
            "Peanut Salad", List.of(TypicalIngredients.APRICOT_REF), List.of(new Step("mix")),
            Set.of(new Tag("gross"), new Tag("round"))
        ));

        this.ingredients.setAll(List.of(
            TypicalIngredients.APRICOT,
            TypicalIngredients.BANANA,
            TypicalIngredients.CUSTARD,
            TypicalIngredients.BAKED_BEANS
        ));

        this.ingredients.add(new Ingredient(
            "Peanut", Optional.empty(), Optional.empty(),
            Set.of(new Tag("brown"), new Tag("round"))
        ));
    }

    @Override
    public ReadOnlyEntryBook<Ingredient> getIngredientBook() {
        return this.ingredients;
    }

    @Override
    public ReadOnlyEntryBook<Recipe> getRecipeBook() {
        return this.recipes;
    }

    @Override
    public Optional<Recipe> findRecipeWithName(String name) {
        return this.recipes.getEntryList()
            .stream()
            .filter(recipe -> recipe.getName().equalsIgnoreCase(name))
            .findFirst();
    }

    @Override
    public Optional<Ingredient> findIngredientWithName(String name) {
        return this.ingredients.getEntryList()
            .stream()
            .filter(ingredient -> ingredient.getName().equalsIgnoreCase(name))
            .findFirst();
    }

    @Override
    public void setRecipe(Recipe target, Recipe editedRecipe) {
        this.recipes.set(target, editedRecipe);
    }

    @Override
    public void setIngredient(Ingredient target, Ingredient edited) {
        this.ingredients.set(target, edited);
    }

    @Override
    public void addRecipe(Recipe target) {
        this.recipes.add(target);
    }

    @Override
    public void addIngredient(Ingredient target) {
        this.ingredients.add(target);
    }

    @Override
    public void deleteIngredient(Ingredient target) {
        this.ingredients.remove(target);
    }

    // do nothing for these
    @Override
    public void addRecipeUsage(Recipe recipe) {
    }

    @Override
    public void removeRecipeUsage(Recipe recipe) {
    }

    @Override
    public void addIngredientUsage(IngredientReference ingredient) {
    }

    @Override
    public void removeIngredientUsage(IngredientReference ingredient) {
    }
}

// StubbedModel.java

package chopchop.testutil;

import static chopchop.testutil.TypicalUsages.INGREDIENT_A_A;
import static chopchop.testutil.TypicalUsages.INGREDIENT_B_A;
import static chopchop.testutil.TypicalUsages.RECIPE_A_A;
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
import chopchop.model.UsageList;
import chopchop.model.usage.RecipeUsage;
import chopchop.model.usage.IngredientUsage;
import javafx.collections.ObservableList;

public class StubbedModel extends chopchop.model.ModelStub {

    private final EntryBook<Recipe> recipes;
    private final EntryBook<Ingredient> ingredients;
    private final UsageList<RecipeUsage> recipeUsageList = new UsageList<>();
    private final UsageList<IngredientUsage> ingredientUsageList = new UsageList<>();

    private StubbedModel(boolean isEmpty) {
        this.recipes = new EntryBook<>();
        this.ingredients = new EntryBook<>();


        if (!isEmpty) {
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

            this.recipeUsageList.add(RECIPE_A_A);

            this.ingredientUsageList.add(INGREDIENT_A_A);
            this.ingredientUsageList.add(INGREDIENT_B_A);
        }
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
    public ObservableList<Recipe> getFilteredRecipeList() {
        return this.recipes.getEntryList();
    }

    @Override
    public ObservableList<Ingredient> getFilteredIngredientList() {
        return this.ingredients.getEntryList();
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
    public void addRecipe(Recipe target) {
        this.recipes.add(target);
    }

    @Override
    public void deleteRecipe(Recipe target) {
        this.recipes.remove(target);
    }

    @Override
    public void setIngredient(Ingredient target, Ingredient edited) {
        this.ingredients.set(target, edited);
    }

    @Override
    public void addIngredient(Ingredient target) {
        this.ingredients.add(target);
    }

    @Override
    public void deleteIngredient(Ingredient target) {
        this.ingredients.remove(target);
    }

    public void addRecipeUsage(RecipeUsage ru) {
        this.recipeUsageList.add(ru);
    }

    @Override
    public void addRecipeUsage(Recipe recipe) {
    }

    @Override
    public void removeRecipeUsage(Recipe recipe) {
        this.recipeUsageList.pop(recipe.getName());
    }

    @Override
    public void addIngredientUsage(IngredientReference ingredient) {
    }

    public void addIngredientUsage(IngredientUsage iu) {
        this.ingredientUsageList.add(iu);
    }

    @Override
    public void removeIngredientUsage(IngredientReference ingredient) {
        this.ingredientUsageList.pop(ingredient.getName());
    }

    @Override
    public UsageList<RecipeUsage> getRecipeUsageList() {
        return this.recipeUsageList;
    }

    @Override
    public UsageList<IngredientUsage> getIngredientUsageList() {
        return this.ingredientUsageList;
    }

    @Override
    public void setRecipeUsageList(UsageList<RecipeUsage> ul) {
        this.recipeUsageList.setAll(ul);
    }

    @Override
    public void setIngredientUsageList(UsageList<IngredientUsage> ul) {
        this.ingredientUsageList.setAll(ul);
    }


    /**
     * Returns an empty stubbed model.
     */
    public static StubbedModel empty() {
        return new StubbedModel(/* isEmpty: */ true);
    }

    /**
     * Returns a stubbed model filled with stuff.
     */
    public static StubbedModel filled() {
        return new StubbedModel(/* isEmpty: */ false);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof StubbedModel)) {
            return false;
        }

        StubbedModel other = (StubbedModel) obj;
        return this.recipes.equals(other.recipes)
            && this.ingredients.equals(other.ingredients)
            && this.ingredientUsageList.equals(other.ingredientUsageList)
            && this.recipeUsageList.equals(other.recipeUsageList);
    }
}

package seedu.address.logic.commands.recipe;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INGREDIENTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
import static seedu.address.testutil.TypicalRecipes.getTypicalWishfulShrinking;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecommendPredicate;

public class RecommendCommandTest {
    private Model model;
    private Model expectedModel;
    private ArrayList<String> keywords;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());
        expectedModel = new ModelManager(model.getWishfulShrinking(), new UserPrefs());
        model.updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
        ObservableList<Ingredient> ingredients = model.getFilteredIngredientList();
        keywords = new ArrayList<>();
        for (int i = 0; i < ingredients.size(); i++) {
            keywords.add(ingredients.get(i).getValue());
        }
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        RecommendPredicate pred = new RecommendPredicate(keywords);
        expectedModel.updateFilteredRecipeList(pred);
        ObservableList<Recipe> recipes = expectedModel.getFilteredRecipeList();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < recipes.size(); i++) {
            builder.append((i + 1) + ". " + recipes.get(i).toString() + "\n");
        }
        assertCommandSuccess(new RecommendCommand(), model,
                RecommendCommand.MESSAGE_SUCCESS + builder.toString(), expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);
        RecommendPredicate pred = new RecommendPredicate(keywords);
        expectedModel.updateFilteredRecipeList(pred);
        ObservableList<Recipe> recipes = expectedModel.getFilteredRecipeList();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < recipes.size(); i++) {
            builder.append((i + 1) + ". " + recipes.get(i).toString() + "\n");
        }
        assertCommandSuccess(new RecommendCommand(), model,
                RecommendCommand.MESSAGE_SUCCESS + builder.toString(), expectedModel);
    }
}

package chopchop.logic.commands;

import static chopchop.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chopchop.testutil.TypicalIngredients.APRICOT;
import static chopchop.testutil.TypicalIngredients.BANANA;
import static chopchop.testutil.TypicalIngredients.getTypicalIngredientBook;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import chopchop.model.attributes.ExpiryDate;
import chopchop.model.attributes.ExpiryDateOnOrBeforePredicate;
import chopchop.model.attributes.TagContainsKeywordsPredicate;
import chopchop.model.usage.IngredientUsage;
import chopchop.model.usage.RecipeUsage;
import chopchop.model.EntryBook;
import chopchop.model.Model;
import chopchop.model.ModelManager;
import chopchop.model.UsageList;
import chopchop.model.UserPrefs;

public class FilterIngredientCommandTest {
    private Model model = new ModelManager(new EntryBook<>(), getTypicalIngredientBook(), new UsageList<RecipeUsage>(),
        new UsageList<IngredientUsage>(), new UserPrefs());
    private Model expectedModel = new ModelManager(new EntryBook<>(), getTypicalIngredientBook(),
        new UsageList<RecipeUsage>(), new UsageList<IngredientUsage>(), new UserPrefs());

    @Test
    public void execute_multipleTags_noIngredientFound() {
        var tagPredicate = prepareTagPredicate("Sweet", "Vegetable");
        var command = new FilterIngredientCommand(null, tagPredicate);
        expectedModel.updateFilteredIngredientList(tagPredicate);
        assertCommandSuccess(command, model, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredIngredientList());
    }

    @Test
    public void execute_multipleTags_multipleRecipesFound() {
        var tagPredicate = prepareTagPredicate("Sweet", "Fruit");
        var command = new FilterIngredientCommand(null, tagPredicate);
        expectedModel.updateFilteredIngredientList(tagPredicate);
        assertCommandSuccess(command, model, expectedModel);
        assertEquals(Arrays.asList(APRICOT, BANANA), model.getFilteredIngredientList());
    }

    @Test
    public void execute_multipleIngredients_multipleIngredientsFound() {
        var expiryPredicate = prepareExpiryPredicate("2023-12-12");
        var command = new FilterIngredientCommand(expiryPredicate, null);
        expectedModel.updateFilteredIngredientList(expiryPredicate);
        assertCommandSuccess(command, model, expectedModel);
        assertEquals(Arrays.asList(APRICOT, BANANA), model.getFilteredIngredientList());
    }

    @Test
    public void execute_multipleTagsIngredients_multipleIngredientsFound() {
        var tagPredicate = prepareTagPredicate("Sweet", "Fruit");
        var expiryPredicate = prepareExpiryPredicate("2023-01-01");
        var command = new FilterIngredientCommand(expiryPredicate, tagPredicate);
        expectedModel.updateFilteredIngredientList(expiryPredicate.and(tagPredicate));
        assertCommandSuccess(command, model, expectedModel);
        assertEquals(Arrays.asList(APRICOT, BANANA), model.getFilteredIngredientList());
    }

    @Test
    public void execute_multipleTagsIngredients_noIngredientFound() {
        var tagPredicate = prepareTagPredicate("Spicy", "Sweet");
        var expiryPredicate = prepareExpiryPredicate("2020-01-01");
        var command = new FilterIngredientCommand(expiryPredicate, tagPredicate);
        expectedModel.updateFilteredIngredientList(expiryPredicate.and(tagPredicate));
        assertCommandSuccess(command, model, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredIngredientList());
    }

    /**
     * Parses {@code userInputs} into a {@code TagContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate prepareTagPredicate(String... userInputs) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInputs));
    }

    /**
     * Parses {@code userInput} into a {@code ExpiryDateOnOrBeforePredicate}.
     */
    private ExpiryDateOnOrBeforePredicate prepareExpiryPredicate(String userInput) {
        return new ExpiryDateOnOrBeforePredicate(new ExpiryDate(userInput));
    }
}


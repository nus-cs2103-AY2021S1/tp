package chopchop.logic.commands;

import static chopchop.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chopchop.testutil.TypicalIngredients.APRICOT;
import static chopchop.testutil.TypicalIngredients.BANANA;
import static chopchop.testutil.TypicalIngredients.getTypicalIngredientBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;

import chopchop.model.attributes.ExpiryDate;
import chopchop.model.attributes.ExpiryDateMatchesKeywordsPredicate;
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
    public void equals() {
        var firstTagPredicate = new TagContainsKeywordsPredicate(Collections.singletonList("firstTag"));
        var secondTagPredicate = new TagContainsKeywordsPredicate(Collections.singletonList("secondTag"));

        var firstExpiryPredicate = new ExpiryDateMatchesKeywordsPredicate(new ExpiryDate("2020-12-31"));
        var secondExpiryPredicate = new ExpiryDateMatchesKeywordsPredicate(new ExpiryDate("2022-01-01"));

        var filterFirstCommand = new FilterIngredientCommand(firstExpiryPredicate, firstTagPredicate);
        var filterSecondCommand = new FilterIngredientCommand(secondExpiryPredicate, firstTagPredicate);
        var filterThirdCommand = new FilterIngredientCommand(firstExpiryPredicate, secondTagPredicate);
        var filterFourthCommand = new FilterIngredientCommand(secondExpiryPredicate, secondTagPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same value -> returns true
        var filterFirstCommandCopy = new FilterIngredientCommand(firstExpiryPredicate, firstTagPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different values -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
        assertFalse(filterFirstCommand.equals(filterThirdCommand));
        assertFalse(filterFirstCommand.equals(filterFourthCommand));
    }

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
     * Parses {@code userInput} into a {@code ExpiryDateMatchesKeywordsPredicate}.
     */
    private ExpiryDateMatchesKeywordsPredicate prepareExpiryPredicate(String userInput) {
        return new ExpiryDateMatchesKeywordsPredicate(new ExpiryDate(userInput));
    }
}


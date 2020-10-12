package chopchop.logic.commands;

import static chopchop.commons.core.Messages.MESSAGE_RECIPES_LISTED_OVERVIEW;
import static chopchop.testutil.TypicalRecipes.APRICOT_SALAD;
import static chopchop.testutil.TypicalRecipes.BANANA_SALAD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static chopchop.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chopchop.testutil.TypicalRecipes.getTypicalRecipeBook;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import chopchop.model.Model;
import chopchop.model.ModelManager;
import chopchop.model.UserPrefs;
import chopchop.model.attributes.NameContainsKeywordsPredicate;
import chopchop.model.ingredient.IngredientBook;

public class FindRecipeCommandTest {
    private Model model = new ModelManager(getTypicalRecipeBook(), new IngredientBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalRecipeBook(), new IngredientBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
            new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
            new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindRecipeCommand(firstPredicate);
        FindCommand findSecondCommand = new FindRecipeCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindRecipeCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindRecipeCommand command = new FindRecipeCommand(predicate);
        expectedModel.updateFilteredRecipeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredRecipeList());
    }

    @Test
    public void execute_multipleKeywords_multipleRecipesFound() {
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate = preparePredicate("apricot salad banana salad");
        FindCommand command = new FindRecipeCommand(predicate);
        expectedModel.updateFilteredRecipeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APRICOT_SALAD, BANANA_SALAD), model.getFilteredRecipeList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.ItemParserUtil.REGEX_ENTRIES;
import static seedu.address.testutil.TypicalItems.getTypicalItemList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.LocationList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RecipeList;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.NameMatchesKeywordsPredicate;
import seedu.address.testutil.TypicalItems;
import seedu.address.ui.DisplayedInventoryType;

/**
 * Contains integration tests (interaction with the Model) for {@code FindItemCommand}.
 */
public class FindItemCommandTest {

    private Model model = new ModelManager(getTypicalItemList(), new LocationList(),
            new RecipeList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalItemList(), new LocationList(),
            new RecipeList(), new UserPrefs());

    @Test
    public void equals() {
        NameMatchesKeywordsPredicate firstPredicate =
                new NameMatchesKeywordsPredicate(Collections.singletonList("first"));
        NameMatchesKeywordsPredicate secondPredicate =
                new NameMatchesKeywordsPredicate(Collections.singletonList("second"));

        FindItemCommand findFirstCommand = new FindItemCommand(firstPredicate);
        FindItemCommand findSecondCommand = new FindItemCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindItemCommand findFirstCommandCopy = new FindItemCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_validKeywords_noItemFound() {
        String expectedMessage = Messages.MESSAGE_NO_ITEM_MATCH;

        NameMatchesKeywordsPredicate predicate = preparePredicate("Orange Strawberry");
        FindItemCommand command = new FindItemCommand(predicate);

        expectedModel.updateFilteredItemList(predicate);

        CommandResult expectedResult = new CommandResult(expectedMessage,
                false, false, DisplayedInventoryType.ITEMS);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_multipleKeywords_multipleItemsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_ITEMS_LISTED_OVERVIEW, 3);

        NameMatchesKeywordsPredicate predicate = preparePredicate("Apple, Banana,Carrot");
        FindItemCommand command = new FindItemCommand(predicate);

        expectedModel.updateFilteredItemList(predicate);

        CommandResult expectedResult = new CommandResult(expectedMessage,
                false, false, DisplayedInventoryType.ITEMS);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertEquals(Arrays.asList(TypicalItems.APPLE, TypicalItems.BANANA, TypicalItems.APPLE_PIE_ITEM),
                expectedModel.getFilteredItemList());
    }

    /**
     * Parses {@code userInput} into a {@code NameMatchesKeywordsPredicate}.
     */
    private NameMatchesKeywordsPredicate preparePredicate(String userInput) {
        List<String> inputs = Arrays.stream(userInput.split(REGEX_ENTRIES))
                .map(String::strip)
                .collect(Collectors.toList());
        return new NameMatchesKeywordsPredicate(inputs);
    }
}

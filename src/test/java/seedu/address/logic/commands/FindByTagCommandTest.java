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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.LocationList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RecipeList;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.TagMatchesKeywordsPredicate;
import seedu.address.testutil.TypicalItems;
import seedu.address.ui.DisplayedInventoryType;

/**
 * Contains integration tests (interaction with the Model) for {@code FindByTagCommand}.
 */
public class FindByTagCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalItemList(), new LocationList(),
                new RecipeList(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalItemList(), new LocationList(),
                new RecipeList(), new UserPrefs());
    }

    @Test
    public void equals() {
        TagMatchesKeywordsPredicate firstPredicate =
                new TagMatchesKeywordsPredicate(Collections.singletonList("first"));
        TagMatchesKeywordsPredicate secondPredicate =
                new TagMatchesKeywordsPredicate(Collections.singletonList("second"));

        FindByTagCommand findFirstCommand = new FindByTagCommand(firstPredicate);
        FindByTagCommand findSecondCommand = new FindByTagCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindByTagCommand findFirstCommandCopy = new FindByTagCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noItemFound() {
        String expectedMessage = Messages.MESSAGE_NO_ITEM_MATCH;

        TagMatchesKeywordsPredicate predicate = preparePredicate(" ");
        FindByTagCommand command = new FindByTagCommand(predicate);

        expectedModel.updateFilteredItemList(predicate);

        CommandResult expectedResult = new CommandResult(expectedMessage,
                false, false, DisplayedInventoryType.ITEMS);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredItemList());
    }

    @Test
    public void execute_substringKeyword_multipleItemsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_ITEMS_LISTED_OVERVIEW, 2);

        TagMatchesKeywordsPredicate predicate = preparePredicate("ab");
        FindByTagCommand command = new FindByTagCommand(predicate);

        expectedModel.updateFilteredItemList(predicate);

        CommandResult expectedResult = new CommandResult(expectedMessage,
                false, false, DisplayedInventoryType.ITEMS);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertEquals(Arrays.asList(TypicalItems.APPLE, TypicalItems.PEAR),
                expectedModel.getFilteredItemList());
    }

    @Test
    public void execute_multipleKeywords_multipleItemsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_ITEMS_LISTED_OVERVIEW, 2);

        TagMatchesKeywordsPredicate predicate = preparePredicate("abc,tuturu, bertmodel");
        FindByTagCommand command = new FindByTagCommand(predicate);

        expectedModel.updateFilteredItemList(predicate);

        CommandResult expectedResult = new CommandResult(expectedMessage,
                false, false, DisplayedInventoryType.ITEMS);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertEquals(Arrays.asList(TypicalItems.APPLE, TypicalItems.PEAR),
                expectedModel.getFilteredItemList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private TagMatchesKeywordsPredicate preparePredicate(String userInput) {
        List<String> inputs = Arrays.stream(userInput.strip().split(REGEX_ENTRIES))
                .map(String::strip)
                .collect(Collectors.toList());
        return new TagMatchesKeywordsPredicate(inputs);
    }
}


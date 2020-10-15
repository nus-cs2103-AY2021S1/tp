package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TAGS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTags.MYFILE3;
import static seedu.address.testutil.TypicalTags.MYFILE4;
import static seedu.address.testutil.TypicalTags.MYFILE5;
import static seedu.address.testutil.TypicalTags.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.TagNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TagNameContainsKeywordsPredicate firstPredicate =
                new TagNameContainsKeywordsPredicate(Collections.singletonList("first"));
        TagNameContainsKeywordsPredicate secondPredicate =
                new TagNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTagFound() {
        String expectedMessage = String.format(MESSAGE_TAGS_LISTED_OVERVIEW, 0);
        TagNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredTagList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTagList());
    }

    @Test
    public void execute_multipleKeywords_multipleTagsFound() {
        String expectedMessage = String.format(MESSAGE_TAGS_LISTED_OVERVIEW, 3);
        TagNameContainsKeywordsPredicate predicate = preparePredicate("3 4 5");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredTagList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MYFILE3, MYFILE4, MYFILE5), model.getFilteredTagList());
    }

    /**
     * Parses {@code userInput} into a {@code TagNameContainsKeywordsPredicate}.
     */
    private TagNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TagNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

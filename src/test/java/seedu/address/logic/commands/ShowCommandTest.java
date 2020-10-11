package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TAG;
import static seedu.address.testutil.TypicalTags.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.model.tag.TagNameEqualsKeywordPredicate;

public class ShowCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TagName firstPredicateTagName = new TagName("first");
        TagName secondPredicateTagName = new TagName("second");

        TagNameEqualsKeywordPredicate firstPredicate = new TagNameEqualsKeywordPredicate(
                firstPredicateTagName);
        TagNameEqualsKeywordPredicate secondPredicate = new TagNameEqualsKeywordPredicate(
                secondPredicateTagName);

        ShowCommand showFirstCommand = new ShowCommand(firstPredicate);
        ShowCommand showSecondCommand = new ShowCommand(secondPredicate);

        // same object -> returns true
        assertTrue(showFirstCommand.equals(showFirstCommand));

        // same values -> returns true
        ShowCommand showFirstCommandCopy = new ShowCommand(firstPredicate);
        assertTrue(showFirstCommand.equals(showFirstCommandCopy));

        // different types -> returns false
        assertFalse(showFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(showFirstCommand.equals(showSecondCommand));
    }

    @Test
    public void execute_invalidTagName_throwsCommandException() {
        TagNameEqualsKeywordPredicate predicate = new TagNameEqualsKeywordPredicate(new TagName("Invalid Tag Name"));
        ShowCommand showCommand = new ShowCommand(predicate);

        assertCommandFailure(showCommand, model, Messages.MESSAGE_INVALID_TAG_INPUT);
    }

    @Test
    public void execute_validTagName_throwsCommandException() {
        Tag tagToBeShown = model.getFilteredTagList().get(INDEX_FIRST_TAG.getZeroBased());
        TagNameEqualsKeywordPredicate predicate = new TagNameEqualsKeywordPredicate(tagToBeShown.getTagName());
        ShowCommand showCommand = new ShowCommand(predicate);

        String expectedMessage = String.format(showCommand.MESSAGE_SUCCESS,
                tagToBeShown.getTagName(), tagToBeShown.getFileAddress());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(showCommand, model, expectedMessage, expectedModel);
    }
}

package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTags.MYFILE;
import static seedu.address.testutil.TypicalTags.MYFILE2;
import static seedu.address.testutil.TypicalTags.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.TagName;
import seedu.address.model.tag.TagNameEqualsKeywordPredicate;
import seedu.address.testutil.TypicalTags;

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
    public void execute_validTagName_showTagFileSuccess() {
        TagNameEqualsKeywordPredicate firstPredicate =
                new TagNameEqualsKeywordPredicate(MYFILE.getTagName());
        TagNameEqualsKeywordPredicate secondPredicate =
                new TagNameEqualsKeywordPredicate(TypicalTags.MYFILE2.getTagName());

        ShowCommand firstShowCommand = new ShowCommand(firstPredicate);
        ShowCommand secondShowCommand = new ShowCommand(secondPredicate);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // show tag that has more than 1 label
        StringBuilder firstBuilder = new StringBuilder();
        firstBuilder.append(String.format(firstShowCommand.MESSAGE_SUCCESS,
                MYFILE.getTagName(), MYFILE.getFileAddress()));
        firstBuilder.append("\nLabels: ");
        MYFILE.getLabels().forEach(firstBuilder::append);
        String firstExpectedMessage = firstBuilder.toString().trim();

        // show tag that has only 1 label
        StringBuilder secondBuilder = new StringBuilder();
        secondBuilder.append(String.format(secondShowCommand.MESSAGE_SUCCESS,
                MYFILE2.getTagName(), MYFILE2.getFileAddress()));
        secondBuilder.append("\nLabel: ");
        MYFILE2.getLabels().forEach(secondBuilder::append);
        String secondExpectedMessage = secondBuilder.toString().trim();


        assertCommandSuccess(firstShowCommand, model, firstExpectedMessage, expectedModel);
        assertCommandSuccess(secondShowCommand, model, secondExpectedMessage, expectedModel);
    }
}

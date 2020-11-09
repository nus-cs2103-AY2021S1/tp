package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.find.FindContactCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ContactMatchesFindKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindContactCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());

    @Test
    public void equals() {
        ContactMatchesFindKeywordPredicate firstPredicate =
                new ContactMatchesFindKeywordPredicate(Collections.singletonList("first"), "first");
        ContactMatchesFindKeywordPredicate secondPredicate =
                new ContactMatchesFindKeywordPredicate(Collections.singletonList("second"), "second");

        FindContactCommand findFirstCommand = new FindContactCommand(firstPredicate);
        FindContactCommand findSecondCommand = new FindContactCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindContactCommand findFirstCommandCopy = new FindContactCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_notExistingName_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ContactMatchesFindKeywordPredicate predicate = preparePredicateName("Joseph Zayn");
        FindContactCommand command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "CONTACT", expectedModel);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ContactMatchesFindKeywordPredicate predicate = preparePredicateName("Kurz Elle Kunz");
        FindContactCommand command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "CONTACT", expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_notExistingTag_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ContactMatchesFindKeywordPredicate predicate = preparePredicateTag("clown");
        FindContactCommand command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "CONTACT", expectedModel);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_existingTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ContactMatchesFindKeywordPredicate predicate = preparePredicateTag("friends");
        FindContactCommand command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "CONTACT", expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_matchNameAndTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        ContactMatchesFindKeywordPredicate predicate = preparePredicate("pauline elle carl benson", "friends");
        FindContactCommand command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "CONTACT", expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_notMatchNameAndTag_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ContactMatchesFindKeywordPredicate predicate = preparePredicate("elle carl", "friends");
        FindContactCommand command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "CONTACT", expectedModel);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredPersonList());
    }

    /**
     * Parses nameInput into a {@code ContactMatchesFindKeywordPredicate}.
     */
    private ContactMatchesFindKeywordPredicate preparePredicateName(String nameInput) {
        return new ContactMatchesFindKeywordPredicate(Arrays.asList(nameInput.split("\\s+")));
    }

    /**
     * Parses nameInput and tagInput into a {@code ContactMatchesFindKeywordPredicate}.
     */
    private ContactMatchesFindKeywordPredicate preparePredicate(String nameInput, String tagInput) {
        return new ContactMatchesFindKeywordPredicate(Arrays.asList(nameInput.split("\\s+")), tagInput);
    }

    /**
     * Parses tagInput into a {@code ContactMatchesFindKeywordPredicate}.
     */
    private ContactMatchesFindKeywordPredicate preparePredicateTag(String tagInput) {
        return new ContactMatchesFindKeywordPredicate(tagInput);
    }
}

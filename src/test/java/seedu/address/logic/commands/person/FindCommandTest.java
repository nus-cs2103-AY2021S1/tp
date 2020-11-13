package seedu.address.logic.commands.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.person.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.UserPrefs;
import seedu.address.model.person.ModelPerson;
import seedu.address.model.person.ModelPersonManager;
import seedu.address.model.person.person.NameDescriptionContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private ModelPerson modelPerson = new ModelPersonManager(getTypicalAddressBook(), new UserPrefs());
    private ModelPerson expectedModelPerson = new ModelPersonManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameDescriptionContainsKeywordsPredicate firstPredicate =
                new NameDescriptionContainsKeywordsPredicate(Collections.singletonList("first"));
        NameDescriptionContainsKeywordsPredicate secondPredicate =
                new NameDescriptionContainsKeywordsPredicate(Collections.singletonList("second"));

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
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0);
        NameDescriptionContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModelPerson.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, modelPerson, expectedMessage, expectedModelPerson);
        assertEquals(Collections.emptyList(), modelPerson.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 3);
        NameDescriptionContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModelPerson.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, modelPerson, expectedMessage, expectedModelPerson);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), modelPerson.getFilteredPersonList());
    }

    @Test
    public void execute_multipleDescriptionKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 3);
        NameDescriptionContainsKeywordsPredicate predicate = preparePredicate("user business analyst");
        FindCommand command = new FindCommand(predicate);
        expectedModelPerson.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, modelPerson, expectedMessage, expectedModelPerson);
        assertEquals(Arrays.asList(ALICE, CARL, GEORGE), modelPerson.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameDescriptionKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 4);
        NameDescriptionContainsKeywordsPredicate predicate = preparePredicate("Kurz user");
        FindCommand command = new FindCommand(predicate);
        expectedModelPerson.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, modelPerson, expectedMessage, expectedModelPerson);
        // Fiona description contains "Kurz"
        assertEquals(Arrays.asList(ALICE, CARL, FIONA, GEORGE), modelPerson.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameDescriptionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameDescriptionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

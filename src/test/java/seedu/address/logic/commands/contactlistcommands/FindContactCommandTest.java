package seedu.address.logic.commands.contactlistcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.contact.TypicalContacts.ALICE;
import static seedu.address.testutil.contact.TypicalContacts.BENSON;
import static seedu.address.testutil.contact.TypicalContacts.CARL;
import static seedu.address.testutil.contact.TypicalContacts.DANIEL;
import static seedu.address.testutil.contact.TypicalContacts.ELLE;
import static seedu.address.testutil.contact.TypicalContacts.FIONA;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalContactList;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.ContactContainsTagsPredicate;
import seedu.address.model.contact.ContactNameContainsKeywordsPredicate;
import seedu.address.model.contact.FindContactCriteria;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.contact.FindContactCriteriaBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindContactCommand}.
 */
public class FindContactCommandTest {

    private Model model = new ModelManager(new ModuleList(), new ModuleList(),
            getTypicalContactList(), new TodoList(), new EventList(), new UserPrefs());
    private Model expectedModel = new ModelManager(new ModuleList(), new ModuleList(),
            getTypicalContactList(), new TodoList(), new EventList(), new UserPrefs());

    @Test
    public void constructor_nullFindContactCriteria_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindContactCommand(null));
    }

    @Test
    public void execute_multipleKeywords_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 3);
        ContactNameContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz");
        FindContactCriteria findContactCriteria = new FindContactCriteriaBuilder()
                .withNamePredicate(predicate).build();
        FindContactCommand command = new FindContactCommand(findContactCriteria);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredContactList());
    }

    @Test
    public void execute_multipleKeywords_noContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0);
        ContactNameContainsKeywordsPredicate predicate = prepareNamePredicate("name test find");
        FindContactCriteria findContactCriteria = new FindContactCriteriaBuilder()
                .withNamePredicate(predicate).build();
        FindContactCommand command = new FindContactCommand(findContactCriteria);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void execute_multipleTagKeywords_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 3);
        ContactContainsTagsPredicate predicate = prepareTagPredicate("friends owesMoney family");
        FindContactCriteria findContactCriteria = new FindContactCriteriaBuilder()
                .withTagPredicate(predicate).build();
        FindContactCommand command = new FindContactCommand(findContactCriteria);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredContactList());
    }

    @Test
    public void execute_multipleTagKeywords_noContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0);
        ContactContainsTagsPredicate predicate = prepareTagPredicate("name test find");
        FindContactCriteria findContactCriteria = new FindContactCriteriaBuilder()
                .withTagPredicate(predicate).build();
        FindContactCommand command = new FindContactCommand(findContactCriteria);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void equals() {
        ContactNameContainsKeywordsPredicate namePredicate =
                new ContactNameContainsKeywordsPredicate(Collections.singletonList("first"));
        ContactContainsTagsPredicate tagPredicate =
                new ContactContainsTagsPredicate(SampleDataUtil.getTagSet("first", "second"));

        FindContactCriteria firstCriteria = new FindContactCriteriaBuilder()
                .withNamePredicate(namePredicate).build();
        FindContactCriteria secondCriteria = new FindContactCriteriaBuilder()
                .withTagPredicate(tagPredicate).build();

        FindContactCommand findFirstCommand = new FindContactCommand(firstCriteria);
        FindContactCommand findSecondCommand = new FindContactCommand(secondCriteria);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same predicate value -> returns true
        FindContactCommand findFirstCommandCopy = new FindContactCommand(firstCriteria);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(10));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different predicate value -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    /**
     * Parses {@code userInput} into a {@code ContactNameContainsKeywordsPredicate}.
     */
    private ContactNameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new ContactNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code ContactContainsTagsPredicate}.
     */
    private ContactContainsTagsPredicate prepareTagPredicate(String userInput) {
        Set<Tag> tags = SampleDataUtil.getTagSet(userInput.split("\\s+"));
        return new ContactContainsTagsPredicate(tags);
    }

}

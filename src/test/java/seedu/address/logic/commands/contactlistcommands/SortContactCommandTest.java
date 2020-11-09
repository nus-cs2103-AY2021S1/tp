package seedu.address.logic.commands.contactlistcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.contact.TypicalContacts.ALICE;
import static seedu.address.testutil.contact.TypicalContacts.BENSON;
import static seedu.address.testutil.contact.TypicalContacts.CARL;
import static seedu.address.testutil.contact.TypicalContacts.DANIEL;
import static seedu.address.testutil.contact.TypicalContacts.ELLE;
import static seedu.address.testutil.contact.TypicalContacts.FIONA;
import static seedu.address.testutil.contact.TypicalContacts.GEORGE;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalContactList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactComparatorByName;
import seedu.address.model.contact.ContactNameContainsKeywordsPredicate;

public class SortContactCommandTest {
    private ModelManager model = new ModelManager(
        new ModuleList(),
        new ModuleList(),
        new ContactList(),
        new TodoList(),
        new EventList(),
        new UserPrefs());

    private ModelManager expectedModel = new ModelManager(
        new ModuleList(),
        new ModuleList(),
        new ContactList(),
        new TodoList(),
        new EventList(),
        new UserPrefs());

    @Test
    public void execute_sortEmptyList_success() {
        String expectedMessage = SortContactCommand.MESSAGE_SUCCESS;
        Comparator<Contact> comparator = new ContactComparatorByName();
        SortContactCommand command = new SortContactCommand(new ContactComparatorByName());

        expectedModel.updateSortedContactList(comparator);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        List<Contact> sortedList = new ArrayList<>();

        assertEquals(sortedList, model.getSortedContactList());
    }

    @Test
    public void execute_byNameUnfilteredList_success() {
        model.setContactList(getTypicalContactList());

        expectedModel.setContactList(getTypicalContactList());

        String expectedMessage = SortContactCommand.MESSAGE_SUCCESS;
        Comparator<Contact> comparator = new ContactComparatorByName();
        SortContactCommand command = new SortContactCommand(comparator);
        expectedModel.updateSortedContactList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        List<Contact> sortedList = new ArrayList<>();
        sortedList.add(ALICE);
        sortedList.add(BENSON);
        sortedList.add(CARL);
        sortedList.add(DANIEL);
        sortedList.add(ELLE);
        sortedList.add(FIONA);
        sortedList.add(GEORGE);

        assertEquals(sortedList, model.getSortedContactList());
    }

    @Test
    public void execute_byNameReversedUnfilteredList_success() {
        model.setContactList(getTypicalContactList());

        expectedModel.setContactList(getTypicalContactList());

        String expectedMessage = SortContactCommand.MESSAGE_SUCCESS;
        Comparator<Contact> comparator = new ContactComparatorByName().reversed();
        SortContactCommand command = new SortContactCommand(comparator);
        expectedModel.updateSortedContactList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        List<Contact> sortedList = new ArrayList<>();
        sortedList.add(GEORGE);
        sortedList.add(FIONA);
        sortedList.add(ELLE);
        sortedList.add(DANIEL);
        sortedList.add(CARL);
        sortedList.add(BENSON);
        sortedList.add(ALICE);

        assertEquals(sortedList, model.getSortedContactList());
    }

    // Test order of filtering and sorting

    @Test
    public void execute_byNameFilteredListFilterThenSort_success() {

        // filter then sort

        final ContactNameContainsKeywordsPredicate predicate = new ContactNameContainsKeywordsPredicate(Arrays.asList(
                "Alice", "Carl", "Elle", "George"));

        model.setContactList(getTypicalContactList());

        expectedModel.setContactList(getTypicalContactList());

        String expectedMessage = SortContactCommand.MESSAGE_SUCCESS;
        Comparator<Contact> comparator = new ContactComparatorByName();
        SortContactCommand command = new SortContactCommand(comparator);

        model.updateFilteredContactList(predicate);

        expectedModel.updateFilteredContactList(predicate);
        expectedModel.updateSortedContactList(comparator);

        // this statement modified the original model, no defensive copy was made
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        List<Contact> sortedList = new ArrayList<>();
        sortedList.add(ALICE);
        sortedList.add(CARL);
        sortedList.add(ELLE);
        sortedList.add(GEORGE);

        assertEquals(sortedList, model.getFilteredContactList());
    }

    @Test
    public void execute_byNameFilteredListSortThenFilter_success() {

        // sort then filter

        final ContactNameContainsKeywordsPredicate predicate = new ContactNameContainsKeywordsPredicate(Arrays.asList(
            "Alice", "Carl", "Elle", "George"));

        model.setContactList(getTypicalContactList());

        expectedModel.setContactList(getTypicalContactList());

        String expectedMessage = SortContactCommand.MESSAGE_SUCCESS;
        Comparator<Contact> comparator = new ContactComparatorByName();
        SortContactCommand command = new SortContactCommand(comparator);

        expectedModel.updateFilteredContactList(predicate);
        expectedModel.updateSortedContactList(comparator);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        List<Contact> sortedList = new ArrayList<>();
        sortedList.add(ALICE);
        sortedList.add(CARL);
        sortedList.add(ELLE);
        sortedList.add(GEORGE);

        model.updateFilteredContactList(predicate);

        assertEquals(sortedList, model.getFilteredContactList());
    }

    @Test
    public void execute_byNameReversedFilteredListFilterThenSort_success() {

        // filter then sort

        final ContactNameContainsKeywordsPredicate predicate = new ContactNameContainsKeywordsPredicate(Arrays.asList(
            "Alice", "Carl", "Elle", "George"));

        model.setContactList(getTypicalContactList());

        expectedModel.setContactList(getTypicalContactList());

        String expectedMessage = SortContactCommand.MESSAGE_SUCCESS;
        Comparator<Contact> comparator = new ContactComparatorByName().reversed();
        SortContactCommand command = new SortContactCommand(comparator);

        model.updateFilteredContactList(predicate);

        expectedModel.updateFilteredContactList(predicate);
        expectedModel.updateSortedContactList(comparator);

        // this statement modified the original model, no defensive copy was made
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        List<Contact> sortedList = new ArrayList<>();
        sortedList.add(GEORGE);
        sortedList.add(ELLE);
        sortedList.add(CARL);
        sortedList.add(ALICE);

        assertEquals(sortedList, model.getFilteredContactList());
    }

    @Test
    public void execute_byNameReversedFilteredListSortThenFilter_success() {

        // sort then filter

        final ContactNameContainsKeywordsPredicate predicate = new ContactNameContainsKeywordsPredicate(Arrays.asList(
            "Alice", "Carl", "Elle", "George"));

        model.setContactList(getTypicalContactList());

        expectedModel.setContactList(getTypicalContactList());

        String expectedMessage = SortContactCommand.MESSAGE_SUCCESS;
        Comparator<Contact> comparator = new ContactComparatorByName().reversed();
        SortContactCommand command = new SortContactCommand(comparator);

        expectedModel.updateFilteredContactList(predicate);
        expectedModel.updateSortedContactList(comparator);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        List<Contact> sortedList = new ArrayList<>();
        sortedList.add(GEORGE);
        sortedList.add(ELLE);
        sortedList.add(CARL);
        sortedList.add(ALICE);

        model.updateFilteredContactList(predicate);

        assertEquals(sortedList, model.getFilteredContactList());
    }

    @Test
    public void equals() {
        Comparator<Contact> firstComparator = new ContactComparatorByName();
        Comparator<Contact> secondComparator = new ContactComparatorByName();

        SortContactCommand sortFirstCommand = new SortContactCommand(firstComparator);
        SortContactCommand sortSecondCommand = new SortContactCommand(secondComparator);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortContactCommand sortFirstCommandCopy = new SortContactCommand(firstComparator);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different command -> returns false
        // comparator must be identical (not equal)
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }
}

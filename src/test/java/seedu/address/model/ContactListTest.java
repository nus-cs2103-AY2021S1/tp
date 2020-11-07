package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.contact.TypicalContacts.ALICE;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalContactList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.exceptions.DuplicateContactException;
import seedu.address.testutil.contact.ContactBuilder;

public class ContactListTest {

    private final ContactList contactList = new ContactList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), contactList.getContactList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyContactList_replacesData() {
        ContactList newData = getTypicalContactList();
        contactList.resetData(newData);
        assertEquals(newData, contactList);
    }

    @Test
    public void resetData_withDuplicateContacts_throwsDuplicateContactException() {
        // Two contacts with the same identity fields
        Contact editedAlice = new ContactBuilder(ALICE).withTelegram(VALID_TELEGRAM_AMY)
                .withTags(VALID_TAG_HUSBAND).build();
        List<Contact> newContacts = Arrays.asList(ALICE, editedAlice);
        ContactListStub newData = new ContactListStub(newContacts);

        assertThrows(DuplicateContactException.class, () -> contactList.resetData(newData));
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactList.hasContact(null));
    }

    @Test
    public void hasContact_contactNotInContactList_returnsFalse() {
        assertFalse(contactList.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactInContactList_returnsTrue() {
        contactList.addContact(ALICE);
        assertTrue(contactList.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactWithSameIdentityFieldsInContactList_returnsTrue() {
        contactList.addContact(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE).withTelegram(VALID_TELEGRAM_AMY)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(contactList.hasContact(editedAlice));
    }

    @Test
    public void addContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactList.addContact(null));
    }

    @Test
    public void removeContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactList.removeContact(null));
    }

    @Test
    public void getContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> contactList.getContactList().remove(0));
    }

    /**
     * A stub ReadOnlyContactList whose contacts list can violate interface constraints.
     */
    private static class ContactListStub implements ReadOnlyContactList {
        private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

        ContactListStub(Collection<Contact> contacts) {
            this.contacts.setAll(contacts);
        }

        @Override
        public ObservableList<Contact> getContactList() {
            return contacts;
        }
    }
}

package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.AMY;
import static seedu.address.testutil.TypicalContacts.BOB;
import static seedu.address.testutil.TypicalContacts.getTypicalContactList;

import org.junit.jupiter.api.Test;

import seedu.address.model.exceptions.VersionedListException;
import seedu.address.testutil.ContactListBuilder;

public class VersionedContactListTest {
    private static final ContactList VALID_CONTACT_LIST = new ContactListBuilder().withContact(AMY)
            .withContact(BOB).build();
    private final VersionedContactList versionedContactList = new VersionedContactList(getTypicalContactList());


    @Test
    public void constructor() {
        assertEquals(getTypicalContactList(), versionedContactList);
    }
    @Test
    public void execute_commit_success() {
        //Initiate empty VersionedContactList
        VersionedContactList initialVersionedContactList = new VersionedContactList();
        initialVersionedContactList.commit(VALID_CONTACT_LIST);
        assertEquals(initialVersionedContactList, VALID_CONTACT_LIST);
    }

    @Test
    public void undoData_withNoHistory_throwsVersionedListException() {
        assertThrows(VersionedListException.class, () -> versionedContactList.undo());
    }

    @Test
    public void execute_undoWithHistory_success() {
        //Initiate empty VersionedContactList
        VersionedContactList initialVersionedContactList = new VersionedContactList();
        initialVersionedContactList.commit(VALID_CONTACT_LIST);
        //Make sure that commit was successful
        assertEquals(initialVersionedContactList, VALID_CONTACT_LIST);
        try {
            initialVersionedContactList.undo();
        } catch (VersionedListException ve) {
            throw new AssertionError("Execution of method should not fail.", ve);
        }
        assertEquals(initialVersionedContactList, new ContactList());
    }

    @Test
    public void redoData_withNoFuture_throwsVersionedListException() {
        assertThrows(VersionedListException.class, () -> versionedContactList.redo());
    }

    @Test
    public void execute_redoWithFuture_success() {
        //Initiate empty VersionedContactList
        VersionedContactList initialVersionedContactList = new VersionedContactList();
        initialVersionedContactList.commit(VALID_CONTACT_LIST);
        //Make sure that commit was successful
        assertEquals(initialVersionedContactList, VALID_CONTACT_LIST);
        try {
            initialVersionedContactList.undo();
            //make sure that undo was successful
            assertEquals(initialVersionedContactList, new ContactList());
            initialVersionedContactList.redo();
        } catch (VersionedListException ve) {
            throw new AssertionError("Execution of method should not fail.", ve);
        }
        assertEquals(initialVersionedContactList, VALID_CONTACT_LIST);
    }
}

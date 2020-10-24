package trackitnus.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackitnus.testutil.typical.TypicalContacts.ALICE;
import static trackitnus.testutil.typical.TypicalContacts.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import trackitnus.logic.commands.contact.ContactCommandTestUtil;
import trackitnus.model.contact.exceptions.ContactNotFoundException;
import trackitnus.model.contact.exceptions.DuplicateContactException;
import trackitnus.testutil.Assert;
import trackitnus.testutil.builder.ContactBuilder;

public class UniqueContactListTest {

    private final UniqueContactList uniqueContactList = new UniqueContactList();

    @Test
    public void contains_nullContact_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueContactList.contains(null));
    }

    @Test
    public void contains_contactNotInList_returnsFalse() {
        assertFalse(uniqueContactList.contains(ALICE));
    }

    @Test
    public void contains_contactInList_returnsTrue() {
        uniqueContactList.add(ALICE);
        assertTrue(uniqueContactList.contains(ALICE));
    }

    @Test
    public void contains_contactWithSameIdentityFieldsInList_returnsTrue() {
        uniqueContactList.add(ALICE);
        Contact editedAlice =
            new ContactBuilder(ALICE)
                .withTags(ContactCommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueContactList.contains(editedAlice));
    }

    @Test
    public void add_nullContact_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueContactList.add(null));
    }

    @Test
    public void add_duplicateContact_throwsDuplicateContactException() {
        uniqueContactList.add(ALICE);
        Assert.assertThrows(DuplicateContactException.class, () -> uniqueContactList.add(ALICE));
    }

    @Test
    public void setContact_nullTargetContact_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueContactList.setContact(null, ALICE));
    }

    @Test
    public void setContact_nullEditedContact_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueContactList.setContact(ALICE, null));
    }

    @Test
    public void setContact_targetContactNotInList_throwsContactNotFoundException() {
        Assert.assertThrows(ContactNotFoundException.class, () -> uniqueContactList.setContact(ALICE, ALICE));
    }

    @Test
    public void setContact_editedContactIsSameContact_success() {
        uniqueContactList.add(ALICE);
        uniqueContactList.setContact(ALICE, ALICE);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(ALICE);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContact_editedContactHasSameIdentity_success() {
        uniqueContactList.add(ALICE);
        Contact editedAlice =
            new ContactBuilder(ALICE).withTags(ContactCommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        uniqueContactList.setContact(ALICE, editedAlice);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(editedAlice);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContact_editedContactHasDifferentIdentity_success() {
        uniqueContactList.add(ALICE);
        uniqueContactList.setContact(ALICE, BOB);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(BOB);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContact_editedContactHasNonUniqueIdentity_throwsDuplicateContactException() {
        uniqueContactList.add(ALICE);
        uniqueContactList.add(BOB);
        Assert.assertThrows(DuplicateContactException.class, () -> uniqueContactList.setContact(ALICE, BOB));
    }

    @Test
    public void remove_nullContact_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueContactList.remove(null));
    }

    @Test
    public void remove_contactDoesNotExist_throwsContactNotFoundException() {
        Assert.assertThrows(ContactNotFoundException.class, () -> uniqueContactList.remove(ALICE));
    }

    @Test
    public void remove_existingContact_removesContact() {
        uniqueContactList.add(ALICE);
        uniqueContactList.remove(ALICE);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContacts_nullUniqueContactList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueContactList.setContacts((UniqueContactList) null));
    }

    @Test
    public void setContacts_uniqueContactList_replacesOwnListWithProvidedUniqueContactList() {
        uniqueContactList.add(ALICE);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(BOB);
        uniqueContactList.setContacts(expectedUniqueContactList);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContacts_nullList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueContactList.setContacts((List<Contact>) null));
    }

    @Test
    public void setContacts_list_replacesOwnListWithProvidedList() {
        uniqueContactList.add(ALICE);
        List<Contact> contactList = Collections.singletonList(BOB);
        uniqueContactList.setContacts(contactList);
        UniqueContactList expectedUniqueContactList = new UniqueContactList();
        expectedUniqueContactList.add(BOB);
        assertEquals(expectedUniqueContactList, uniqueContactList);
    }

    @Test
    public void setContacts_listWithDuplicateContacts_throwsDuplicateContactException() {
        List<Contact> listWithDuplicateContacts = Arrays.asList(ALICE, ALICE);
        Assert.assertThrows(DuplicateContactException.class, () -> uniqueContactList
            .setContacts(listWithDuplicateContacts));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
            -> uniqueContactList.asUnmodifiableObservableList().remove(0));
    }
}

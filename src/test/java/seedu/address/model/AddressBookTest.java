package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.ALICE;
import static seedu.address.testutil.TypicalTags.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.testutil.TagBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getTagList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateTags_throwsDuplicateTagException() {
        // Two persons with the same identity fields
        Tag editedAlice = new TagBuilder(ALICE).build();
        List<Tag> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicateTagException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTag(null));
    }

    @Test
    public void hasTag_tagNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTag(ALICE));
    }

    @Test
    public void hasTag_tagInAddressBook_returnsTrue() {
        addressBook.addTag(ALICE);
        assertTrue(addressBook.hasTag(ALICE));
    }

    @Test
    public void hasTag_tagWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addTag(ALICE);
        Tag editedAlice = new TagBuilder(ALICE).build();
        assertTrue(addressBook.hasTag(editedAlice));
    }

    @Test
    public void getTagList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTagList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Tag> tags = FXCollections.observableArrayList();

        AddressBookStub(Collection<Tag> tags) {
            this.tags.setAll(tags);
        }

        @Override
        public ObservableList<Tag> getTagList() {
            return tags;
        }
    }

}

package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.CS2101;
import static seedu.address.testutil.TypicalTags.CS2103;
import static seedu.address.testutil.TypicalTags.getTypicalAddressBook;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.explorer.FileList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.testutil.TagBuilder;

public class AddressBookTest {

    private AddressBook addressBook = new AddressBook();

    @BeforeEach
    public void init() {
        addressBook = new AddressBook();
    }


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
        Tag editedCS2103 = new TagBuilder(CS2103).build();
        List<Tag> newPersons = Arrays.asList(CS2103, editedCS2103);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicateTagException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTag(null));
    }

    @Test
    public void hasTag_tagNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTag(CS2103));
    }

    @Test
    public void hasTag_tagInAddressBook_returnsTrue() {
        addressBook.addTag(CS2103);
        assertTrue(addressBook.hasTag(CS2103));
    }

    @Test
    public void hasTag_tagWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addTag(CS2103);
        Tag editedCS2103 = new TagBuilder(CS2103).build();
        assertTrue(addressBook.hasTag(editedCS2103));
    }


    @Test
    public void setTag_tagInAddressBook_success() {
        AddressBook dummyAddressBook = addressBook;
        dummyAddressBook.addTag(CS2103);
        dummyAddressBook.setTag(CS2103, CS2101);
        Tag expectedTag = new TagBuilder(CS2101).build();
        assertTrue(dummyAddressBook.hasTag(expectedTag));
    }

    @Test
    public void setTag_nullTag_throwsNullPointerException() {
        AddressBook dummyAddressBook = addressBook;
        dummyAddressBook.addTag(CS2103);
        assertThrows(NullPointerException.class, () -> dummyAddressBook.setTag(CS2103, null));
    }

    @Test
    public void removeTag_tagInAddressBook_success() {
        AddressBook dummyAddressBook = addressBook;
        dummyAddressBook.addTag(CS2103);
        dummyAddressBook.removeTag(CS2103);
        Tag expectedTag = new TagBuilder(CS2101).build();
        assertFalse(dummyAddressBook.hasTag(expectedTag));
    }

    @Test
    public void removeTag_nullTag_throwsNullPointerException() {
        AddressBook dummyAddressBook = addressBook;
        assertThrows(NullPointerException.class, () -> dummyAddressBook.removeTag(null));
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

        @Override
        public FileList getFileList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<File> getObservableFileList() {
            throw new AssertionError("This method should not be called.");
        }
    }

}

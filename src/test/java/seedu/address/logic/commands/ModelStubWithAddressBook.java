package seedu.address.logic.commands;

import java.io.File;

import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.explorer.FileList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Model stub with AddressBook functionality
 */
class ModelStubWithAddressBook extends ModelStub {

    private final AddressBookStubWithTag ab = new AddressBookStubWithTag();

    @Override
    public void addTag(Tag tag) {
        ab.addTag(tag);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return ab;
    }

    @Override
    public boolean hasTag(Tag tag) {
        return ab.getTagList().contains(tag);
    }
}

class AddressBookStubWithTag implements ReadOnlyAddressBook {

    private final UniqueTagList list = new UniqueTagList();

    @Override
    public ObservableList<Tag> getTagList() {
        return list.asUnmodifiableObservableList();
    }

    @Override
    public FileList getFileList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<File> getObservableFileList() {
        throw new AssertionError("This method should not be called.");
    }

    public void addTag(Tag tag) {
        list.add(tag);
    }
}

package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyAddressBook;
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

    public void addTag(Tag tag) {
        list.add(tag);
    }
}

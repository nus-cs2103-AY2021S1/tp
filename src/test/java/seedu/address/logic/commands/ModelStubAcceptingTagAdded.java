package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.explorer.CurrentPath;
import seedu.address.model.explorer.FileList;
import seedu.address.model.tag.Tag;

/**
 * A Model stub that always accept the tag being added.
 */
class ModelStubAcceptingTagAdded extends ModelStub {
    final ArrayList<Tag> tagsAdded = new ArrayList<>();

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return tagsAdded.stream().anyMatch(tag::isSameTag);
    }

    @Override
    public void addTag(Tag tag) {
        requireNonNull(tag);
        tagsAdded.add(tag);
    }

    @Override
    public CurrentPath getCurrentPath() {
        return new CurrentPath(System.getProperty("user.dir"), new FileList());
    }

    @Override
    public void commitAddressBook() {
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}

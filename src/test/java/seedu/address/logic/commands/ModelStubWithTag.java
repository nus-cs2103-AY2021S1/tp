package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.model.tag.Tag;

/**
 * A Model stub that contains a single tag.
 */
class ModelStubWithTag extends ModelStub {
    private Tag tag;

    ModelStubWithTag(Tag tag) {
        requireNonNull(tag);
        this.tag = tag;
    }

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return this.tag.isSameTag(tag);
    }

    @Override
    public List<Tag> findFilteredTagList(Predicate<Tag> predicate) {
        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(tag);
        return tagList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTag(Tag target) {
        this.tag = null;
    }

    @Override
    public void addTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public void commitAddressBook() {
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModelStubWithTag // instanceof handles nulls
                && this.tag.equals(((ModelStubWithTag) other).tag));
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.tag.Tag;

/**
 * A Model stub that contains a single tag.
 */
class ModelStubWithTag extends ModelStub {
    private final Tag tag;

    ModelStubWithTag(Tag tag) {
        requireNonNull(tag);
        this.tag = tag;
    }

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return this.tag.isSameTag(tag);
    }
}

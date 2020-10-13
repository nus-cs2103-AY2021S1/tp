package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

public class ModelStubWithTagAndTaglist extends ModelStub {
    private Tag tag;

    ModelStubWithTagAndTaglist(Tag tag) {
        requireNonNull(tag);
        this.tag = tag;
    }

    public Tag getTag() {
        return this.tag;
    }

    @Override
    public void addTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public void deleteTag(Tag tag) {

    }

    @Override
    public List<Tag> findFilteredTagList(Predicate<Tag> predicate) {
        if (predicate.test(tag)) {
            return List.of(tag);
        }

        return List.of();
    }

    @Override
    public boolean equals(Object other) {
        boolean a = other instanceof ModelStubWithTagAndTaglist;
        return other == this // short circuit if same object
                || (other instanceof ModelStubWithTagAndTaglist // instanceof handles nulls
                && ((ModelStubWithTagAndTaglist) other).tag.equals(this.tag));
    }
}

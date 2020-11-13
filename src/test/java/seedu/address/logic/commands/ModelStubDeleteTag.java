package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.model.tag.Tag;

public class ModelStubDeleteTag extends ModelStub {
    private List<Tag> tagList;
    private int commitCount = 0;

    ModelStubDeleteTag(List<Tag> tagList) {
        requireNonNull(tagList);
        this.tagList = tagList;
    }

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return this.tagList.contains(tag);
    }

    @Override
    public void deleteTag(Tag tag) {
        requireNonNull(tag);
        this.tagList.remove(tag);
    }

    @Override
    public List<Tag> findFilteredTagList(Predicate<Tag> predicate) {
        return tagList.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public void commitAddressBook() {
        commitCount++;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ModelStubDeleteTag)) {
            return false;
        }

        ModelStubDeleteTag o = (ModelStubDeleteTag) other;
        return this.tagList.equals(o.tagList) && this.commitCount == o.commitCount;
    }
}

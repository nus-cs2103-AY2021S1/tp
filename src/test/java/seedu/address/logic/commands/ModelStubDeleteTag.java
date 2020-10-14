package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

public class ModelStubDeleteTag extends ModelStub {
    private List<Tag> tagList;

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
        for (Tag tag : tagList) {
            if (predicate.test(tag)) {
                return List.of(tag);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;

        }

        if (other instanceof ModelStubDeleteTag) {
            if (tagList.isEmpty() && ((ModelStubDeleteTag) other).tagList.isEmpty()) {
                return true;
            } else if (this.tagList.equals(((ModelStubDeleteTag) other).tagList)) {
                return true;
            }
        }

        return false;
    }
}

package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.model.explorer.CurrentPath;
import seedu.address.model.explorer.FileList;
import seedu.address.model.tag.Tag;

public class ModelStubWithTagAndTaglist extends ModelStub {
    private final List<Tag> tags;
    private final CurrentPath currentPath;
    private int commitCount;

    /**
     * Creates a ModelStubWithTagAndTagList object.
     */
    public ModelStubWithTagAndTaglist() {
        tags = new ArrayList<>();
        currentPath = new CurrentPath(System.getProperty("user.dir"), new FileList());
        commitCount = 0;
    }

    @Override
    public void addTag(Tag tag) {
        tags.add(tag);
    }

    @Override
    public void deleteTag(Tag tag) {
        tags.remove(tag);
    }

    @Override
    public boolean hasTag(Tag tag) {
        return tags.contains(tag);
    }

    @Override
    public CurrentPath getCurrentPath() {
        return this.currentPath;
    }

    @Override
    public List<Tag> findFilteredTagList(Predicate<Tag> predicate) {
        return tags.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public void commitAddressBook() {
        commitCount += 1;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModelStubWithTagAndTaglist // instanceof handles nulls
                && ((ModelStubWithTagAndTaglist) other).tags.equals(this.tags)
                && ((ModelStubWithTagAndTaglist) other).commitCount == this.commitCount);
    }

}

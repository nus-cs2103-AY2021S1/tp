package seedu.expense.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.expense.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.expense.model.ExpenseBook.DEFAULT_TAG;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.expense.model.tag.exceptions.DefaultTagException;
import seedu.expense.model.tag.exceptions.DuplicateTagException;
import seedu.expense.model.tag.exceptions.TagNotFoundException;

/**
 * A list of tags that enforces uniqueness between its elements and does not allow nulls.
 * A tag is considered unique by comparing using {@code Tag#equals(Tag)}.
 * As such, adding and updating of tags uses Tag#equals(Tag) for equality so as
 * to ensure that the tag being added or updated is unique in terms of identity in the UniqueTagList.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Tag#equals(Object)
 */
public class UniqueTagList implements Iterable<Tag> {
    private final ObservableList<Tag> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tag> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tag as the given argument.
     */
    public boolean contains(Tag toCheck) {
        requireNonNull(toCheck);
        return toCheck.equals(DEFAULT_TAG) || internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a tag to the list.
     * The tag must not already exist in the list.
     */
    public void add(Tag toAdd) {
        requireNonNull(toAdd);

        if (toAdd.equals(DEFAULT_TAG)) {
            throw new DefaultTagException();
        }

        if (contains(toAdd)) {
            throw new DuplicateTagException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the tag {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in the list.
     * The tag identity of {@code editedTag} must not be the same as another existing tag in the list.
     */
    public void setTag(Tag target, Tag editedTag) {
        requireAllNonNull(target, editedTag);

        if (target.equals(DEFAULT_TAG)) {
            throw new DefaultTagException();
        }

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TagNotFoundException();
        }

        if (editedTag.equals(DEFAULT_TAG)) {
            throw new DefaultTagException();
        }

        if (!target.equals(editedTag) && contains(editedTag)) {
            throw new DuplicateTagException();
        }

        internalList.set(index, editedTag);
    }

    /**
     * Removes the equivalent tag from the list.
     * The tag must exist in the list.
     */
    public void remove(Tag toRemove) {
        requireNonNull(toRemove);

        if (toRemove.equals(DEFAULT_TAG)) {
            throw new DefaultTagException();
        }

        if (!internalList.remove(toRemove)) {
            throw new TagNotFoundException();
        }
    }

    public void setTags(UniqueTagList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     */
    public void setTags(List<Tag> tags) {
        requireAllNonNull(tags);

        if (tags.contains(DEFAULT_TAG)) {
            throw new DefaultTagException();
        }

        if (!tagsAreUnique(tags)) {
            throw new DuplicateTagException();
        }

        internalList.setAll(tags);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Tag> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tag> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueTagList // instanceof handles nulls
            && internalList.equals(((UniqueTagList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tags} contains only unique tags.
     */
    private boolean tagsAreUnique(List<Tag> tags) {
        for (int i = 0; i < tags.size() - 1; i++) {
            for (int j = i + 1; j < tags.size(); j++) {
                if (tags.get(i).equals(tags.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

package com.eva.model.comment.view;

import static com.eva.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.eva.model.comment.Comment;
import com.eva.model.comment.exceptions.CommentNotFoundException;
import com.eva.model.comment.exceptions.DuplicateCommentException;
import com.eva.model.person.Person;
import com.eva.model.person.exceptions.DuplicatePersonException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniqueCommentsList implements Iterable<Comment> {

    private final ObservableList<Comment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Comment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Comment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameComment);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Comment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCommentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setComment(Comment target, Comment editedComment) {
        requireAllNonNull(target, editedComment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new CommentNotFoundException();
        }

        if (!target.isSameComment(editedComment) && contains(editedComment)) {
            throw new DuplicateCommentException();
        }

        internalList.set(index, editedComment);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Comment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new CommentNotFoundException();
        }
    }

    public void setComments(UniqueCommentsList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setComments(List<Comment> comments) {
        requireAllNonNull(comments);
        if (!commentsAreUnique(comments)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(comments);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Comment> asUnmodifiableObservableList() {
        internalList.sort(new Comparator<Comment>() {
            @Override
            public int compare(Comment c1, Comment c2) {
                if (c1.getDate().isBefore(c2.getDate())) {
                    return -1;
                } else if (c1.getDate().isAfter(c2.getDate())) {
                    return 1;
                } else {
                    return c1.getTitle().getTitleDescription()
                            .compareTo(c2.getTitle().getTitleDescription());
                }
            }
        });
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Comment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueCommentsList // instanceof handles nulls
                && internalList.equals(((UniqueCommentsList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean commentsAreUnique(List<Comment> comments) {
        for (int i = 0; i < comments.size() - 1; i++) {
            for (int j = i + 1; j < comments.size(); j++) {
                if (comments.get(i).isSameComment(comments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Fills the contents of this list with {@code leaveSet}.
     */
    public void fill(Set<Comment> commentSet) {
        List<Comment> comments = new ArrayList<>(commentSet);
        for (Comment comment : comments) {
            add(comment);
        }
    }
}

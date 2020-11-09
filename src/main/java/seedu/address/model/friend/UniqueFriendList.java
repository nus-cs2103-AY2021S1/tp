package seedu.address.model.friend;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.commons.TravelPlanObject;
import seedu.address.model.friend.exceptions.DuplicateFriendException;
import seedu.address.model.friend.exceptions.FriendNotFoundException;

/**
 * A list of friends that enforces uniqueness between its elements and does not allow nulls.
 * A friend is considered unique by comparing using {@code Friend#isSameFriend(Friend)}. As such, adding and updating of
 * friends uses Friend#isSameFriend(Friend) for equality so as to ensure that the friend being added or updated is
 * unique in terms of identity in the UniqueFriendList. However, the removal of a person uses Friend#equals(Object) so
 * as to ensure that the friend with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Friend#isSameFriend(Friend)
 */
public class UniqueFriendList implements Iterable<Friend> {

    private final ObservableList<Friend> internalList = FXCollections.observableArrayList();
    private final ObservableList<Friend> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent friend as the given argument.
     */
    public boolean contains(Friend toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameFriend);
    }

    /**
     * Adds a friend to the list.
     * The friend must not already exist in the list.
     */
    public void add(Friend toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateFriendException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the friend {@code target} in the list with {@code editedFriend}.
     * {@code target} must exist in the list.
     * The friend identity of {@code editedFriend} must not be the same as another existing friend in the list.
     */
    public void setFriend(Friend target, Friend editedFriend) {
        requireAllNonNull(target, editedFriend);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new FriendNotFoundException();
        }

        if (!target.isSameFriend(editedFriend) && contains(editedFriend)) {
            throw new DuplicateFriendException();
        }

        internalList.set(index, editedFriend);
    }

    /**
     * Removes the equivalent friend from the list.
     * The friend must exist in the list.
     */
    public void remove(Friend toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new FriendNotFoundException();
        }
    }

    /**
     * Replaces the content of this list with another UniqueFriendList {@code replacement}
     * @param replacement must not be null.
     */
    public void setFriends(UniqueFriendList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code friends}.
     * {@code friends} must not contain duplicate friends.
     */
    public void setFriends(List<Friend> friends) {
        requireAllNonNull(friends);
        if (!friendsAreUnique(friends)) {
            throw new DuplicateFriendException();
        }

        internalList.setAll(friends);
    }

    public void sort(Comparator<Friend> comparator) {
        FXCollections.sort(internalList, comparator);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Friend> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}, with each Friend object
     * typecast to TravelPlanObject (TPO).
     */
    public ObservableList<TravelPlanObject> asUnmodifiableObservableTpoList() {
        return internalList.stream().map(item -> (TravelPlanObject) item)
                .collect(Collectors.collectingAndThen(Collectors.toList(), l -> FXCollections.observableArrayList(l)));
    }

    @Override
    public Iterator<Friend> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueFriendList // instanceof handles nulls
                && internalList.equals(((UniqueFriendList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code friends} contains only unique friends.
     */
    private boolean friendsAreUnique(List<Friend> friends) {
        for (int i = 0; i < friends.size() - 1; i++) {
            for (int j = i + 1; j < friends.size(); j++) {
                if (friends.get(i).isSameFriend(friends.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public ObservableList<Friend> getInternalList() {
        return internalList;
    }
}

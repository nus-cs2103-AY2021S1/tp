package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Wraps all data at the Trackr level.
 * Duplicates are not allowed (by .isSame comparison).
 * @param <T> A class that implements {@code Showable}.
 */
public class Trackr<T extends Showable<T>> implements ReadOnlyTrackr<T> {

    private final UniqueList<T> uniqueList;

    public Trackr() {
        uniqueList = new UniqueList<>();
    }

    /**
     * Creates a Trackr using the data in the {@code toBeCopied}
     */
    public Trackr(ReadOnlyTrackr<T> toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the list with {@code data}.
     * {@code data} must not contain duplicate objects.
     */
    public void setData(List<T> data) {
        this.uniqueList.setList(data);
    }

    /**
     * Resets the existing data of this {@code Trackr} with {@code newData}.
     */
    public void resetData(ReadOnlyTrackr<T> newData) {
        requireNonNull(newData);
        setData(newData.getList());
    }

    /**
     * Returns true if an object with the same identity as {@code object} exists in Trackr.
     */
    public boolean hasObject(T object) {
        requireNonNull(object);
        return uniqueList.contains(object);
    }

    /**
     * Adds an object to Trackr.
     * The object must not already exist in Trackr.
     */
    public void addObject(T object) {
        requireNonNull(object);
        uniqueList.add(object);
    }

    /**
     * Replaces the given object {@code target} in the list with {@code editedObject}.
     * {@code target} must exist in Trackr.
     * The identity of {@code editedObject} must not be the same as another existing object.
     */
    public void setObject(T target, T editedObject) {
        requireAllNonNull(target, editedObject);
        uniqueList.set(target, editedObject);
    }

    /**
     * Removes {@code key} from this {@code Trackr}.
     * {@code key} must exist in Trackr.
     */
    public void removeObject(T key) {
        uniqueList.remove(key);
    }

    @Override
    public String toString() {
        return uniqueList.asUnmodifiableObservableList().size() + " data objects";
    }

    @Override
    public ObservableList<T> getList() {
        return uniqueList.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Trackr // instanceof handles nulls
                && uniqueList.equals(((Trackr) other).uniqueList));
    }

    @Override
    public int hashCode() {
        return uniqueList.hashCode();
    }

}

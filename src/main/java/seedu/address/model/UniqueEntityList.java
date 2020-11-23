package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.exception.DuplicateEntityException;
import seedu.address.model.exception.EntityNotFoundException;
import seedu.address.model.person.Person;

/**
 * A list of entities that enforces uniqueness between its elements and does not allow nulls.
 * A entity is considered unique by comparing using {@code Entity#isSame(Entity)}. As such, adding and updating of
 * entities uses  Entity#isSame(Entity)) for equality so as to ensure that the entity being added or updated is
 * unique in terms of identity in the UniqueEntityList. However, the removal of a entity uses Entity#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Entity#isSame(Entity)
 */
public abstract class UniqueEntityList<T extends Entity> implements Iterable<T> {

    protected final ObservableList<T> internalList = FXCollections.observableArrayList();
    protected final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent entity as the given argument.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck :: isSame);
    }

    /**
     * Returns true if the list contains an equivalent Person as the given argument.
     */
    public boolean containsPerson(Person person) {
        requireNonNull(person);
        return internalList.stream().anyMatch(person :: isSame);
    }

    public abstract DuplicateEntityException duplicateException();
    public abstract EntityNotFoundException notFoundException();
    /**
     * Adds a entity to the list.
     * The entity must not already exist in the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw duplicateException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the entity {@code entity} in the list with {@code editedEntity}.
     * {@code target} must exist in the list.
     * The identity of {@code editedEntity} must not be the same as another existing entity in the list.
     */
    public void setEntity(T target, T editedEntity) {
        requireAllNonNull(target, editedEntity);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw notFoundException();
        }

        if (!target.isSame(editedEntity) && contains(editedEntity)) {
            throw duplicateException();
        }

        internalList.set(index, editedEntity);
    }

    /**
     * Removes the equivalent entity from the list.
     * The entity must exist in the list.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw notFoundException();
        }
    }

    public void setEntities(UniqueEntityList<T> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code entities} must not contain duplicate entities.
     */
    public void setEntities(List<T> entities) {
        requireAllNonNull(entities);
        if (!entitiesAreUnique(entities)) {
            throw duplicateException();
        }

        internalList.setAll(entities);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEntityList // instanceof handles nulls
                && internalList.equals(((UniqueEntityList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code entities} contains only unique entities.
     */
    private boolean entitiesAreUnique(List<T> entities) {
        for (int i = 0; i < entities.size() - 1; i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                if (entities.get(i).isSame(entities.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

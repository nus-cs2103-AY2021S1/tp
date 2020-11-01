package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.id.PropertyId.DEFAULT_PROPERTY_ID;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.id.Id;
import seedu.address.model.id.PropertyId;
import seedu.address.model.id.SellerId;
import seedu.address.model.property.exceptions.DuplicatePropertyException;
import seedu.address.model.property.exceptions.PropertyNotFoundException;

/**
 * A list of properties that enforces uniqueness between its elements and does not allow nulls.
 * A property is considered unique by comparing using {@code Property#isSameProperty(Property)}.
 * As such, adding and updating of properties uses Property#isSameProperty(Property) for equality so as
 * to ensure that the property being added or updated is unique in terms of identity in the
 * UniquePropertyList. However, the removal of a property uses Property#equals(Object) so as to ensure
 * that the property with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Property#isSameProperty(Property)
 */
public class UniquePropertyList implements Iterable<Property> {

    private static final String PROPERTY_ID_PREFIX = "P";
    private final ObservableList<Property> internalList = FXCollections.observableArrayList();
    private final ObservableList<Property> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent property as the given argument.
     */
    public boolean contains(Property toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameProperty);
    }

    /**
     * Returns true if the list contains an equivalent property that does not have the excludedId.
     *
     * @param toCheck The property to be checked.
     * @param excludedId The property id that was exempt from being checked.
     * @return True if the list contains such a property.
     */
    public boolean containsExceptPropertyId(Property toCheck, PropertyId excludedId) {
        requireAllNonNull(toCheck, excludedId);
        return internalList.stream().filter(property ->
                !(property.getPropertyId().equals(excludedId)))
                .anyMatch(toCheck::isSameProperty);
    }

    /**
     * Adds a property to the list.
     * The property must not already exist in the list.
     *
     * @return The added property.
     */
    public Property add(Property toAdd) {
        requireNonNull(toAdd);
        Property added;
        if (contains(toAdd)) {
            throw new DuplicatePropertyException();
        }
        if (toAdd.getPropertyId().equals(DEFAULT_PROPERTY_ID)) {
            added = toAdd.setId(getLastId().increment());
        } else {
            added = toAdd;
        }
        internalList.add(added);
        return added;
    }

    /**
     * Replaces the property {@code target} in the list with {@code editedProperty}.
     * {@code target} must exist in the list.
     * The property identity of {@code editedProperty} must not be the same as another existing property in the list.
     */
    public void setProperty(Property target, Property editedProperty) {
        requireAllNonNull(target, editedProperty);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PropertyNotFoundException();
        }

        if (!target.isSameProperty(editedProperty) && contains(editedProperty)) {
            throw new DuplicatePropertyException();
        }

        internalList.set(index, editedProperty);
    }

    /**
     * Removes the equivalent property from the list.
     * The property must exist in the list.
     */
    public void remove(Property toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PropertyNotFoundException();
        }
    }

    /**
     * Removes the property with the specified id.
     *
     * @param id The id specified.
     */
    public void removeByPropertyId(Id id) {
        requireNonNull(id);
        remove(getPropertyById(id));
    }

    /**
     * Removes the property with the corresponding seller id.
     *
     * @param sellerId The seller id specified.
     */
    public void removeBySellerId(SellerId sellerId) {
        internalList.removeIf(property -> property.getSellerId().equals(sellerId));

    }

    /**
     * Gets the first property with the same id as {@code id}.
     *
     * @param id The specified id.
     * @return The first property with the same id.
     */
    public Property getPropertyById(Id id) {
        requireNonNull(id);
        FilteredList<Property> filteredProperties = internalList
                .filtered(property -> property.getPropertyId().equals(id));
        if (filteredProperties.size() == 0) {
            throw new PropertyNotFoundException();
        }
        assert filteredProperties.size() == 1;
        return filteredProperties.get(0);
    }

    /**
     * Checks if the property list contains a property with the given id.
     *
     * @param id The specified id.
     * @return True if the property list contains the property with the given id.
     */
    public boolean containsPropertyId(Id id) {
        requireNonNull(id);
        return internalList.filtered(property -> property.getPropertyId().equals(id))
                .size() > 0;
    }

    public void setProperties(UniquePropertyList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code properties}.
     * {@code properties} must not contain duplicate properties.
     */
    public void setProperties(List<Property> properties) {
        requireAllNonNull(properties);
        if (!propertiesAreUnique(properties)) {
            throw new DuplicatePropertyException();
        }

        internalList.setAll(properties);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Property> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Property> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePropertyList // instanceof handles nulls
                && internalList.equals(((UniquePropertyList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code properties} contains only unique properties.
     */
    private boolean propertiesAreUnique(List<Property> properties) {
        for (int i = 0; i < properties.size() - 1; i++) {
            for (int j = i + 1; j < properties.size(); j++) {
                if (properties.get(i).isSameProperty(properties.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private PropertyId getLastId() {
        if (internalList.size() == 0) {
            return new PropertyId(0);
        }
        return internalList.get(internalList.size() - 1).getPropertyId();
    }
}

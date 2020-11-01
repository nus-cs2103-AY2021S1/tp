package seedu.address.model.propertybook;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.id.PropertyId;
import seedu.address.model.id.SellerId;
import seedu.address.model.property.Property;
import seedu.address.model.property.UniquePropertyList;

/**
 * Wraps all data at the property-book level
 * Duplicates are not allowed (by .isSameProperty comparison)
 */
public class PropertyBook implements ReadOnlyPropertyBook {

    private final UniquePropertyList properties;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        properties = new UniquePropertyList();
    }

    public PropertyBook() {}

    /**
     * Creates a PropertyBook using the Properties in the {@code toBeCopied}
     */
    public PropertyBook(ReadOnlyPropertyBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the property list with {@code properties}.
     * {@code properties} must not contain duplicate properties.
     */
    public void setProperties(List<Property> properties) {
        this.properties.setProperties(properties);
    }

    /**
     * Resets the existing data of this {@code PropertyBook} with {@code newData}.
     */
    public void resetData(ReadOnlyPropertyBook newData) {
        requireNonNull(newData);
        setProperties(newData.getPropertyList());
    }

    //// property-level operations

    /**
     * Returns true if a property with the same identity as {@code property} exists in the property book.
     */
    public boolean hasProperty(Property property) {
        requireNonNull(property);
        return properties.contains(property);
    }

    /**
     * Returns true if a property with the same identity as {@code property} exists in the property book,
     * excluding the {@code excludedId}.
     *
     * @param property The property to check.
     * @param excludedId The excluded property id.
     * @return True if the property exists.
     */
    public boolean hasPropertyExceptPropertyId(Property property, PropertyId excludedId) {
        requireAllNonNull(property, excludedId);
        return properties.containsExceptPropertyId(property, excludedId);
    }

    /**
     * Adds a property to the property book.
     * The property must not already exist in the property book.
     *
     * @return The added property.
     */
    public Property addProperty(Property p) {
        return properties.add(p);
    }

    /**
     * Replaces the given property {@code target} in the list with {@code editedProperty}.
     * {@code target} must exist in the property book.
     * The property identity of {@code editedProperty} must not be the same as another existing
     * property in the property book.
     */
    public void setProperty(Property target, Property editedProperty) {
        requireNonNull(editedProperty);

        properties.setProperty(target, editedProperty);
    }

    /**
     * Removes {@code key} from this {@code PropertyBook}.
     * {@code key} must exist in the property book.
     */
    public void removeProperty(Property key) {
        properties.remove(key);
    }

    /**
     * Removes property with {@code id} from this {@code PropertyBook}. A
     * property with this {@code id} must exist in the property book.
     *
     * @param propertyId The propertyId of the property to be removed.
     */
    public void removePropertyByPropertyId(PropertyId propertyId) {
        properties.removeByPropertyId(propertyId);
    }

    /**
     * Removes property with {@code sellerId} from this {@code PropertyBook}. A property
     * with this {@code sellerId} must exist in the property book.
     *
     * @param sellerId the sellerId of which to remove the property.
     */
    public void removePropertyBySellerId(SellerId sellerId) {
        properties.removeBySellerId(sellerId);
    }
    /**
     * Gets property with {@code id} from this {@code PropertyBook}. A
     * property with this {@code id} must exist in the property book.
     *
     * @param propertyId The propertyId.
     * @return The property with the {@code id}.
     */
    public Property getPropertyById(PropertyId propertyId) {
        return properties.getPropertyById(propertyId);
    }

    /**
     * Checks if this {@code PropertyBook} contains a property with the given {@code id}.
     *
     * @param propertyId The given id.
     * @return True if a property with the given id exists in the list.
     */
    public boolean containsPropertyId(PropertyId propertyId) {
        return properties.containsPropertyId(propertyId);
    }

    //// util methods

    @Override
    public String toString() {
        return properties.asUnmodifiableObservableList().size() + " properties";
        // TODO: refine later
    }

    @Override
    public ObservableList<Property> getPropertyList() {
        return properties.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyBook // instanceof handles nulls
                && properties.equals(((PropertyBook) other).properties));
    }

    @Override
    public int hashCode() {
        return properties.hashCode();
    }
}

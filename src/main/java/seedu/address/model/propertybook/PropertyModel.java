package seedu.address.model.propertybook;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.id.PropertyId;
import seedu.address.model.property.Property;

public interface PropertyModel {

    /** {@code Predicate} that always evaluate to true */
    Predicate<Property> PREDICATE_SHOW_ALL_PROPERTIES = unused -> true;

    /**
     * Replaces property book data with the data in {@code propertyBook}.
     */
    void setPropertyBook(ReadOnlyPropertyBook propertyBook);

    /** Returns the property book. */
    ReadOnlyPropertyBook getPropertyBook();

    /**
     * Returns true if a property with the same identity as {@code property} exists in the property book.
     */
    boolean hasProperty(Property property);

    /**
     * Returns true if a property with the same identity as {@code property} exists in the property book,
     * except if has {@code excludedId}.
     */
    boolean hasPropertyExceptPropertyId(Property property, PropertyId excludedId);

    /**
     * Deletes the given property.
     * The property must exist in the property book.
     */
    void deleteProperty(Property target);

    /**
     * Deletes the property with the given id.
     * A property with the id must exist in the property book.
     * @param propertyId The given id.
     */
    void deletePropertyByPropertyId(PropertyId propertyId);

    /**
     * Adds the given property.
     * {@code property} must not already exist in the property book.
     * @return
     */
    Property addProperty(Property property);

    /**
     * Gets the property with the given id.
     * {@code id} must exist in the property book.
     *
     * @param propertyId The specified id.
     */
    Property getPropertyById(PropertyId propertyId);

    /**
     * Checks if the property book contains a property with the given id.
     *
     * @param propertyId The given id.
     * @return True if a property exists.
     */
    boolean containsPropertyId(PropertyId propertyId);

    /**
     * Replaces the given property {@code target} with {@code editedProperty}.
     * {@code target} must exist in the property book.
     * The property identity of {@code editedProperty} must not be the same as another existing property in the
     * property book.
     */
    void setProperty(Property target, Property editedProperty);

    /** Returns an unmodifiable view of the filtered property list */
    ObservableList<Property> getFilteredPropertyList();

    /**
     * Updates the filter of the filtered property list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPropertyList(Predicate<Property> predicate);

}

package seedu.address.testutil.property;

import seedu.address.model.property.Property;
import seedu.address.model.propertybook.PropertyBook;

/**
 * A utility class to help with building PropertyBook objects.
 * Example usage: <br>
 *     {@code PropertyBook pb = new PropertyBookBuilder().withPropertyName("John's House").build();}
 */
public class PropertyBookBuilder {

    private PropertyBook propertyBook;

    public PropertyBookBuilder() {
        propertyBook = new PropertyBook();
    }

    public PropertyBookBuilder(PropertyBook propertyBook) {
        this.propertyBook = propertyBook;
    }

    /**
     * Adds a new {@code Property} to the {@code PropertyBook} that we are building.
     */
    public PropertyBookBuilder withProperty(Property property) {
        propertyBook.addProperty(property);
        return this;
    }

    public PropertyBook build() {
        return propertyBook;
    }
}

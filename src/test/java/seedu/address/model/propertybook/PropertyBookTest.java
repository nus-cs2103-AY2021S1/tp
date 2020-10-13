package seedu.address.model.propertybook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_A;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_B;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.property.Property;
import seedu.address.model.property.exceptions.DuplicatePropertyException;
import seedu.address.model.property.exceptions.PropertyNotFoundException;
import seedu.address.testutil.property.PropertyBuilder;

public class PropertyBookTest {

    private final PropertyBook propertyBook = new PropertyBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), propertyBook.getPropertyList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> propertyBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPropertyBook_replacesData() {
        PropertyBook newData = getTypicalPropertyBook();
        propertyBook.resetData(newData);
        assertEquals(newData, propertyBook);
    }

    @Test
    public void resetData_withDuplicateProperties_throwsDuplicatePropertyException() {
        // Two properties with the same identity fields
        Property editedPropertyA = new PropertyBuilder(PROPERTY_B)
                .withPropertyId(PROPERTY_A.getPropertyId().toString())
                .build();
        List<Property> newProperties = Arrays.asList(PROPERTY_A, editedPropertyA);
        PropertyBookStub newData = new PropertyBookStub(newProperties);

        assertThrows(DuplicatePropertyException.class, () -> propertyBook.resetData(newData));
    }

    @Test
    public void hasProperty_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> propertyBook.hasProperty(null));
    }

    @Test
    public void hasProperty_propertyNotInPropertyBook_returnsFalse() {
        assertFalse(propertyBook.hasProperty(PROPERTY_A));
    }

    @Test
    public void hasProperty_propertyInPropertyBook_returnsTrue() {
        propertyBook.addProperty(PROPERTY_A);
        assertTrue(propertyBook.hasProperty(PROPERTY_A));
    }

    @Test
    public void hasProperty_propertyWithSameIdentityFieldsInPropertyBook_returnsTrue() {
        propertyBook.addProperty(PROPERTY_A);
        Property editedPropertyA = new PropertyBuilder(PROPERTY_B)
                .withPropertyId(PROPERTY_A.getPropertyId().toString())
                .build();
        assertTrue(propertyBook.hasProperty(editedPropertyA));
    }

    @Test
    public void getPropertyById_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> propertyBook.getPropertyById(null));
    }

    @Test
    public void getPropertyById_propertyInPropertyBook_getsProperty() {
        propertyBook.addProperty(PROPERTY_A);
        assertEquals(PROPERTY_A, propertyBook.getPropertyById(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void getPropertyById_propertyNotInPropertyBook_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class,
                () -> propertyBook.getPropertyById(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void containsPropertyId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> propertyBook.containsPropertyId(null));
    }

    @Test
    public void containsPropertyId_propertyInPropertyBook_returnsTrue() {
        propertyBook.addProperty(PROPERTY_A);
        assertTrue(propertyBook.containsPropertyId(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void containsPropertyId_propertyNotInPropertyBook_returnsFalse() {
        assertFalse(propertyBook.containsPropertyId(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void removePropertyByPropertyId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> propertyBook.removePropertyByPropertyId(null));
    }

    @Test
    public void removePropertyByPropertyId_propertyInPropertyBook_removesProperty() {
        propertyBook.addProperty(PROPERTY_A);
        propertyBook.removePropertyByPropertyId(PROPERTY_A.getPropertyId());
        PropertyBook expectedPropertyBook = new PropertyBook();
        assertEquals(propertyBook, expectedPropertyBook);
    }

    @Test
    public void removePropertyByPropertyId_propertyNotInPropertyBook_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class,
                () -> propertyBook.removePropertyByPropertyId(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void getPropertyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> propertyBook.getPropertyList().remove(0));
    }

    /**
     * A stub ReadOnlyPropertyBook whose properties list can violate interface constraints.
     */
    private static class PropertyBookStub implements ReadOnlyPropertyBook {
        private final ObservableList<Property> properties = FXCollections.observableArrayList();

        PropertyBookStub(Collection<Property> properties) {
            this.properties.setAll(properties);
        }

        @Override
        public ObservableList<Property> getPropertyList() {
            return properties;
        }
    }
}

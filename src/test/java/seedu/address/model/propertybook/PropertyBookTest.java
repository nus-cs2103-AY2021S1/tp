package seedu.address.model.propertybook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_A;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_B;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.id.PropertyId;
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
    public void hasPropertyExceptPropertyId_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> propertyBook.hasPropertyExceptPropertyId(null, new PropertyId(1)));
    }

    @Test
    public void hasProperty_nullPropertyId_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> propertyBook.hasPropertyExceptPropertyId(PROPERTY_A, null));
    }

    @Test
    public void hasPropertyExceptPropertyId_propertyNotInPropertyBook_returnsFalse() {

        // not in property book
        assertFalse(propertyBook.hasPropertyExceptPropertyId(PROPERTY_A, PROPERTY_B.getPropertyId()));

        // has excluded property id
        propertyBook.addProperty(PROPERTY_A);
        assertFalse(propertyBook.hasPropertyExceptPropertyId(PROPERTY_A, PROPERTY_A.getPropertyId()));
    }

    @Test
    public void hasPropertyExceptPropertyId_propertyInPropertyBook_returnsTrue() {
        propertyBook.addProperty(PROPERTY_A);
        assertTrue(propertyBook.hasPropertyExceptPropertyId(PROPERTY_A, PROPERTY_B.getPropertyId()));
    }

    @Test
    public void hasPropertyExceptPropertyId_propertyWithSameIdentityFieldsInPropertyBook_returnsTrue() {
        propertyBook.addProperty(PROPERTY_A);
        Property editedPropertyA = new PropertyBuilder(PROPERTY_B)
                .withAddress(PROPERTY_A.getAddress().toString())
                .build();
        assertTrue(propertyBook.hasPropertyExceptPropertyId(editedPropertyA, PROPERTY_B.getPropertyId()));
    }

    @Test
    public void addProperty_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> propertyBook.addProperty(null));
    }

    @Test
    public void addProperty_duplicateProperty_throwsDuplicatePropertyException() {
        propertyBook.addProperty(PROPERTY_A);
        assertThrows(DuplicatePropertyException.class,
                () -> propertyBook.addProperty(PROPERTY_A));
    }

    @Test
    public void addProperty_existingId_success() {
        Property added = propertyBook.addProperty(PROPERTY_A);
        assertEquals(PROPERTY_A, added);
    }

    @Test
    public void addProperty_defaultId_success() {
        Property added = propertyBook.addProperty(
                new PropertyBuilder(PROPERTY_A)
                        .withPropertyId(PropertyId.DEFAULT_PROPERTY_ID.toString())
                        .build());
        assertEquals(PROPERTY_A.setId(new PropertyId(1)), added);
        added = propertyBook.addProperty(
                new PropertyBuilder(PROPERTY_B)
                        .withPropertyId(PropertyId.DEFAULT_PROPERTY_ID.toString())
                        .build());
        assertEquals(PROPERTY_B.setId(new PropertyId(2)), added);
    }

    @Test
    public void setProperty_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> propertyBook.setProperty(null, PROPERTY_A));
        assertThrows(NullPointerException.class,
                () -> propertyBook.setProperty(PROPERTY_A, null));
    }

    @Test
    public void setProperty_propertyNotFound_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class,
                () -> propertyBook.setProperty(PROPERTY_A, PROPERTY_B));
    }

    @Test
    public void setProperty_duplicateProperty_throwsDuplicatePropertyException() {
        propertyBook.addProperty(PROPERTY_A);
        propertyBook.addProperty(PROPERTY_B);
        assertThrows(DuplicatePropertyException.class,
                () -> propertyBook.setProperty(PROPERTY_A, PROPERTY_B));
        assertThrows(DuplicatePropertyException.class,
                () -> propertyBook.setProperty(PROPERTY_A,
                        new PropertyBuilder(PROPERTY_A)
                                .withAddress(PROPERTY_B.getAddress().toString()).build()));
    }

    @Test
    public void setProperty_sameProperty_success() {
        propertyBook.addProperty(PROPERTY_A);
        propertyBook.setProperty(PROPERTY_A, PROPERTY_A);
        PropertyBook expected = new PropertyBook();
        expected.addProperty(PROPERTY_A);
        assertEquals(expected, propertyBook);
    }

    @Test
    public void setProperty_sucess() {
        propertyBook.addProperty(PROPERTY_A);
        propertyBook.setProperty(PROPERTY_A, PROPERTY_B);
        PropertyBook expected = new PropertyBook();
        expected.addProperty(PROPERTY_B);
        assertEquals(expected, propertyBook);
    }

    @Test
    public void removeProperty_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> propertyBook.removeProperty(null));
    }

    @Test
    public void removeProperty_notInPropertyBook_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class,
                () -> propertyBook.removeProperty(PROPERTY_A));
    }

    @Test
    public void removeProperty_success() {
        propertyBook.addProperty(PROPERTY_A);
        propertyBook.removeProperty(PROPERTY_A);
        assertEquals(new PropertyBook(), propertyBook);
    }

    @Test
    public void removePropertyByPropertyId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> propertyBook.removePropertyByPropertyId(null));
    }

    @Test
    public void removePropertyByPropertyId_notInPropertyBook_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class,
                () -> propertyBook.removePropertyByPropertyId(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void removePropertyByPropertyId_success() {
        propertyBook.addProperty(PROPERTY_A);
        propertyBook.removePropertyByPropertyId(PROPERTY_A.getPropertyId());
        assertEquals(new PropertyBook(), propertyBook);
    }

    @Test
    public void removeAllPropertiesWithSellerId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> propertyBook.removeAllPropertiesWithSellerId(null));
    }

    @Test
    public void removeAllPropertiesWithSellerId_success() {
        propertyBook.removeAllPropertiesWithSellerId(PROPERTY_A.getSellerId());
        assertEquals(new PropertyBook(), propertyBook);
        propertyBook.addProperty(PROPERTY_A);
        propertyBook.addProperty(new PropertyBuilder(PROPERTY_B)
                .withSellerId(PROPERTY_A.getSellerId().toString())
                .build());
        propertyBook.removeAllPropertiesWithSellerId(PROPERTY_A.getSellerId());
        assertEquals(new PropertyBook(), propertyBook);
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
        assertThrows(PropertyNotFoundException.class, () ->
                propertyBook.getPropertyById(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void getPropertiesBySellerId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> propertyBook.getPropertiesBySellerId(null));
    }

    @Test
    public void getPropertiesBySellerId_emptyList() {
        assertEquals(new ArrayList<>(), propertyBook.getPropertiesBySellerId(PROPERTY_A.getSellerId()));
    }

    @Test
    public void getPropertiesBySellerId_getsProperties() {
        propertyBook.addProperty(PROPERTY_A);
        propertyBook.addProperty(PROPERTY_B);
        ArrayList<Property> result = propertyBook.getPropertiesBySellerId(PROPERTY_A.getSellerId());
        ArrayList<Property> expected = new ArrayList<>();
        expected.add(PROPERTY_A);
        assertEquals(expected, result);
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
    public void testToString() {
        propertyBook.addProperty(PROPERTY_A);
        String expected = "1 properties";
        assertEquals(expected, propertyBook.toString());
    }

    @Test
    public void getPropertyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> propertyBook.getPropertyList().remove(0));
    }

    @Test
    public void equals() {
        // same object
        assertTrue(propertyBook.equals(propertyBook));

        // different type
        assertFalse(propertyBook.equals(new BidBook()));

        // same property book
        PropertyBook other = new PropertyBook();
        other.addProperty(PROPERTY_A);
        propertyBook.addProperty(PROPERTY_A);
        assertTrue(propertyBook.equals(other));

        // different property book
        propertyBook.addProperty(PROPERTY_B);
        assertFalse(propertyBook.equals(other));
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

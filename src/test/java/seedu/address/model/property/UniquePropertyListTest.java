package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_ASKING_PRICE_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_NAME_BEDOK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_A;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.id.PropertyId;
import seedu.address.model.property.exceptions.DuplicatePropertyException;
import seedu.address.model.property.exceptions.PropertyNotFoundException;
import seedu.address.testutil.property.PropertyBuilder;

public class UniquePropertyListTest {

    private final UniquePropertyList uniquePropertyList = new UniquePropertyList();

    @Test
    public void contains_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.contains(null));
    }

    @Test
    public void contains_propertyNotInList_returnsFalse() {
        assertFalse(uniquePropertyList.contains(PROPERTY_A));
    }

    @Test
    public void contains_propertyInList_returnsTrue() {
        uniquePropertyList.add(PROPERTY_A);
        assertTrue(uniquePropertyList.contains(PROPERTY_A));
    }

    @Test
    public void contains_propertyWithSamePropertyIdInList_returnsTrue() {
        uniquePropertyList.add(PROPERTY_A);
        Property editedPropertyA = new PropertyBuilder(PROPERTY_B)
                .withPropertyId(PROPERTY_A.getPropertyId().toString())
                .build();
        assertTrue(uniquePropertyList.contains(editedPropertyA));
    }

    @Test
    public void contains_propertyWithSameAddressInList_returnsTrue() {
        uniquePropertyList.add(PROPERTY_A);
        Property editedPropertyA = new PropertyBuilder(PROPERTY_B)
                .withAddress(PROPERTY_A.getAddress().toString())
                .build();
        assertTrue(uniquePropertyList.contains(editedPropertyA));
    }

    @Test
    public void containsExceptPropertyId_nullToCheck_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> uniquePropertyList.containsExceptPropertyId(null,
                        new PropertyId(1)));
    }

    @Test
    public void containsExceptPropertyId_nullPropertyId_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> uniquePropertyList.containsExceptPropertyId(PROPERTY_A,
                        null));
    }

    @Test
    public void containsExceptPropertyId_propertyNotInList_returnsFalse() {

        // property not in list at all
        assertFalse(uniquePropertyList.containsExceptPropertyId(PROPERTY_A,
                new PropertyId(1)));

        // property exists but has excluded property id
        uniquePropertyList.add(PROPERTY_A);
        assertFalse(uniquePropertyList.containsExceptPropertyId(PROPERTY_A,
                PROPERTY_A.getPropertyId()));
    }

    @Test
    public void containsExceptPropertyId_propertyInList_returnsTrue() {
        uniquePropertyList.add(PROPERTY_A);
        uniquePropertyList.add(PROPERTY_B);
        assertTrue(uniquePropertyList.containsExceptPropertyId(PROPERTY_A,
                PROPERTY_B.getPropertyId()));
    }

    @Test
    public void containsExceptPropertyId_propertyWithSamePropertyIdInList_returnsTrue() {
        PropertyId excluded = PROPERTY_B.getPropertyId();
        uniquePropertyList.add(PROPERTY_A);
        Property editedPropertyA = new PropertyBuilder(PROPERTY_B)
                .withPropertyId(PROPERTY_A.getPropertyId().toString())
                .build();
        assertTrue(uniquePropertyList.containsExceptPropertyId(editedPropertyA,
                excluded));
    }

    @Test
    public void containsExceptPropertyId_propertyWithSameAddressInList_returnsTrue() {
        PropertyId excluded = PROPERTY_B.getPropertyId();
        uniquePropertyList.add(PROPERTY_A);
        Property editedPropertyA = new PropertyBuilder(PROPERTY_B)
                .withAddress(PROPERTY_A.getAddress().toString())
                .build();
        assertTrue(uniquePropertyList.containsExceptPropertyId(editedPropertyA,
                excluded));
    }

    @Test
    public void add_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.add(null));
    }

    @Test
    public void add_duplicateProperty_throwsDuplicatePropertyException() {
        uniquePropertyList.add(PROPERTY_A);
        assertThrows(DuplicatePropertyException.class, () -> uniquePropertyList.add(PROPERTY_A));
    }

    @Test
    public void add_existingId_success() {
        Property added = PROPERTY_A;
        assertEquals(added, uniquePropertyList.add(PROPERTY_A));
    }

    @Test
    public void add_defaultId_success() {
        Property toAdd = PROPERTY_A.setId(PropertyId.DEFAULT_PROPERTY_ID);
        Property added = PROPERTY_A.setId(new PropertyId(1));
        assertEquals(added, uniquePropertyList.add(toAdd));
        added = PROPERTY_B.setId(new PropertyId(2));
        toAdd = PROPERTY_B.setId(PropertyId.DEFAULT_PROPERTY_ID);
        assertEquals(added, uniquePropertyList.add(toAdd));
    }

    @Test
    public void setProperty_nullTargetProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperty(null, PROPERTY_A));
    }

    @Test
    public void setProperty_nullEditedProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperty(PROPERTY_A, null));
    }

    @Test
    public void setProperty_targetPropertyNotInList_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () -> uniquePropertyList.setProperty(PROPERTY_A, PROPERTY_A));
    }

    @Test
    public void setProperty_editedPropertyIsSameProperty_success() {
        uniquePropertyList.add(PROPERTY_A);
        uniquePropertyList.setProperty(PROPERTY_A, PROPERTY_A);
        UniquePropertyList otherUniquePropertyList = new UniquePropertyList();
        otherUniquePropertyList.add(PROPERTY_A);
        assertEquals(otherUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_editedPropertyHasSameIdentity_success() {
        uniquePropertyList.add(PROPERTY_A);
        Property editedPropertyA = new PropertyBuilder(PROPERTY_A)
                .withPropertyName(VALID_PROPERTY_NAME_BEDOK)
                .withAskingPrice(VALID_PROPERTY_ASKING_PRICE_BEDOK)
                .build();
        uniquePropertyList.setProperty(PROPERTY_A, editedPropertyA);
        UniquePropertyList otherUniquePropertyList = new UniquePropertyList();
        otherUniquePropertyList.add(editedPropertyA);
        assertEquals(otherUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_editedPropertyHasDifferentIdentity_success() {
        uniquePropertyList.add(PROPERTY_A);
        uniquePropertyList.setProperty(PROPERTY_A, PROPERTY_B);
        UniquePropertyList otherUniquePropertyList = new UniquePropertyList();
        otherUniquePropertyList.add(PROPERTY_B);
        assertEquals(otherUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_editedPropertyHasNonUniqueIdentity_throwsDuplicatePropertyException() {
        uniquePropertyList.add(PROPERTY_A);
        uniquePropertyList.add(PROPERTY_B);
        assertThrows(DuplicatePropertyException.class,
                () -> uniquePropertyList.setProperty(PROPERTY_A, new PropertyBuilder(PROPERTY_A)
                        .withAddress(PROPERTY_B.getAddress().toString()).build()));
        assertThrows(DuplicatePropertyException.class,
                () -> uniquePropertyList.setProperty(PROPERTY_A, new PropertyBuilder(PROPERTY_A)
                        .withPropertyId(PROPERTY_B.getPropertyId().toString()).build()));
        assertThrows(DuplicatePropertyException.class, () -> uniquePropertyList.setProperty(PROPERTY_A, PROPERTY_B));
    }

    @Test
    public void remove_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.remove(null));
    }

    @Test
    public void remove_propertyDoesNotExist_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () -> uniquePropertyList.remove(PROPERTY_A));
    }

    @Test
    public void remove_existingProperty_removesProperty() {
        uniquePropertyList.add(PROPERTY_A);
        uniquePropertyList.remove(PROPERTY_A);
        UniquePropertyList otherUniquePropertyList = new UniquePropertyList();
        assertEquals(otherUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void removeByPropertyId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyList.removeByPropertyId(null));
    }

    @Test
    public void removeByPropertyId_propertyDoesNotExist_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () ->
                uniquePropertyList.removeByPropertyId(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void removeByPropertyId_existingProperty_removesProperty() {
        uniquePropertyList.add(PROPERTY_A);
        uniquePropertyList.removeByPropertyId(PROPERTY_A.getPropertyId());
        UniquePropertyList otherUniquePropertyList = new UniquePropertyList();
        assertEquals(otherUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void removeAllWithSellerId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyList.removeAllWithSellerId(null));
    }

    @Test
    public void removeAllWithSellerId_existingProperty_removesProperty() {
        uniquePropertyList.add(PROPERTY_A);
        uniquePropertyList.add(new PropertyBuilder(PROPERTY_B)
                .withSellerId(PROPERTY_A.getSellerId().toString())
                .build());
        uniquePropertyList.removeAllWithSellerId(PROPERTY_A.getSellerId());
        UniquePropertyList otherUniquePropertyList = new UniquePropertyList();
        assertEquals(otherUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void getPropertyById_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyList.getPropertyById(null));
    }

    @Test
    public void getPropertyById_propertyDoesNotExist_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () ->
                uniquePropertyList.getPropertyById(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void getPropertyById_existingId_getsProperty() {
        uniquePropertyList.add(PROPERTY_A);
        assertEquals(PROPERTY_A, uniquePropertyList.getPropertyById(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void containsPropertyId_existingId_returnsTrue() {
        uniquePropertyList.add(PROPERTY_A);
        assertTrue(uniquePropertyList.containsPropertyId(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void containsPropertyId_propertyDoesNotExist_returnsFalse() {
        assertFalse(uniquePropertyList.containsPropertyId(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void containsPropertyId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.containsPropertyId(null));
    }

    @Test
    public void setProperties_nullUniquePropertyList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperties((UniquePropertyList) null));
    }

    @Test
    public void setProperties_uniquePropertyList_replacesOwnListWithProvidedUniquePropertyList() {
        uniquePropertyList.add(PROPERTY_A);
        UniquePropertyList otherUniquePropertyList = new UniquePropertyList();
        otherUniquePropertyList.add(PROPERTY_B);
        uniquePropertyList.setProperties(otherUniquePropertyList);
        assertEquals(otherUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperties_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperties((List<Property>) null));
    }

    @Test
    public void setProperties_list_replacesOwnListWithProvidedList() {
        uniquePropertyList.add(PROPERTY_A);
        List<Property> propertyList = Collections.singletonList(PROPERTY_B);
        uniquePropertyList.setProperties(propertyList);
        UniquePropertyList otherUniquePropertyList = new UniquePropertyList();
        otherUniquePropertyList.add(PROPERTY_B);
        assertEquals(otherUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperties_listWithDuplicateProperties_throwsDuplicatePropertyException() {
        List<Property> listWithDuplicateProperties = Arrays.asList(PROPERTY_A, PROPERTY_A);
        assertThrows(DuplicatePropertyException.class, () ->
                uniquePropertyList.setProperties(listWithDuplicateProperties));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniquePropertyList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals() {
        UniquePropertyList list = new UniquePropertyList();

        // same object
        assertTrue(list.equals(list));

        // different type
        assertFalse(list.equals(new ArrayList<>()));

        // same list
        list.add(PROPERTY_A);
        UniquePropertyList other = new UniquePropertyList();
        other.add(PROPERTY_A);
        assertTrue(list.equals(other));

        // different list
        list.add(PROPERTY_B);
        assertFalse(list.equals(other));
    }
}

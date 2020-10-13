package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_ASKING_PRICE_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_NAME_BEDOK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_A;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

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
    public void add_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.add(null));
    }

    @Test
    public void add_duplicateProperty_throwsDuplicatePropertyException() {
        uniquePropertyList.add(PROPERTY_A);
        assertThrows(DuplicatePropertyException.class, () -> uniquePropertyList.add(PROPERTY_A));
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
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(PROPERTY_A);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_editedPropertyHasSameIdentity_success() {
        uniquePropertyList.add(PROPERTY_A);
        Property editedPropertyA = new PropertyBuilder(PROPERTY_A)
                .withPropertyName(VALID_PROPERTY_NAME_BEDOK)
                .withAskingPrice(VALID_PROPERTY_ASKING_PRICE_BEDOK)
                .build();
        uniquePropertyList.setProperty(PROPERTY_A, editedPropertyA);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(editedPropertyA);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_editedPropertyHasDifferentIdentity_success() {
        uniquePropertyList.add(PROPERTY_A);
        uniquePropertyList.setProperty(PROPERTY_A, PROPERTY_B);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(PROPERTY_B);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_editedPropertyHasNonUniqueIdentity_throwsDuplicatePropertyException() {
        uniquePropertyList.add(PROPERTY_A);
        uniquePropertyList.add(PROPERTY_B);
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
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void getPropertyById_existingId_getsProperty() {
        uniquePropertyList.add(PROPERTY_A);
        assertEquals(PROPERTY_A, uniquePropertyList.getPropertyById(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void getPropertyById_propertyDoesNotExist_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () ->
                uniquePropertyList.getPropertyById(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void getPropertyById_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyList.getPropertyById(null));
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
    public void removeByPropertyId_existingProperty_removesProperty() {
        uniquePropertyList.add(PROPERTY_A);
        uniquePropertyList.removeByPropertyId(PROPERTY_A.getPropertyId());
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void removeByPropertyId_propertyDoesNotExist_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () ->
                uniquePropertyList.removeByPropertyId(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void removeByPropertyId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyList.removeByPropertyId(null));
    }

    @Test
    public void setProperties_nullUniquePropertyList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperties((UniquePropertyList) null));
    }

    @Test
    public void setProperties_uniquePropertyList_replacesOwnListWithProvidedUniquePropertyList() {
        uniquePropertyList.add(PROPERTY_A);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(PROPERTY_B);
        uniquePropertyList.setProperties(expectedUniquePropertyList);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
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
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(PROPERTY_B);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
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
}

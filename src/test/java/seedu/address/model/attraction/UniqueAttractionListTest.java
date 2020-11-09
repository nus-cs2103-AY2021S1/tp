package seedu.address.model.attraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SIGHTSEEING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.BOTANIC_GARDENS;
import static seedu.address.testutil.TypicalAttractions.SUNTEC;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.attraction.exceptions.AttractionNotFoundException;
import seedu.address.model.attraction.exceptions.DuplicateAttractionException;
import seedu.address.testutil.AttractionBuilder;

public class UniqueAttractionListTest {

    private final UniqueAttractionList uniqueAttractionList = new UniqueAttractionList();

    @Test
    public void contains_nullAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAttractionList.contains(null));
    }

    @Test
    public void contains_attractionNotInList_returnsFalse() {
        assertFalse(uniqueAttractionList.contains(BOTANIC_GARDENS));
    }

    @Test
    public void contains_attractionInList_returnsTrue() {
        uniqueAttractionList.add(BOTANIC_GARDENS);
        assertTrue(uniqueAttractionList.contains(BOTANIC_GARDENS));
    }

    @Test
    public void contains_attractionWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAttractionList.add(BOTANIC_GARDENS);
        Attraction editedAttraction = new AttractionBuilder(BOTANIC_GARDENS)
            .withAddress(VALID_ADDRESS_MBS).withTags(VALID_TAG_SIGHTSEEING).build();
        assertTrue(uniqueAttractionList.contains(editedAttraction));
    }

    @Test
    public void add_nullAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAttractionList.add(null));
    }

    @Test
    public void add_duplicateAttraction_throwsDuplicateAttractionException() {
        uniqueAttractionList.add(BOTANIC_GARDENS);
        assertThrows(DuplicateAttractionException.class, () -> uniqueAttractionList.add(BOTANIC_GARDENS));
    }

    @Test
    public void setAttraction_nullTargetAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> uniqueAttractionList.setAttraction(null, BOTANIC_GARDENS));
    }

    @Test
    public void setAttraction_nullEditedAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> uniqueAttractionList.setAttraction(BOTANIC_GARDENS, null));
    }

    @Test
    public void setAttraction_targetAttractionNotInList_throwsAttractionNotFoundException() {
        assertThrows(AttractionNotFoundException.class, ()
            -> uniqueAttractionList.setAttraction(BOTANIC_GARDENS, BOTANIC_GARDENS));
    }

    @Test
    public void setAttraction_editedAttractionIsSameAttraction_success() {
        uniqueAttractionList.add(BOTANIC_GARDENS);
        uniqueAttractionList.setAttraction(BOTANIC_GARDENS, BOTANIC_GARDENS);
        UniqueAttractionList expectedUniqueAttractionList = new UniqueAttractionList();
        expectedUniqueAttractionList.add(BOTANIC_GARDENS);
        assertEquals(expectedUniqueAttractionList, uniqueAttractionList);
    }

    @Test
    public void setAttraction_editedAttractionHasSameIdentity_success() {
        uniqueAttractionList.add(BOTANIC_GARDENS);
        Attraction editedAttraction = new AttractionBuilder(BOTANIC_GARDENS)
            .withAddress(VALID_ADDRESS_MBS).withTags(VALID_TAG_SIGHTSEEING).build();
        uniqueAttractionList.setAttraction(BOTANIC_GARDENS, editedAttraction);
        UniqueAttractionList expectedUniqueAttractionList = new UniqueAttractionList();
        expectedUniqueAttractionList.add(editedAttraction);
        assertEquals(expectedUniqueAttractionList, uniqueAttractionList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueAttractionList.add(BOTANIC_GARDENS);
        uniqueAttractionList.setAttraction(BOTANIC_GARDENS, SUNTEC);
        UniqueAttractionList expectedUniqueAttractionList = new UniqueAttractionList();
        expectedUniqueAttractionList.add(SUNTEC);
        assertEquals(expectedUniqueAttractionList, uniqueAttractionList);
    }

    @Test
    public void setAttraction_editedAttractionHasNonUniqueIdentity_throwsDuplicateAttractionException() {
        uniqueAttractionList.add(BOTANIC_GARDENS);
        uniqueAttractionList.add(SUNTEC);
        assertThrows(DuplicateAttractionException.class, ()
            -> uniqueAttractionList.setAttraction(BOTANIC_GARDENS, SUNTEC));
    }

    @Test
    public void remove_nullAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAttractionList.remove(null));
    }

    @Test
    public void remove_attractionDoesNotExist_throwsAttractionNotFoundException() {
        assertThrows(AttractionNotFoundException.class, () -> uniqueAttractionList.remove(BOTANIC_GARDENS));
    }

    @Test
    public void remove_existingAttraction_removesAttraction() {
        uniqueAttractionList.add(BOTANIC_GARDENS);
        uniqueAttractionList.remove(BOTANIC_GARDENS);
        UniqueAttractionList expectedUniquePersonList = new UniqueAttractionList();
        assertEquals(expectedUniquePersonList, uniqueAttractionList);
    }

    @Test
    public void setAttractions_nullUniqueAttractionList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> uniqueAttractionList.setAttractions((UniqueAttractionList) null));
    }

    @Test
    public void setAttractions_uniqueAttractionList_replacesOwnListWithProvidedUniqueAttractionList() {
        uniqueAttractionList.add(BOTANIC_GARDENS);
        UniqueAttractionList expectedUniqueAttractionList = new UniqueAttractionList();
        expectedUniqueAttractionList.add(SUNTEC);
        uniqueAttractionList.setAttractions(expectedUniqueAttractionList);
        assertEquals(expectedUniqueAttractionList, uniqueAttractionList);
    }

    @Test
    public void setAttractions_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAttractionList.setAttractions((List<Attraction>) null));
    }

    @Test
    public void setAttractions_list_replacesOwnListWithProvidedList() {
        uniqueAttractionList.add(BOTANIC_GARDENS);
        List<Attraction> attractionList = Collections.singletonList(SUNTEC);
        uniqueAttractionList.setAttractions(attractionList);
        UniqueAttractionList expectedUniqueAttractionList = new UniqueAttractionList();
        expectedUniqueAttractionList.add(SUNTEC);
        assertEquals(expectedUniqueAttractionList, uniqueAttractionList);
    }

    @Test
    public void setAttractions_listWithDuplicateAttractions_throwsDuplicateAttractionException() {
        List<Attraction> listWithDuplicateAttractions = Arrays.asList(BOTANIC_GARDENS, BOTANIC_GARDENS);
        assertThrows(DuplicateAttractionException.class, ()
            -> uniqueAttractionList.setAttractions(listWithDuplicateAttractions));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueAttractionList.asUnmodifiableObservableList().remove(0));
    }
}

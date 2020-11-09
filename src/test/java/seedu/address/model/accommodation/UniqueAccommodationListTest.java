package seedu.address.model.accommodation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicals.TypicalAccommodations.ALICEHOTEL;
import static seedu.address.testutil.typicals.TypicalAccommodations.BOBHOTEL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.accommodation.exceptions.AccommodationNotFoundException;
import seedu.address.model.accommodation.exceptions.DuplicateAccommodationException;
import seedu.address.testutil.builders.AccommodationBuilder;

public class UniqueAccommodationListTest {
    private final UniqueAccommodationList uniqueAccommodationList = new UniqueAccommodationList();

    @Test
    public void contains_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAccommodationList.contains(null));
    }

    @Test
    public void contains_accommodationNotInList_returnsFalse() {
        assertFalse(uniqueAccommodationList.contains(ALICEHOTEL));
    }

    @Test
    public void contains_accommodationInList_returnsTrue() {
        uniqueAccommodationList.add(ALICEHOTEL);
        assertTrue(uniqueAccommodationList.contains(ALICEHOTEL));
    }

    @Test
    public void contains_accommodationWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAccommodationList.add(ALICEHOTEL);
        Accommodation editedAlice = new AccommodationBuilder(ALICEHOTEL).withLocation("Not Alice").build();
        assertTrue(uniqueAccommodationList.contains(editedAlice));
    }

    @Test
    public void add_nullAccommodation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAccommodationList.add(null));
    }

    @Test
    public void add_duplicateAccommodation_throwsDuplicateAccommodationException() {
        uniqueAccommodationList.add(ALICEHOTEL);
        assertThrows(DuplicateAccommodationException.class, () -> uniqueAccommodationList.add(ALICEHOTEL));
    }

    @Test
    public void setAccommodation_nullTargetAccommodation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueAccommodationList.setAccommodation(null, ALICEHOTEL));
    }

    @Test
    public void setAccommodation_nullEditedAccommodation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueAccommodationList.setAccommodation(ALICEHOTEL, null));
    }

    @Test
    public void setAccommodation_targetAccommodationNotInList_throwsAccommodationNotFoundException() {
        assertThrows(AccommodationNotFoundException.class, () ->
                uniqueAccommodationList.setAccommodation(ALICEHOTEL, ALICEHOTEL));
    }

    @Test
    public void setAccommodation_editedAccommodationIsSameAccommodation_success() {
        uniqueAccommodationList.add(ALICEHOTEL);
        uniqueAccommodationList.setAccommodation(ALICEHOTEL, ALICEHOTEL);
        UniqueAccommodationList expectedUniqueAccommodationList = new UniqueAccommodationList();
        expectedUniqueAccommodationList.add(ALICEHOTEL);
        assertEquals(expectedUniqueAccommodationList, uniqueAccommodationList);
    }

    @Test
    public void setAccommodation_editedAccommodationHasSameIdentity_success() {
        uniqueAccommodationList.add(ALICEHOTEL);
        Accommodation editedAlice = new AccommodationBuilder(ALICEHOTEL).withLocation("Not Alice").build();
        uniqueAccommodationList.setAccommodation(ALICEHOTEL, editedAlice);
        UniqueAccommodationList expectedUniqueAccommodationList = new UniqueAccommodationList();
        expectedUniqueAccommodationList.add(editedAlice);
        assertEquals(expectedUniqueAccommodationList, uniqueAccommodationList);
    }

    @Test
    public void setAccommodation_editedAccommodationHasDifferentIdentity_success() {
        uniqueAccommodationList.add(ALICEHOTEL);
        uniqueAccommodationList.setAccommodation(ALICEHOTEL, BOBHOTEL);
        UniqueAccommodationList expectedUniqueAccommodationList = new UniqueAccommodationList();
        expectedUniqueAccommodationList.add(BOBHOTEL);
        assertEquals(expectedUniqueAccommodationList, uniqueAccommodationList);
    }

    @Test
    public void setAccommodation_editedAccommodationHasNonUniqueIdentity_throwsDuplicateAccommodationException() {
        uniqueAccommodationList.add(ALICEHOTEL);
        uniqueAccommodationList.add(BOBHOTEL);
        assertThrows(DuplicateAccommodationException.class, () ->
                uniqueAccommodationList.setAccommodation(ALICEHOTEL, BOBHOTEL));
    }

    @Test
    public void remove_nullAccommodation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAccommodationList.remove(null));
    }

    @Test
    public void remove_accommodationDoesNotExist_throwsAccommodationNotFoundException() {
        assertThrows(AccommodationNotFoundException.class, () -> uniqueAccommodationList.remove(ALICEHOTEL));
    }

    @Test
    public void remove_existingAccommodation_removesAccommodation() {
        uniqueAccommodationList.add(ALICEHOTEL);
        uniqueAccommodationList.remove(ALICEHOTEL);
        UniqueAccommodationList expectedUniqueAccommodationList = new UniqueAccommodationList();
        assertEquals(expectedUniqueAccommodationList, uniqueAccommodationList);
    }

    @Test
    public void setAccommodations_nullUniqueAccommodationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueAccommodationList.setAccommodations((UniqueAccommodationList) null));
    }

    @Test
    public void setAccommodations_uniqueAccommodationList_replacesOwnListWithProvidedUniqueAccommodationList() {
        uniqueAccommodationList.add(ALICEHOTEL);
        UniqueAccommodationList expectedUniqueAccommodationList = new UniqueAccommodationList();
        expectedUniqueAccommodationList.add(BOBHOTEL);
        uniqueAccommodationList.setAccommodations(expectedUniqueAccommodationList);
        assertEquals(expectedUniqueAccommodationList, uniqueAccommodationList);
    }

    @Test
    public void setAccommodations_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueAccommodationList.setAccommodations((List<Accommodation>) null));
    }

    @Test
    public void setAccommodations_list_replacesOwnListWithProvidedList() {
        uniqueAccommodationList.add(ALICEHOTEL);
        List<Accommodation> accommodationList = Collections.singletonList(BOBHOTEL);
        uniqueAccommodationList.setAccommodations(accommodationList);
        UniqueAccommodationList expectedUniqueAccommodationList = new UniqueAccommodationList();
        expectedUniqueAccommodationList.add(BOBHOTEL);
        assertEquals(expectedUniqueAccommodationList, uniqueAccommodationList);
    }

    @Test
    public void setAccommodations_listWithDuplicateAccommodations_throwsDuplicateAccommodationException() {
        List<Accommodation> listWithDuplicateAccommodations = Arrays.asList(ALICEHOTEL, ALICEHOTEL);
        assertThrows(DuplicateAccommodationException.class, () ->
                uniqueAccommodationList.setAccommodations(listWithDuplicateAccommodations));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueAccommodationList.asUnmodifiableObservableList().remove(0));
    }
}

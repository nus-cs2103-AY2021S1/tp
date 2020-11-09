package seedu.address.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_JAPAN_TRIP;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItineraries.JAPAN_TRIP;
import static seedu.address.testutil.TypicalItineraries.PARIS_TRIP;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.itinerary.exceptions.DuplicateItineraryException;
import seedu.address.model.itinerary.exceptions.ItineraryNotFoundException;
import seedu.address.testutil.ItineraryBuilder;

public class UniqueItineraryListTest {

    private final UniqueItineraryList uniqueItineraryList = new UniqueItineraryList();

    @Test
    public void contains_nullItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.contains(null));
    }

    @Test
    public void contains_itineraryNotInList_returnsFalse() {
        assertFalse(uniqueItineraryList.contains(PARIS_TRIP));
    }

    @Test
    public void contains_itineraryInList_returnsTrue() {
        uniqueItineraryList.add(PARIS_TRIP);
        assertTrue(uniqueItineraryList.contains(PARIS_TRIP));
    }

    @Test
    public void contains_itineraryWithSameIdentityFieldsInList_returnsTrue() {
        uniqueItineraryList.add(PARIS_TRIP);
        Itinerary editedItinerary = new ItineraryBuilder(PARIS_TRIP)
                .withDescription(VALID_DESCRIPTION_JAPAN_TRIP).withBudget(VALID_BUDGET_JAPAN_TRIP).build();
        assertTrue(uniqueItineraryList.contains(editedItinerary));
    }

    @Test
    public void add_nullItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.add(null));
    }

    @Test
    public void add_duplicateItinerary_throwsDuplicateItineraryException() {
        uniqueItineraryList.add(PARIS_TRIP);
        assertThrows(DuplicateItineraryException.class, () -> uniqueItineraryList.add(PARIS_TRIP));
    }

    @Test
    public void setItinerary_nullTargetItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.setItinerary(null, PARIS_TRIP));
    }

    @Test
    public void setItinerary_nullEditedItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.setItinerary(PARIS_TRIP, null));
    }

    @Test
    public void setItinerary_targetItineraryNotInList_throwsItineraryNotFoundException() {
        assertThrows(ItineraryNotFoundException.class, () -> uniqueItineraryList.setItinerary(PARIS_TRIP, PARIS_TRIP));
    }

    @Test
    public void setItinerary_editedItineraryIsSameItinerary_success() {
        uniqueItineraryList.add(PARIS_TRIP);
        uniqueItineraryList.setItinerary(PARIS_TRIP, PARIS_TRIP);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        expectedUniqueItineraryList.add(PARIS_TRIP);
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setItinerary_editedItineraryHasSameIdentity_success() {
        uniqueItineraryList.add(PARIS_TRIP);
        Itinerary editedItinerary = new ItineraryBuilder(PARIS_TRIP)
                .withDescription(VALID_DESCRIPTION_JAPAN_TRIP).withBudget(VALID_BUDGET_JAPAN_TRIP).build();
        uniqueItineraryList.setItinerary(PARIS_TRIP, editedItinerary);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        expectedUniqueItineraryList.add(editedItinerary);
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueItineraryList.add(PARIS_TRIP);
        uniqueItineraryList.setItinerary(PARIS_TRIP, JAPAN_TRIP);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        expectedUniqueItineraryList.add(JAPAN_TRIP);
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setItinerary_editedItineraryHasNonUniqueIdentity_throwsDuplicateItineraryException() {
        uniqueItineraryList.add(PARIS_TRIP);
        uniqueItineraryList.add(JAPAN_TRIP);
        assertThrows(DuplicateItineraryException.class, () -> uniqueItineraryList.setItinerary(PARIS_TRIP, JAPAN_TRIP));
    }

    @Test
    public void remove_nullItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.remove(null));
    }

    @Test
    public void remove_itineraryDoesNotExist_throwsItineraryNotFoundException() {
        assertThrows(ItineraryNotFoundException.class, () -> uniqueItineraryList.remove(PARIS_TRIP));
    }

    @Test
    public void remove_existingItinerary_removesItinerary() {
        uniqueItineraryList.add(PARIS_TRIP);
        uniqueItineraryList.remove(PARIS_TRIP);
        UniqueItineraryList expectedUniquePersonList = new UniqueItineraryList();
        assertEquals(expectedUniquePersonList, uniqueItineraryList);
    }

    @Test
    public void setItineraries_nullUniqueItineraryList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.setItineraries((UniqueItineraryList) null));
    }

    @Test
    public void setItineraries_uniqueItineraryList_replacesOwnListWithProvidedUniqueItineraryList() {
        uniqueItineraryList.add(PARIS_TRIP);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        expectedUniqueItineraryList.add(JAPAN_TRIP);
        uniqueItineraryList.setItineraries(expectedUniqueItineraryList);
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setItineraries_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.setItineraries((List<Itinerary>) null));
    }

    @Test
    public void setItineraries_list_replacesOwnListWithProvidedList() {
        uniqueItineraryList.add(PARIS_TRIP);
        List<Itinerary> itineraryList = Collections.singletonList(JAPAN_TRIP);
        uniqueItineraryList.setItineraries(itineraryList);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        expectedUniqueItineraryList.add(JAPAN_TRIP);
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setItineraries_listWithDuplicateItineraries_throwsDuplicateItineraryException() {
        List<Itinerary> listWithDuplicateItineraries = Arrays.asList(PARIS_TRIP, PARIS_TRIP);
        assertThrows(DuplicateItineraryException.class, ()
            -> uniqueItineraryList.setItineraries(listWithDuplicateItineraries));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueItineraryList.asUnmodifiableObservableList().remove(0));
    }
}

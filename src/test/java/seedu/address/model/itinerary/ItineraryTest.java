package seedu.address.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_JAPAN_TRIP;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItineraries.JAPAN_TRIP;
import static seedu.address.testutil.TypicalItineraries.PARIS_TRIP;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItineraryBuilder;

class ItineraryTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Itinerary itinerary = new ItineraryBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> itinerary.getDays().remove(0));
    }

    @Test
    public void isSameItinerary() {
        // same object -> returns true
        assertTrue(PARIS_TRIP.isSameItinerary(PARIS_TRIP));

        // null -> returns false
        assertFalse(PARIS_TRIP.isSameItinerary(null));

        // different name -> returns false
        Itinerary editedParis = new ItineraryBuilder(PARIS_TRIP).withName(VALID_NAME_JAPAN_TRIP).build();
        assertFalse(PARIS_TRIP.isSameItinerary(editedParis));

        // same name, same start date, same end date, different attributes -> returns true
        editedParis = new ItineraryBuilder(PARIS_TRIP)
                .withDescription(VALID_DESCRIPTION_JAPAN_TRIP)
                .withBudget(VALID_BUDGET_JAPAN_TRIP)
                .build();
        assertTrue(PARIS_TRIP.isSameItinerary(editedParis));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Itinerary parisCopy = new ItineraryBuilder(PARIS_TRIP).build();
        assertTrue(PARIS_TRIP.equals(parisCopy));

        // same object -> returns true
        assertTrue(PARIS_TRIP.equals(PARIS_TRIP));

        // null -> returns false
        assertFalse(PARIS_TRIP.equals(null));

        // different type -> returns false
        assertFalse(PARIS_TRIP.equals(5));

        // different itinerary -> returns false
        assertFalse(PARIS_TRIP.equals(JAPAN_TRIP));

        // different name -> returns false
        Itinerary editedJapan = new ItineraryBuilder(JAPAN_TRIP).withName(VALID_NAME_PARIS_TRIP).build();
        assertFalse(JAPAN_TRIP.equals(editedJapan));

        // different description -> returns false
        editedJapan = new ItineraryBuilder(JAPAN_TRIP).withDescription(VALID_DESCRIPTION_PARIS_TRIP).build();
        assertFalse(JAPAN_TRIP.equals(editedJapan));

        // different start date -> returns false
        Itinerary editedParis = new ItineraryBuilder(PARIS_TRIP).withStartDate(VALID_START_DATE_JAPAN_TRIP).build();
        assertFalse(PARIS_TRIP.equals(editedParis));

        // different end date -> returns false
        editedJapan = new ItineraryBuilder(JAPAN_TRIP).withEndDate(VALID_END_DATE_PARIS_TRIP).build();
        assertFalse(JAPAN_TRIP.equals(editedJapan));

        // different budget -> returns false
        editedJapan = new ItineraryBuilder(JAPAN_TRIP).withBudget(VALID_BUDGET_PARIS_TRIP).build();
        assertFalse(JAPAN_TRIP.equals(editedJapan));
    }
}

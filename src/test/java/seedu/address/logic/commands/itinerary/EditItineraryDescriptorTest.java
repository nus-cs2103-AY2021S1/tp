package seedu.address.logic.commands.itinerary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_JAPAN_TRIP;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.itinerary.EditItineraryCommand.EditItineraryDescriptor;
import seedu.address.testutil.EditItineraryDescriptorBuilder;

public class EditItineraryDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditItineraryDescriptor descriptorWithSameValues = new EditItineraryDescriptor(DESC_PARIS_TRIP);
        assertTrue(DESC_PARIS_TRIP.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_PARIS_TRIP.equals(DESC_PARIS_TRIP));

        // null -> returns false
        assertFalse(DESC_PARIS_TRIP.equals(null));

        // different types -> returns false
        assertFalse(DESC_PARIS_TRIP.equals(5));

        // different values -> returns false
        assertFalse(DESC_PARIS_TRIP.equals(DESC_JAPAN_TRIP));

        // different name -> returns false
        EditItineraryDescriptor editedParisTrip = new EditItineraryDescriptorBuilder(DESC_PARIS_TRIP)
                .withName(VALID_NAME_JAPAN_TRIP).build();
        assertFalse(DESC_PARIS_TRIP.equals(editedParisTrip));

        // different description -> returns false
        editedParisTrip = new EditItineraryDescriptorBuilder(DESC_PARIS_TRIP)
                .withDescription(VALID_DESCRIPTION_JAPAN_TRIP).build();
        assertFalse(DESC_PARIS_TRIP.equals(editedParisTrip));

        // different start date -> returns false
        editedParisTrip = new EditItineraryDescriptorBuilder(DESC_PARIS_TRIP)
                .withStartDate(VALID_START_DATE_JAPAN_TRIP).build();
        assertFalse(DESC_PARIS_TRIP.equals(editedParisTrip));

        // different end date -> returns false
        editedParisTrip = new EditItineraryDescriptorBuilder(DESC_PARIS_TRIP)
                .withEndDate(VALID_END_DATE_JAPAN_TRIP).build();
        assertFalse(DESC_PARIS_TRIP.equals(editedParisTrip));

        // different budget -> returns false
        editedParisTrip = new EditItineraryDescriptorBuilder(DESC_PARIS_TRIP)
                .withBudget(VALID_BUDGET_JAPAN_TRIP).build();
        assertFalse(DESC_PARIS_TRIP.equals(editedParisTrip));
    }
}

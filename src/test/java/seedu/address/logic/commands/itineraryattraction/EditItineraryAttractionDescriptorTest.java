package seedu.address.logic.commands.itineraryattraction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EIFFEL_IA;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MBS_IA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPENING_HOURS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_RANGE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VISITED_MBS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.itineraryattraction.EditItineraryAttractionCommand.EditItineraryAttractionDescriptor;
import seedu.address.testutil.EditItineraryAttractionDescriptorBuilder;

public class EditItineraryAttractionDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditItineraryAttractionDescriptor descriptorWithSameValues =
                new EditItineraryAttractionDescriptor(DESC_EIFFEL_IA);
        assertTrue(DESC_EIFFEL_IA.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_EIFFEL_IA.equals(DESC_EIFFEL_IA));

        // null -> returns false
        assertFalse(DESC_EIFFEL_IA.equals(null));

        // different types -> returns false
        assertFalse(DESC_EIFFEL_IA.equals(5));

        // different values -> returns false
        assertFalse(DESC_EIFFEL_IA.equals(DESC_MBS_IA));

        // different name -> returns false
        EditItineraryAttractionDescriptor editedEiffel = new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA)
                .withName(VALID_NAME_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different phone -> returns false
        editedEiffel = new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA).withPhone(VALID_PHONE_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different email -> returns false
        editedEiffel = new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA).withEmail(VALID_EMAIL_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different address -> returns false
        editedEiffel =
                new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA).withAddress(VALID_ADDRESS_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different location -> returns false
        editedEiffel = new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA)
                .withLocation(VALID_LOCATION_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different description -> returns false
        editedEiffel = new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA)
                .withDescription(VALID_DESCRIPTION_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different opening hours -> returns false
        editedEiffel = new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA)
                .withOpeningHours(VALID_OPENING_HOURS_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different price range -> returns false
        editedEiffel = new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA)
                .withPriceRange(VALID_PRICE_RANGE_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different rating -> returns false
        editedEiffel =
                new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA).withRating(VALID_RATING_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different visited -> returns false
        editedEiffel =
                new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA).withVisited(VALID_VISITED_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different tags -> returns false
        editedEiffel =
                new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA).withTags(VALID_TAG_ACTIVITY).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different start time -> returns false
        editedEiffel = new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA)
                .withStartTime(VALID_START_TIME_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different end time -> returns false
        editedEiffel =
                new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA).withEndTime(VALID_END_TIME_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

    }
}

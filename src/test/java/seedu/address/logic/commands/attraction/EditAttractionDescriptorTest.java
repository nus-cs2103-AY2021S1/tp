package seedu.address.logic.commands.attraction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPENING_HOURS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_RANGE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VISITED_MBS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.attraction.EditAttractionCommand.EditAttractionDescriptor;
import seedu.address.testutil.EditAttractionDescriptorBuilder;

public class EditAttractionDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditAttractionDescriptor descriptorWithSameValues = new EditAttractionDescriptor(DESC_EIFFEL);
        assertTrue(DESC_EIFFEL.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_EIFFEL.equals(DESC_EIFFEL));

        // null -> returns false
        assertFalse(DESC_EIFFEL.equals(null));

        // different types -> returns false
        assertFalse(DESC_EIFFEL.equals(5));

        // different values -> returns false
        assertFalse(DESC_EIFFEL.equals(DESC_MBS));

        // different name -> returns false
        EditAttractionDescriptor editedEiffel = new EditAttractionDescriptorBuilder(DESC_EIFFEL)
                .withName(VALID_NAME_MBS).build();
        assertFalse(DESC_EIFFEL.equals(editedEiffel));

        // different phone -> returns false
        editedEiffel = new EditAttractionDescriptorBuilder(DESC_EIFFEL).withPhone(VALID_PHONE_MBS).build();
        assertFalse(DESC_EIFFEL.equals(editedEiffel));

        // different email -> returns false
        editedEiffel = new EditAttractionDescriptorBuilder(DESC_EIFFEL).withEmail(VALID_EMAIL_MBS).build();
        assertFalse(DESC_EIFFEL.equals(editedEiffel));

        // different address -> returns false
        editedEiffel = new EditAttractionDescriptorBuilder(DESC_EIFFEL).withAddress(VALID_ADDRESS_MBS).build();
        assertFalse(DESC_EIFFEL.equals(editedEiffel));

        // different location -> returns false
        editedEiffel = new EditAttractionDescriptorBuilder(DESC_EIFFEL).withLocation(VALID_LOCATION_MBS).build();
        assertFalse(DESC_EIFFEL.equals(editedEiffel));

        // different description -> returns false
        editedEiffel = new EditAttractionDescriptorBuilder(DESC_EIFFEL).withDescription(VALID_DESCRIPTION_MBS).build();
        assertFalse(DESC_EIFFEL.equals(editedEiffel));

        // different opening hours -> returns false
        editedEiffel = new EditAttractionDescriptorBuilder(DESC_EIFFEL)
                .withOpeningHours(VALID_OPENING_HOURS_MBS).build();
        assertFalse(DESC_EIFFEL.equals(editedEiffel));

        // different price range -> returns false
        editedEiffel = new EditAttractionDescriptorBuilder(DESC_EIFFEL).withPriceRange(VALID_PRICE_RANGE_MBS).build();
        assertFalse(DESC_EIFFEL.equals(editedEiffel));

        // different rating -> returns false
        editedEiffel = new EditAttractionDescriptorBuilder(DESC_EIFFEL).withRating(VALID_RATING_MBS).build();
        assertFalse(DESC_EIFFEL.equals(editedEiffel));

        // different visited -> returns false
        editedEiffel = new EditAttractionDescriptorBuilder(DESC_EIFFEL).withVisited(VALID_VISITED_MBS).build();
        assertFalse(DESC_EIFFEL.equals(editedEiffel));

        // different tags -> returns false
        editedEiffel = new EditAttractionDescriptorBuilder(DESC_EIFFEL).withTags(VALID_TAG_ACTIVITY).build();
        assertFalse(DESC_EIFFEL.equals(editedEiffel));
    }
}

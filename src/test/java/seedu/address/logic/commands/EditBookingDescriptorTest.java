package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditBookingDescriptorBuilder;

public class EditBookingDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditBookingCommand.EditBookingDescriptor descriptorWithSameValues =
                new EditBookingCommand.EditBookingDescriptor(DESC_BOOKING_AMY);
        assertTrue(DESC_BOOKING_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_BOOKING_AMY.equals(DESC_BOOKING_AMY));

        // null -> returns false
        assertFalse(DESC_BOOKING_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_BOOKING_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_BOOKING_AMY.equals(DESC_BOOKING_BOB));

        // different room id -> returns false
        EditBookingCommand.EditBookingDescriptor editedAmy =
                new EditBookingDescriptorBuilder(DESC_BOOKING_AMY).withRoomId(VALID_ROOM_ID_BOB).build();
        assertFalse(DESC_BOOKING_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditBookingDescriptorBuilder(DESC_BOOKING_AMY).withStartDate(VALID_START_DATE_BOB).build();
        assertFalse(DESC_BOOKING_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditBookingDescriptorBuilder(DESC_BOOKING_AMY).withEndDate(VALID_END_DATE_BOB).build();
        assertFalse(DESC_BOOKING_AMY.equals(editedAmy));
    }
}

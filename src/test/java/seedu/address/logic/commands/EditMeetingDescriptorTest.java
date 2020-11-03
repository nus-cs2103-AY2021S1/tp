package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CM1111;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CM1112;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CM1112_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_NAME_CM1112_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARTICIPANT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CM1112_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditMeetingDescriptorBuilder;

public class EditMeetingDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditMeetingCommand.EditMeetingDescriptor descriptorWithSameValues =
                new EditMeetingCommand.EditMeetingDescriptor(DESC_CM1111);
        assertTrue(DESC_CM1111.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CM1111.equals(DESC_CM1111));

        // null -> returns false
        assertFalse(DESC_CM1111.equals(null));

        // different types -> returns false
        assertFalse(DESC_CM1111.equals(5));

        // different values -> returns false
        assertFalse(DESC_CM1111.equals(DESC_CM1112));

        // different name -> returns false
        EditMeetingCommand.EditMeetingDescriptor editedCS2111 =
                new EditMeetingDescriptorBuilder(DESC_CM1111)
                        .withMeetingName(VALID_MEETING_NAME_CM1112_MEETING).build();
        assertFalse(DESC_CM1111.equals(editedCS2111));

        // different date -> returns false
        editedCS2111 = new EditMeetingDescriptorBuilder(DESC_CM1111).withDate(VALID_DATE_CM1112_MEETING).build();
        assertFalse(DESC_CM1111.equals(editedCS2111));

        // different email -> returns false
        editedCS2111 = new EditMeetingDescriptorBuilder(DESC_CM1111).withTime(VALID_TIME_CM1112_MEETING).build();
        assertFalse(DESC_CM1111.equals(editedCS2111));

        // different tags -> returns false
        editedCS2111 = new EditMeetingDescriptorBuilder(DESC_CM1111).withMembers(VALID_PARTICIPANT_BOB).build();
        assertFalse(DESC_CM1111.equals(editedCS2111));
    }
}

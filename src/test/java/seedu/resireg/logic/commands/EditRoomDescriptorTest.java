package seedu.resireg.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.DESC_ROOM_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FLOOR_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_TYPE_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_DAMAGED;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.commands.EditRoomCommand.EditRoomDescriptor;
import seedu.resireg.testutil.EditRoomDescriptorBuilder;

public class EditRoomDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditRoomDescriptor descriptorWithSameValues = new EditRoomDescriptor(DESC_ROOM_A);
        assertTrue(DESC_ROOM_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ROOM_A.equals(DESC_ROOM_A));

        // null -> returns false
        assertFalse(DESC_ROOM_A.equals(null));

        // different types -> returns false
        assertFalse(DESC_ROOM_A.equals(5));

        // different values -> returns false
        assertFalse(DESC_ROOM_A.equals(DESC_BOB));

        // different floor -> returns false
        EditRoomDescriptor editedRoomA = new EditRoomDescriptorBuilder(DESC_ROOM_A).withFloor(VALID_FLOOR_B).build();
        assertFalse(DESC_ROOM_A.equals(editedRoomA));

        // different room number -> returns false
        editedRoomA = new EditRoomDescriptorBuilder(DESC_ROOM_A).withRoomNumber(VALID_ROOM_NUMBER_B).build();
        assertFalse(DESC_ROOM_A.equals(editedRoomA));

        // different room type -> returns false
        editedRoomA = new EditRoomDescriptorBuilder(DESC_ROOM_A).withRoomType(VALID_ROOM_TYPE_B).build();
        assertFalse(DESC_ROOM_A.equals(editedRoomA));

        // different tags -> returns false
        editedRoomA = new EditRoomDescriptorBuilder(DESC_ROOM_A).withTags(VALID_TAG_DAMAGED).build();
        assertFalse(DESC_ROOM_A.equals(editedRoomA));
    }
}

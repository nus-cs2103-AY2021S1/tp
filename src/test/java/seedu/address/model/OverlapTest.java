package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_EXPERIMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_CS2000;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_CS2103T;
import static seedu.address.model.util.Overlap.isSameTimeSlot;
import static seedu.address.model.util.Overlap.overlapWithOtherTimeSlots;

import org.junit.jupiter.api.Test;

public class OverlapTest {
    private ModelManager modelManager = new ModelManager();
    @Test
    public void isSameTimeSlot_returnsTrue() {
        //same lesson
        assertTrue(isSameTimeSlot(VALID_LESSON_CS2100, VALID_LESSON_CS2100));
        //same lesson
        assertTrue(isSameTimeSlot(VALID_LESSON_CS2103T, VALID_LESSON_CS2103T));
        //same event
        assertTrue(isSameTimeSlot(VALID_EVENT_EXPERIMENT, VALID_EVENT_EXPERIMENT));
        //different lesson same time slot
        assertTrue(isSameTimeSlot(VALID_LESSON_CS2103T, VALID_LESSON_CS2000));
    }
    @Test
    public void isSameTimeSlot_returnsFalse() {
        //different lessons and events that do not overlap
        assertFalse(isSameTimeSlot(VALID_LESSON_CS2100, VALID_LESSON_CS2103T));
        assertFalse(isSameTimeSlot(VALID_LESSON_CS2100, VALID_EVENT_EXPERIMENT));
        assertFalse(isSameTimeSlot(VALID_LESSON_CS2103T, VALID_EVENT_EXPERIMENT));
    }
    @Test
    public void overlapWithOtherTimeSlots_returnsFalse() {
        //lesson does not exist in model yet
        assertFalse(overlapWithOtherTimeSlots(modelManager, VALID_LESSON_CS2100));
        assertFalse(overlapWithOtherTimeSlots(modelManager, VALID_LESSON_CS2103T));
        assertFalse(overlapWithOtherTimeSlots(modelManager, VALID_LESSON_CS2000));
        //event does not exist in model yet
        assertFalse(overlapWithOtherTimeSlots(modelManager, VALID_EVENT_EXPERIMENT));
    }
    @Test
    public void overlapWithOtherLessons_returnsTrue() {
        modelManager.addTask(VALID_EVENT_EXPERIMENT);
        modelManager.addLesson(VALID_LESSON_CS2100);
        modelManager.addLesson(VALID_LESSON_CS2103T);
        //event exists in model
        assertTrue(overlapWithOtherTimeSlots(modelManager, VALID_EVENT_EXPERIMENT));
        //lesson exists in model
        assertTrue(overlapWithOtherTimeSlots(modelManager, VALID_LESSON_CS2100));
        assertTrue(overlapWithOtherTimeSlots(modelManager, VALID_LESSON_CS2103T));
        //lesson clashes with existing lesson
        assertTrue(overlapWithOtherTimeSlots(modelManager, VALID_LESSON_CS2000));
    }
}

package seedu.address.logic.commands.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BENCH;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SQUATS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXERCISE_NAME_SQUATS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXERCISE_TAG_ARMS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exercise.ExerciseEditCommand.EditExerciseDescriptor;
import seedu.address.testutil.EditExerciseDescriptorBuilder;

public class EditExerciseDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditExerciseDescriptor descriptorWithSameValues = new EditExerciseDescriptor(DESC_BENCH);
        assertTrue(DESC_BENCH.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_BENCH.equals(DESC_BENCH));

        // null -> returns false
        assertFalse(DESC_BENCH.equals(null));

        // different types -> returns false
        assertFalse(DESC_BENCH.equals(5));

        // different values -> returns false
        assertFalse(DESC_BENCH.equals(DESC_SQUATS));

        // different exercise -> returns false
        EditExerciseDescriptor editedBench = new EditExerciseDescriptorBuilder(DESC_BENCH)
                .withName(VALID_EXERCISE_NAME_SQUATS).build();
        assertFalse(DESC_BENCH.equals(editedBench));

        // different tags -> returns false
        editedBench = new EditExerciseDescriptorBuilder(DESC_BENCH).withTags(VALID_EXERCISE_TAG_ARMS).build();
        assertFalse(DESC_BENCH.equals(editedBench));
    }
}

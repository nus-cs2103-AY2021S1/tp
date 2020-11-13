package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.logic.commands.CommandTestUtil.DESC_MC_ONE;
import static seedu.jarvis.logic.commands.CommandTestUtil.DESC_MC_TWO;

import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.edit.EditMasteryCheckCommand;

public class EditMasteryCheckDescriptorTest {

    @Test
    public void equals() {
        EditMasteryCheckCommand.EditMasteryCheckDescriptor descriptorWithSameValues =
                new EditMasteryCheckCommand.EditMasteryCheckDescriptor(DESC_MC_ONE);

        // same values -> returns true
        assertTrue(DESC_MC_ONE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_MC_ONE.equals(DESC_MC_ONE));

        // null -> returns false
        assertFalse(DESC_MC_ONE.equals(null));

        // different types -> returns false
        assertFalse(DESC_MC_ONE.equals(5));

        // different values -> returns false
        assertFalse(DESC_MC_ONE.equals(DESC_MC_TWO));

    }
}

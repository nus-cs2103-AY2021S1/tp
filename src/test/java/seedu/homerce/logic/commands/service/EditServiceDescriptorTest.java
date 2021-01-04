package seedu.homerce.logic.commands.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.logic.commands.CommandTestUtil.DESC_LASH_LIFT;
import static seedu.homerce.logic.commands.CommandTestUtil.DESC_MANICURE;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_AMOUNT_MANICURE;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_DURATION_MANICURE;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_SERVICE_CODE_MANICURE;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_TITLE_MANICURE;

import org.junit.jupiter.api.Test;

import seedu.homerce.testutil.service.EditServiceDescriptorBuilder;

public class EditServiceDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditServiceCommand.EditServiceDescriptor descriptorWithSameValues =
            new EditServiceCommand.EditServiceDescriptor(DESC_LASH_LIFT);
        assertTrue(DESC_LASH_LIFT.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_LASH_LIFT.equals(DESC_LASH_LIFT));

        // null -> returns false
        assertFalse(DESC_LASH_LIFT.equals(null));

        // different types -> returns false
        assertFalse(DESC_LASH_LIFT.equals(5));

        // different services -> returns false
        assertFalse(DESC_LASH_LIFT.equals(DESC_MANICURE));

        // different title -> returns false
        EditServiceCommand.EditServiceDescriptor editedLashLift =
            new EditServiceDescriptorBuilder(DESC_LASH_LIFT).withTitle(VALID_TITLE_MANICURE).build();
        assertFalse(DESC_LASH_LIFT.equals(editedLashLift));

        // different duration -> returns false
        editedLashLift = new EditServiceDescriptorBuilder(DESC_LASH_LIFT).withDuration(VALID_DURATION_MANICURE).build();
        assertFalse(DESC_LASH_LIFT.equals(editedLashLift));

        // different amount -> returns false
        editedLashLift = new EditServiceDescriptorBuilder(DESC_LASH_LIFT).withAmount(VALID_AMOUNT_MANICURE).build();
        assertFalse(DESC_LASH_LIFT.equals(editedLashLift));

        // different service code -> returns false
        editedLashLift = new EditServiceDescriptorBuilder(DESC_LASH_LIFT)
            .withServiceCode(VALID_SERVICE_CODE_MANICURE).build();
        assertFalse(DESC_LASH_LIFT.equals(editedLashLift));
    }
}

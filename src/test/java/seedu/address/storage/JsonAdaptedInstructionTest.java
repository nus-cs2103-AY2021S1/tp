package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedInstruction.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.recipe.Instruction;

public class JsonAdaptedInstructionTest {
    @Test
    public void toModelType_nullInstruction_throwsIllegalValueException() {
        JsonAdaptedInstruction instruction = new JsonAdaptedInstruction((String) null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Instruction.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, instruction::toModelType);
    }
}

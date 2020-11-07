package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InstructionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Instruction(null));
    }

    @Test
    public void isSameInstruction() {
        Instruction instr1 = new Instruction("Cook. Eat.");
        Instruction instr2 = new Instruction("Cook. Eat.");
        assertTrue(instr1.equals(instr2));
    }

    @Test
    public void isValidInstruction() {
        // null instruction
        assertThrows(NullPointerException.class, () -> Instruction.isValidInstruction(null));

        // invalid instruction
        assertFalse(Instruction.isValidInstruction(new Instruction(""))); // empty string
        assertFalse(Instruction.isValidInstruction(new Instruction(" "))); // spaces only

        // valid instruction
        assertTrue(Instruction.isValidInstruction(new Instruction("cook.")));
        assertTrue(Instruction.isValidInstruction(
                new Instruction("add 1 glass of water."))); // alphanumeric characters
        assertTrue(Instruction.isValidInstruction(
                new Instruction("add 1 glass of water. cook for 5 minutes."))); // multiple lines
        assertTrue(Instruction.isValidInstruction(
                new Instruction("Add 1 glass of water."))); // with capital letters
        assertTrue(Instruction.isValidInstruction(new Instruction("Add 1 glass of water, "
                + "salt and cook for 10 minutes. Serve when ready."))); // long names
    }


}

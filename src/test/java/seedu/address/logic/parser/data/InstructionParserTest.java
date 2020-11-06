package seedu.address.logic.parser.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.Instruction;

public class InstructionParserTest {
    private static final String VALID_INSTRUCTION1_STRING = "Cook. Eat.";
    private static final String VALID_INSTRUCTION2_STRING = "Cook.";
    private static final String INVALID_INSTRUCTION = "...";

    @Test
    public void parse_validMultipleInstructions_success() throws ParseException {
        ArrayList<Instruction> expectedInstruction = new ArrayList<>();
        expectedInstruction.add(new Instruction("Cook"));
        expectedInstruction.add(new Instruction("Eat"));
        assertEquals(expectedInstruction, InstructionParser.parse(VALID_INSTRUCTION1_STRING));
    }

    @Test
    public void parse_validSingleInstruction_success() throws ParseException {
        ArrayList<Instruction> expectedInstruction = new ArrayList<>();
        expectedInstruction.add(new Instruction("Cook"));
        assertEquals(expectedInstruction, InstructionParser.parse(VALID_INSTRUCTION2_STRING));
    }

    @Test
    public void parse_invalidInstruction_throwsParseException() throws ParseException {
        assertThrows(ParseException.class, () -> InstructionParser.parse(INVALID_INSTRUCTION));
    }
}

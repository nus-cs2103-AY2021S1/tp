package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.Instruction;

public class InstructionParserTest {
    private static final String VALID_INSTRUCTION1_STRING = "Cook. Eat.";
    private static final String VALID_INSTRUCTION2_STRING = "Cook.";

    @Test
    public void parse_validMultipleInstructions_success() throws ParseException {
        ArrayList<Instruction> expectedInstruction = new ArrayList<>();
        expectedInstruction.add(new Instruction("1) Cook"));
        expectedInstruction.add(new Instruction("2) Eat"));
        assertEquals(expectedInstruction, InstructionParser.parse(VALID_INSTRUCTION1_STRING));
    }

    @Test
    public void parse_validSingleInstruction_success() throws ParseException {
        ArrayList<Instruction> expectedInstruction = new ArrayList<>();
        expectedInstruction.add(new Instruction("1) Cook"));
        assertEquals(expectedInstruction, InstructionParser.parse(VALID_INSTRUCTION2_STRING));
    }
}

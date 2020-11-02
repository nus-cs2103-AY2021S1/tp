package seedu.address.logic.parser.data;

import java.util.ArrayList;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.Instruction;

/**
 * Parse user inputted instructions.
 */
public class InstructionParser {
    /**
     * Parses a String made of 1 or more instructions
     * adds them to an ArrayList of Instruction objects to be returned
     * @param instructionString String of 1 or more instructions
     * @return ArrayList of String objects of the instructions in the parameter
     * @throws ParseException
     */
    public static ArrayList<Instruction> parse(String instructionString) {
        String[] instructionsToken = instructionString.split("\\.");
        ArrayList<Instruction> instructions = new ArrayList<>();
        for (int i = 0; i < instructionsToken.length; i++) {
            String instr = (i + 1) + ") " + instructionsToken[i].trim();
            instructions.add(new Instruction(instr));
        }
        return instructions;
    }
}

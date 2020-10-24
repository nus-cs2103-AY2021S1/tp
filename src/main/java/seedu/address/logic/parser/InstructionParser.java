package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Instruction;

import java.util.ArrayList;

/**
 * Parse user inputted ingredients.
 */
public class InstructionParser {
    /**
     * Parses a String made of 1 or more instructions
     * adds them to an ArrayList of String objects to be returned
     * @param instructionString String of 1 or more instructions
     * @return ArrayList of String objects of the ingredients in the parameter
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

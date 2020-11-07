package seedu.address.model.recipe;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Recipe's instructions in the Wishful Shrinking.
 */
public class Instruction {

    public static final String MESSAGE_CONSTRAINTS =
            "1. Instruction should not be empty \n"
                    + "2. Each instructions can consist of alphanumeric characters, punctuations and spaces. \n"
                    + "3. Instructions are separated by one full stop, no consecutive full stops are allowed.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}\\p{Punct}][\\p{Alnum}\\p{Punct} ]*";

    public final String instructions;

    /**
     * Constructs a {@code Instruction}.
     *
     * @param instructions A valid instruction.
     */
    public Instruction(@JsonProperty("instructions") String instructions) {
        requireNonNull(instructions);
        this.instructions = instructions;
    }

    /**
     *
     * @return Contents of instruction.
     */
    public String getInstruction() {
        return this.instructions;
    }

    /**
     * Returns true if a given string is a valid instruction.
     */
    public static boolean isValidInstruction(Instruction test) {
        return test.getValue().matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return instructions.trim();
    }

    @Override
    public String toString() {
        return instructions;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Instruction // instanceof handles nulls
                && instructions.equals(((Instruction) other).instructions)); // state check
    }

    @Override
    public int hashCode() {
        return instructions.hashCode();
    }

}

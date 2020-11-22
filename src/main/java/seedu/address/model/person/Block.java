package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a Person's Block number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBlock(String)}
 */
public class Block {


    public static final String MESSAGE_CONSTRAINTS =
            "Block should be 1 of the following alphabets";
    public static final String VALIDATION_REGEX = "[a-zA-Z]";
    private static List<String> blockPref = Arrays.asList("A", "B", "C", "D");
    public final String value;

    /**
     * Constructs a {@code Block}.
     *
     * @param block A valid Block number.
     */
    public Block(String block) {
        requireNonNull(block);
        checkArgument(isValidBlock(block), getMessageConstraints());
        value = block.toUpperCase();
    }

    public static List<String> getBlockList() {
        return new ArrayList<>(blockPref);
    }

    public static String getMessageConstraints() {
        StringBuilder blockConstraints = new StringBuilder();
        blockConstraints.append(" ");
        for (String block : blockPref) {
            blockConstraints.append(block);
            blockConstraints.append(", ");
        }
        blockConstraints.setLength(blockConstraints.length() - 2);
        return MESSAGE_CONSTRAINTS + blockConstraints.toString();
    }

    /**
     * Returns true if a given string is a valid Block number.
     */
    public static boolean isValidBlock(String test) {
        return test.matches(VALIDATION_REGEX) && blockPref.contains(test);
    }

    public static void setBlockPref(String[] pref) {
        blockPref = Arrays.asList(pref);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Block // instanceof handles nulls
                && value.equals(((Block) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

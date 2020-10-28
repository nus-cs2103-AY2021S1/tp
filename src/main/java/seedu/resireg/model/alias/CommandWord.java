package seedu.resireg.model.alias;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;

import seedu.resireg.logic.commands.CommandWordEnum;

/**
 * Represents a Student's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCommandWord(String)}
 */
public class CommandWord {

    public static final String MESSAGE_CONSTRAINTS =
        "Invalid command word. Type \"help\" to see a list of command words.";
    public static final String VALIDATION_REGEX = "^[A-Za-z-]+$";

    public final String commandWord;

    /**
     * Constructs a {@code Alias}.
     *
     * @param commandWord A valid command word.
     */
    public CommandWord(String commandWord) {
        requireNonNull(commandWord);
        checkArgument(isValidCommandWord(commandWord), MESSAGE_CONSTRAINTS);
        this.commandWord = commandWord;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidCommandWord(String test) {
        List<CommandWordEnum> commandWords = Arrays.asList(CommandWordEnum.values());
        return test.matches(VALIDATION_REGEX)
            && commandWords.stream().anyMatch(commandWord -> commandWord.toString().equals(test));
    }


    @Override
    public String toString() {
        return commandWord;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CommandWord // instanceof handles nulls
            && commandWord.equals(((CommandWord) other).commandWord)); // state check
    }

    @Override
    public int hashCode() {
        return commandWord.hashCode();
    }

}

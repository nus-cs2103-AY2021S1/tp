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
        "Command words should should be as defined in the User Guide. Type help to know more.";

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
        return commandWords.stream().anyMatch(commandWord -> commandWord.toString().equals(test));
    }


    @Override
    public String toString() {
        return commandWord;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Alias // instanceof handles nulls
            && commandWord.equals(((CommandWord) other).commandWord)); // state check
    }

    @Override
    public int hashCode() {
        return commandWord.hashCode();
    }

}

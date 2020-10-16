package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;

/**
 * Represents a command with hidden internal logic and the ability to reverse the execution of other commands.
 */
public abstract class NegateCommand extends Command {

    public static final String COMMAND_WORD = "un";

    private final String commandWord;
    private final Index targetIndex;

    /**
     * Constructs a {@code NegateCommand} to reverse the execution of other commands.
     *
     * @param suffix the command word of the command to be negated
     * @param targetIndex index of the assignment in the filtered assignment list
     */
    NegateCommand(String suffix, Index targetIndex) {
        requireNonNull(suffix);
        requireNonNull(targetIndex);
        this.commandWord = COMMAND_WORD + suffix;
        this.targetIndex = targetIndex;
    }
}

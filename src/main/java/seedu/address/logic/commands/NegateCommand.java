package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;

/**
 * Represents a command with hidden internal logic and the ability to reverse the execution of other commands.
 */
public abstract class NegateCommand extends Command {

    public static final String COMMAND_WORD = "un";

    private final Index targetIndex;

    /**
     * Constructs a {@code NegateCommand} to reverse the execution of other commands.
     *
     * @param targetIndex index of the assignment in the filtered assignment list
     */
    NegateCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    protected Index getTargetIndex() {
        return this.targetIndex;
    }
}

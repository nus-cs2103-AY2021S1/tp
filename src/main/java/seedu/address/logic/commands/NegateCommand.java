package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;

public abstract class NegateCommand extends Command {

    public static final String COMMAND_WORD = "un";

    private final String commandWord;
    private final Index targetIndex;

    NegateCommand(String suffix, Index targetIndex) {
        requireNonNull(suffix);
        requireNonNull(targetIndex);
        this.commandWord = COMMAND_WORD + suffix;
        this.targetIndex = targetIndex;
    }
}

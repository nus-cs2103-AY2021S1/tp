package seedu.address.logic.commands;

public abstract class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort items of lifebook.\n"
            + "Parameters: [contact|task|clear]\n"
            + "Usage: " + COMMAND_WORD + " [contact|task|clear]\n"
            + "Example: " + COMMAND_WORD + " task";
}

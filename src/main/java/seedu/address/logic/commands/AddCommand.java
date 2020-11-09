package seedu.address.logic.commands;

public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add an item to lifebook.\n"
            + "Parameters: [todo|event|contact]\n"
            + COMMAND_WORD + " [todo|event|contact] /h to see more.";
}

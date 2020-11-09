package seedu.address.logic.commands;

public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all items in events, todos or contacts"
            + "Parameters: [todo|event|contact|task]\n"
            + "Example: " + COMMAND_WORD + " todo";
}

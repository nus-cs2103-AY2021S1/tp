package seedu.address.logic.commands;

public abstract class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edit todos, events or contacts.\n"
            + "Parameters: [todo|contact|event]"
            + COMMAND_WORD + " [todo|event|contact] /h to see more.";
}

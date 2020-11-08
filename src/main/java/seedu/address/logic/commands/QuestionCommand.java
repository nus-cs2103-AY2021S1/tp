package seedu.address.logic.commands;

public abstract class QuestionCommand extends Command {

    public static final String COMMAND_WORD = "question";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds, resolves or deletes a question "
            + "from a student in Reeve.\n\n"
            + "SUPPORTED COMMANDS: add, solve, delete";
}

package seedu.expense.logic.commands;

import seedu.expense.model.Model;

/**
 * Displays graph window.
 */
public class GraphCommand extends Command {

    public static final String COMMAND_WORD = "graph";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays a graphical representation of the user's expenses.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_GRAPH_MESSAGE = "Displaying graph of expenses.";


    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_GRAPH_MESSAGE, false, false, true);
    }

}

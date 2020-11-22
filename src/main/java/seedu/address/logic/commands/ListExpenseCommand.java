package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXPENSES;

import seedu.address.model.Model;

/**
 * Lists all expenses in the expense book to the user.
 */
public class ListExpenseCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all expenses";

    /**
     * Executes the list expense command.
     * @param model {@code Model} which the command should operate on.
     * @return A command result in which all the expenses being listed.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

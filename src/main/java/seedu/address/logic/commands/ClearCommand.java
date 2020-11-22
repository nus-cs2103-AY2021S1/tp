package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.ExpenseBook;
import seedu.address.model.Model;

/**
 * Clears the expense book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Expense book has been cleared!";


    /**
     * Executes the clear command.
     * @param model {@code Model} which the command should operate on.
     * @return A command result in the expenses cleared.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setExpenseBook(new ExpenseBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

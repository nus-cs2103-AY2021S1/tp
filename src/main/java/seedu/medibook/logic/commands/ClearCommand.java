package seedu.medibook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.medibook.model.MediBook;
import seedu.medibook.model.Model;

/**
 * Clears the medi book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Medi book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setMediBook(new MediBook());
        model.setShouldDeleteAllMedicalNotes(true);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

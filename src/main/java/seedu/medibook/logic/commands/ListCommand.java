package seedu.medibook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import seedu.medibook.model.Model;

/**
 * Lists all patients in the medi book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all patients";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        model.resetAccessedPatient();
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, true);
    }
}

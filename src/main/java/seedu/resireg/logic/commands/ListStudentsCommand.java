package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.storage.Storage;

/**
 * Lists all students in ResiReg to the user.
 */
public class ListStudentsCommand extends Command {

    public static final String COMMAND_WORD = "students";

    public static final String MESSAGE_SUCCESS = "Listed all students";

    public static final Help HELP = new Help(COMMAND_WORD, "Lists all students.");

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new ToggleCommandResult(MESSAGE_SUCCESS, TabView.STUDENTS);
    }
}

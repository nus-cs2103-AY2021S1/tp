package seedu.taskmaster.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taskmaster.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.taskmaster.model.Model;

/**
 * Lists all students in the student list to the user.
 */
public class ListStudentsCommand extends Command {
    public static final String COMMAND_WORD = "list-students";

    public static final String MESSAGE_SUCCESS = "Listed all students";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.showStudentList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.semester.Semester;
import seedu.address.model.semester.SemesterManager;

/**
 * Stops the modifying of the module list in a semester.
 * Signifies that the user is done updating the module list
 * and prevents the user from adding, updating or deleting
 * modules in a particular semester.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Stops the adding or updating of modules in the semester stated.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_DONE_SEMESTER_SUCCESS = "You are done updating: %1$s";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        SemesterManager semesterManager = SemesterManager.getInstance();
        Semester semester = semesterManager.getCurrentSemester();

        if (semester == Semester.NA) {
            throw new CommandException(Messages.MESSAGE_INVALID_DONE_COMMAND);
        }

        semesterManager.setCurrentSemester(Semester.NA);
        return new CommandResult(String.format(MESSAGE_DONE_SEMESTER_SUCCESS, semester),
                false, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof DoneCommand; // instanceof handles nulls
    }
}

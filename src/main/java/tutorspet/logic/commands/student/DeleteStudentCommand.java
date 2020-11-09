package tutorspet.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;

import java.util.List;

import tutorspet.commons.core.index.Index;
import tutorspet.logic.commands.Command;
import tutorspet.logic.commands.CommandResult;
import tutorspet.logic.commands.exceptions.CommandException;
import tutorspet.model.Model;
import tutorspet.model.student.Student;

/**
 * Deletes a student identified using it's displayed index from the application.
 */
public class DeleteStudentCommand extends Command {

    public static final String COMMAND_WORD = "delete-student";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted student:\n%1$s.";
    public static final String MESSAGE_COMMIT = "Deleted student: %1$s.";

    private final Index targetIndex;

    public DeleteStudentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getOneBased() > lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToDelete = lastShownList.get(targetIndex.getZeroBased());

        model.deleteStudent(studentToDelete);
        model.commit(String.format(MESSAGE_COMMIT, studentToDelete.getName()));
        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteStudentCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteStudentCommand) other).targetIndex)); // state check
    }
}

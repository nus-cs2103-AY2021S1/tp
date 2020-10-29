package seedu.taskmaster.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_CLASS_PARTICIPATION;

import java.util.List;

import seedu.taskmaster.logic.commands.exceptions.CommandException;
import seedu.taskmaster.model.Model;
import seedu.taskmaster.model.session.exceptions.SessionException;
import seedu.taskmaster.model.student.Student;

public class ParticipationAllCommand extends ParticipationCommand {
    public static final String COMMAND_WORD = "score all";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Scores the class participation of all students in the student list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_CLASS_PARTICIPATION + "CLASS_PARTICIPATION (default: integer between 0 to 10) \n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_CLASS_PARTICIPATION + "7";

    public static final String MESSAGE_MARK_ALL_SUCCESS = "Scored %1$s for all students' participation mark.";

    public ParticipationAllCommand(int score) {
        super(null, score);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        try {
            model.scoreAllStudents(lastShownList, score);
        } catch (SessionException sessionException) {
            throw new CommandException(sessionException.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_MARK_ALL_SUCCESS, score));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ParticipationCommand)) {
            return false;
        }

        // state check
        ParticipationCommand m = (ParticipationCommand) other;

        return (this.score == m.score);
    }
}

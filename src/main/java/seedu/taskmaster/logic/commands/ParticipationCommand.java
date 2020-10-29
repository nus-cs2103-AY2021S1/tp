package seedu.taskmaster.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_CLASS_PARTICIPATION;

import java.util.List;

import seedu.taskmaster.commons.core.Messages;
import seedu.taskmaster.commons.core.index.Index;
import seedu.taskmaster.logic.commands.exceptions.CommandException;
import seedu.taskmaster.model.Model;
import seedu.taskmaster.model.session.exceptions.SessionException;
import seedu.taskmaster.model.student.Student;

/**
 * Marks the participation of a student identified using its displayed index from the student list.
 */
public class ParticipationCommand extends Command {
    public static final String COMMAND_WORD = "score";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Scores the class participation of the student identified by the "
            + "index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_CLASS_PARTICIPATION + "CLASS_PARTICIPATION (default: integer between 0 to 10) \n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_CLASS_PARTICIPATION + "7";

    public static final String MESSAGE_MARK_STUDENT_SUCCESS = "%1$s scored %2$s for class participation";
    protected final int score;
    private final Index targetIndex;

    /**
     * @param targetIndex of the student in the filtered student list to mark
     * @param newScore as the new score of the student
     */
    public ParticipationCommand(Index targetIndex, int newScore) {
        this.targetIndex = targetIndex;
        this.score = newScore;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int index = targetIndex.getZeroBased();
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToScore = lastShownList.get(index);
        try {
            model.scoreStudent(studentToScore, score);
        } catch (SessionException sessionException) {
            throw new CommandException(sessionException.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_MARK_STUDENT_SUCCESS, studentToScore, score));
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

        return (targetIndex.equals(m.targetIndex))
                && (this.score == m.score);
    }
}


package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Student;

/**
 * Edits the participation score of an existing student in Trackr.
 */
public class EditParticipationCommand extends Command {

    public static final String COMMAND_WORD = "editParticipation";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the participation score of the student identified by the index number used in the "
            + "displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_PARTICIPATION + "PARTICIPATION SCORE "
            + "[" + PREFIX_PARTICIPATION + "PARTICIPATION SCORE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PARTICIPATION + "-2";

    public static final String MESSAGE_EDIT_PARTICIPATION_SUCCESS = "%s's participation score is %s/%s";
    public static final String MESSAGE_WRONG_VIEW = "You are currently not in the Student view";

    private final Index index;
    private final String score;

    /**
     * Creates an EditParticipationCommand to update the participation.
     */
    public EditParticipationCommand(Index index, String score) {
        requireNonNull(index);
        requireNonNull(score);
        this.index = index;
        this.score = score;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.isInStudentView()) {
            throw new CommandException(MESSAGE_WRONG_VIEW);
        }

        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEditParticipation = lastShownList.get(index.getZeroBased());
        try {
            studentToEditParticipation.getAttendance().editParticipation(score);
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(
                MESSAGE_EDIT_PARTICIPATION_SUCCESS,
                studentToEditParticipation.getName().fullName,
                studentToEditParticipation.getAttendance().getParticipationScoreAsString(),
                studentToEditParticipation.getAttendance().getMaxParticipationScore())
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditParticipationCommand // instanceof handles nulls
                && index.equals(((EditParticipationCommand) other).index)
                && score.equals(((EditParticipationCommand) other).score));
    }
}

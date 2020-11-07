package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.ParticipationBelowSpecifiedScorePredicate;

public class ParticipationBelowCommand extends Command {
    public static final String COMMAND_WORD = "participationBelow";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows a new list of students whose participation score falls below the upper bound. " + "\n"
            + "Parameters: UPPER_BOUND (must be one positive integer) "
            + "Example: " + COMMAND_WORD + " 5 ";

    public static final String MESSAGE_PARTICIPATION_BELOW_SUCCESS = "Here are the students whose "
            + "participation are below %s";
    public static final String MESSAGE_WRONG_VIEW = "You are currently not in the Student view";

    private final ParticipationBelowSpecifiedScorePredicate predicate;
    private final int upperBound;

    /**
     * Creates an AddAttendanceCommand to update the attendance.
     */
    public ParticipationBelowCommand(ParticipationBelowSpecifiedScorePredicate predicate, int upperBound) {
        this.predicate = predicate;
        this.upperBound = upperBound;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.isInStudentView()) {
            throw new CommandException(MESSAGE_WRONG_VIEW);
        }

        requireNonNull(model);

        model.updateFilteredStudentList(predicate);
        return new CommandResult(String.format(
                MESSAGE_PARTICIPATION_BELOW_SUCCESS,
                upperBound)
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParticipationBelowCommand // instanceof handles nulls
                && predicate.equals(((ParticipationBelowCommand) other).predicate)); // state check
    }
}

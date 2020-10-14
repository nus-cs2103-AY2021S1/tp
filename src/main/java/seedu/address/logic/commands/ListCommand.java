package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Deadline;

/**
 * Lists all assignments in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the tasks in the next [INDEX] number of weeks.\n"
            + "If index keyed in exceeds range or if no index is keyed in, the entire list of tasks is displayed.\n"
            + "Parameters: [INDEX] (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Listed your assignments";

    private final Index numberOfWeeks;

    public ListCommand(Index numberOfWeeks) {
        this.numberOfWeeks = numberOfWeeks;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Predicate<Assignment> showLimitedAssignment = assignment -> {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(Deadline.DEADLINE_DATE_TIME_FORMAT)
                    .withResolverStyle(ResolverStyle.STRICT);
            String deadlineToParse = assignment.getDeadline().value;
            LocalDateTime currentDateAndTime = LocalDateTime.now();
            LocalDateTime lastDateAndTime = currentDateAndTime.plusWeeks(numberOfWeeks.getZeroBased());
            LocalDateTime parsedDeadline = LocalDateTime.parse(deadlineToParse, inputFormat);

            return parsedDeadline.isAfter(currentDateAndTime) && parsedDeadline.isBefore(lastDateAndTime);
        };

        if (numberOfWeeks.getZeroBased() != 0) {
            model.updateFilteredAssignmentList(showLimitedAssignment);
        } else {
            model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);
        }

        return new CommandResult(ListCommand.MESSAGE_SUCCESS);
    }
}

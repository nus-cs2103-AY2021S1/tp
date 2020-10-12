package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
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
            + ": Lists the tasks within the next index number of weeks.\n"
            + "If index keyed in exceeds range of list or if no index is keyed in, the entire list is displayed.\n"
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
            LocalDateTime currentDateAndTime = LocalDateTime.now();
            LocalDateTime lastDateAndTime = currentDateAndTime.plusWeeks(numberOfWeeks.getZeroBased());
            String toParse = assignment.getDeadline().value;
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(Deadline.DEADLINE_DATE_TIME_FORMAT)
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalDateTime parsedDeadline = LocalDateTime.parse(toParse, inputFormat);

            return parsedDeadline.isAfter(currentDateAndTime) && parsedDeadline.isBefore(lastDateAndTime);
        };

        if (numberOfWeeks.getZeroBased() != 0) {
            model.updateFilteredAssignmentList(showLimitedAssignment);
        } else {
            model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, model.getFilteredAssignmentList().size()));
    }
}

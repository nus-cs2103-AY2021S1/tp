package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.assignment.Deadline;
import seedu.address.model.assignment.Task;

/**
 * Lists all tasks in ProductiveNUS to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the tasks in the next [INDEX] number of weeks.\n"
            + "If no index is keyed in, all your tasks will be displayed.\n"
            + "Parameters: [INDEX] (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Listed your tasks";

    private final Index numberOfWeeks;

    public ListCommand(Index numberOfWeeks) {
        this.numberOfWeeks = numberOfWeeks;
    }

    private Predicate<Task> showLimitedTasks() {
        return task -> {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(Deadline.DEADLINE_DATE_TIME_FORMAT)
                    .withResolverStyle(ResolverStyle.STRICT);
            String dateAndTimeToParse = task.getTime().value;
            LocalDateTime currentDateAndTime = LocalDateTime.now();
            LocalDateTime lastDateAndTime = currentDateAndTime.plusWeeks(numberOfWeeks.getZeroBased());
            LocalDateTime parsedDateAndTime = LocalDateTime.parse(dateAndTimeToParse, inputFormat);

            boolean isAfterCurrentDateAndTime = parsedDateAndTime.isAfter(currentDateAndTime);
            boolean isBeforeLastDateAndTime = parsedDateAndTime.isBefore(lastDateAndTime);

            return isAfterCurrentDateAndTime && isBeforeLastDateAndTime;
        };
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (numberOfWeeks.getZeroBased() != 0) {
            model.updateFilteredTaskList(showLimitedTasks());
        } else {
            model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        }
        return new CommandResult(ListCommand.MESSAGE_SUCCESS);
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_TYPE;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.information.Job;

/**
 * Sorts the list of jobs using the specified comparator.
 */
public class SortJobCommand extends Command {

    public static final String COMMAND_WORD = "sort job";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the list of jobs in the address book"
            + "according to the specified order given by user input.\n"
            + "Parameters: "
            + PREFIX_SORT_TYPE + "FIELD_TO_BE_SORTED "
            + PREFIX_SORT_ORDER + "ORDER_TO_SORT\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORT_TYPE + "v "
            + PREFIX_SORT_ORDER + "asc ";

    public static final String MESSAGE_SUCCESS = "Successfully sorted jobs ";

    public static final String MESSAGE_SORT_JOB_TYPE_INVALID = "Only job title, company name, vacancy and priority "
            + "can be sorted for jobs. "
            + "Input for field to be sorted must be either n, c, v or pr.";

    private final Comparator<Job> comparator;
    private final String sortMessage;

    /**
     * Constructor for SortJobCommand. Checks the order specified and forms the appropriate comparator.
     */
    public SortJobCommand(Comparator<Job> comparator, Boolean isAscending) {
        requireNonNull(comparator);
        if (!isAscending) {
            this.sortMessage = comparator.toString() + "in descending order.";
            this.comparator = comparator.reversed();
        } else {
            this.sortMessage = comparator.toString() + "in ascending order.";
            this.comparator = comparator;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateSortedJobList(comparator);
        return new CommandResult(MESSAGE_SUCCESS + this.sortMessage, Job.TAB_NAME);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortJobCommand)) {
            return false;
        }

        // state check
        SortJobCommand s = (SortJobCommand) other;
        return comparator.equals(s.comparator);
    }
}

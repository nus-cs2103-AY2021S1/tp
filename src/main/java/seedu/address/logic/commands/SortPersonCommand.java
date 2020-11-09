package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_TYPE;

import java.util.Comparator;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.information.Person;

/**
 * Sorts the persons using the specified comparator.
 */
public class SortPersonCommand extends Command {

    public static final String COMMAND_WORD = "sort can";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the candidates in the address book"
            + "according to the specified order given by the user input. \n"
            + "Parameters: "
            + PREFIX_SORT_TYPE + "FIELD_TO_BE_SORTED "
            + PREFIX_SORT_ORDER + "ORDER_TO_SORT\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORT_TYPE + "exp "
            + PREFIX_SORT_ORDER + "asc ";

    public static final String MESSAGE_SUCCESS = "Successfully sorted list of candidates ";

    public static final String MESSAGE_SORT_TYPE_INVALID = "Invalid Sort Type.\n"
            + "Sort type must be one of n, exp, sal, bl or doa";

    private static final Logger logger = LogsCenter.getLogger(SortPersonCommand.class);

    private final Comparator<Person> comparator;
    private final String sortMessage;

    /**
     * Constructor for SortPersonCommand. Checks the order of sort required
     * and producers the appropriate comparator.
     */
    public SortPersonCommand(Comparator<Person> comparator, Boolean isAscending) {
        assert (comparator != null);
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
        model.updateSortedPersonList(comparator);
        logger.info("Sorting People");
        return new CommandResult(MESSAGE_SUCCESS + this.sortMessage, Person.TAB_NAME);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortPersonCommand)) {
            return false;
        }

        // state check
        SortPersonCommand s = (SortPersonCommand) other;
        return comparator.equals(s.comparator);
    }
}

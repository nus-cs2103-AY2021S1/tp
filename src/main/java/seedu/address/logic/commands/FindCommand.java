package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;

/**
 * Finds and lists all assignments in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all assignments"
            + " by either its NAME, MODULE CODE, DEADLINE or PRIORITY. \n"
            + "DEADLINE keywords are in the format dd-MM-yyyy or HHmm, which allows finding of assignments by date "
            + "and time separately.\n"
            + "Parameters:\n"
            + PREFIX_NAME + "NAME_OF_ASSIGNMENT [MORE NAME_OF_ASSIGNMENT]\n"
            + PREFIX_MODULE_CODE + "MODULE_CODE [MORE MODULE_CODE]\n"
            + PREFIX_DEADLINE + " DATE_OR_TIME_OF_DEADLINE [MORE DATE_OR_TIME_OF_DEADLINE]\n"
            + PREFIX_PRIORITY + " PRIORITY [MORE PRIORITY]\n"
            + "Example: " + COMMAND_WORD + " d/1200 24-10-2020\n"
            + "The example above finds all assignments due on 24-10-2020 (regardless of time), and "
            + "all assignments due at 1200 (regardless of date)";

    public static final String INVALID_DATE_OR_TIME_MESSAGE =
            "Deadline field should have date or time keywords in the format dd-MM-yyyy and HHmm respectively.\n"
             + "It should not be left empty.";

    public static final String MORE_THAN_ONE_PREFIX_MESSAGE =
            "Multiple assignment fields detected. Finding is done one field at a time.\n"
             + "Fields and its prefix: n/ to find by name, d/ to find by date or time, \n"
             + "p/ to find by priority and mod/ to find by module code.";

    private final Predicate<Assignment> predicate;

    /**
     * Constructor for find command, which takes in a predicate.
     * @param predicate Predicate to filter assignments.
     */
    public FindCommand(Predicate<Assignment> predicate) {
        assert predicate != null;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAssignmentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, model.getFilteredAssignmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}

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
 * Finds and lists all assignments in ProductiveNus whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = "Formats: \n"
            + COMMAND_WORD + " " + PREFIX_NAME + "NAME_OF_ASSIGNMENT [MORE_NAME_OF_ASSIGNMENT]\n"
            + COMMAND_WORD + " " + PREFIX_MODULE_CODE + "MODULE_CODE [MORE_MODULE_CODE]\n"
            + COMMAND_WORD + " " + PREFIX_DEADLINE + " DATE_OR_TIME_OF_DEADLINE [MORE_DATE_OR_TIME_OF_DEADLINE]\n"
            + COMMAND_WORD + " " + PREFIX_PRIORITY + " PRIORITY [MORE_PRIORITY]";

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

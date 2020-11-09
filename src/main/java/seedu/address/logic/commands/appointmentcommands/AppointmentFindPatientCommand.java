package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.filters.appointmentfilters.SearchPatientFilter;


/**
 * Finds and lists all appointments in address book whose patient name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class AppointmentFindPatientCommand extends Command {

    public static final String COMMAND_WORD = "a-find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all appointments with patients whose names "
            + "contain any of the specified keywords (case-insensitive) and displays them as a list with index "
            + "numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final SearchPatientFilter predicate;

    public AppointmentFindPatientCommand(SearchPatientFilter predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                        model.getFilteredAppointmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentFindPatientCommand // instanceof handles nulls
                && predicate.equals(((AppointmentFindPatientCommand) other).predicate)); // state check
    }
}

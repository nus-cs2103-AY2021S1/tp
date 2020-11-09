package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.filters.appointmentfilters.SearchAppointmentWeekFilter;

/**
 * Finds and lists all the appointments this week in the addressbook.
 */
public class AppointmentWeekCommand extends Command {

    public static final String COMMAND_WORD = "a-upcoming";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all the appointments this week. "
            + "\n"
            + "Example: " + COMMAND_WORD;

    private final SearchAppointmentWeekFilter predicate;

    public AppointmentWeekCommand(SearchAppointmentWeekFilter predicate) {
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
                || (other instanceof AppointmentWeekCommand // instanceof handles nulls
                && predicate.equals(((AppointmentWeekCommand) other).predicate)); // state check
    }
}

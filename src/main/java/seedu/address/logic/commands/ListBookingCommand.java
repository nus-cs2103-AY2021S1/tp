package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BOOKINGS;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.LogicManager;
import seedu.address.model.Model;

public class ListBookingCommand extends Command {

    public static final String COMMAND_WORD = "listBooking";

    public static final String MESSAGE_SUCCESS = "Listed all bookings";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        logger.info("=============================[ Executing listBooking ]===========================");
        model.updateFilteredBookingList(PREDICATE_SHOW_ALL_BOOKINGS);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKINGS_LISTED_OVERVIEW, model.getFilteredBookingList().size()),
                false, false, false, true);
    }
}

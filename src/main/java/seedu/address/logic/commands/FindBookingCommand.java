package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_ID_MISSING;
import static seedu.address.commons.core.Messages.MESSAGE_ROOM_ID_MISSING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IS_ACTIVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Finds and list the active booking in booking book which roomId corresponding input.
 */
public class FindBookingCommand extends Command {

    public static final String COMMAND_WORD = "findBooking";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the booking(s) "
            + "with one or more parameters including personId, roomId, start date, end date, isArchived state. \n"
            + "There must at least be 1 parameter. Dates should be in the format yyyy-MM-dd. \n"
            + "Parameters:"
            + "[" + PREFIX_ROOM_ID + "ROOM_ID] "
            + "[" + PREFIX_PERSON_ID + "PERSON_ID] "
            + "[" + PREFIX_START_DATE + "START_DATE] "
            + "[" + PREFIX_END_DATE + "END_DATE] "
            + "[" + PREFIX_IS_ACTIVE + "IS_ARCHIVED] (\"true\" or \"false\")\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROOM_ID + "2104 "
            + PREFIX_PERSON_ID + "2 "
            + PREFIX_START_DATE + "2020-09-14 "
            + PREFIX_IS_ACTIVE + "false";


    private final Optional<Integer> roomId;

    private final Optional<Integer> personId;

    private final List<Predicate<Booking>> predicates;

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    /**
     * Constructs a FindBookingCommand with list of predicates.
     * Optional roomId and personId is used to check if such room and person is present in roomBook and personBook.
     *
     * @param predicates a list of predicates.
     * @param roomId an optional roomId if roomId is provided to filter the booking.
     * @param personId an optional personId if personId is provided to filter the booking.
     */
    public FindBookingCommand(List<Predicate<Booking>> predicates, Optional<Integer> roomId,
                              Optional<Integer> personId) {
        this.predicates = predicates;
        this.roomId = roomId;
        this.personId = personId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("=============================[ Executing findBooking ]===========================");
        requireNonNull(model);
        if (roomId.isPresent() && !model.hasRoom(roomId.get())) {
            logger.warning("Missing roomId");
            throw new CommandException(MESSAGE_ROOM_ID_MISSING);
        }

        if (personId.isPresent() && !model.hasPersonWithId(personId.get())) {
            logger.warning("Missing personId");
            throw new CommandException(MESSAGE_PERSON_ID_MISSING);
        }

        Predicate<Booking> predicate = predicates.stream().reduce(x -> true, Predicate::and);
        model.updateFilteredBookingList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKINGS_LISTED_OVERVIEW, model.getFilteredBookingList().size()),
                false, false, false, true);
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindBookingCommand)) {
            return false;
        }

        FindBookingCommand otherFindBookingCommand = (FindBookingCommand) other;

        return predicates.equals(otherFindBookingCommand.predicates)
                && roomId.equals(otherFindBookingCommand.roomId)
                && personId.equals(otherFindBookingCommand.personId);

    }
}

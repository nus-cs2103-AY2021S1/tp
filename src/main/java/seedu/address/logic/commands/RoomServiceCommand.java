package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_SERVICE_TYPE;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.roomservice.RoomService;
import seedu.address.model.roomservice.RoomServiceType;


/**
 * Command to order room service for a particular booking
 */
public class RoomServiceCommand extends Command {

    public static final String COMMAND_WORD = "orderRoomService";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Orders room service for booking.\n"
            + "Valid room service types are: "
            + RoomServiceType.getAllNames()
            + "\n"
            + "Parameters: "
            + PREFIX_BOOKING_ID + "BOOKING_ID "
            + PREFIX_ROOM_SERVICE_TYPE + "TYPE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_BOOKING_ID + "3 "
            + PREFIX_ROOM_SERVICE_TYPE + "DINING";

    public static final String MESSAGE_SUCCESS = "Ordered room service:\n%s";
    public static final String MESSAGE_BOOKING_MISSING = "No booking with id %s can be found.";
    public static final String MESSAGE_BOOKING_INVALID = "Booking with id %s is archived.";

    private final RoomService roomService;
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    /**
     * Creates a RoomServiceCommand
     */
    public RoomServiceCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Orders room service given that the booking id exists and the booking is active
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info(String.format("Ordering room service: %s", roomService));

        if (!model.hasBookingWithId(roomService.getBookingId())) {
            throw new CommandException(String.format(MESSAGE_BOOKING_MISSING, roomService.getBookingId()));
        }

        if (!model.getBookingWithId(roomService.getBookingId()).isActive()) {
            throw new CommandException(String.format(MESSAGE_BOOKING_INVALID, roomService.getBookingId()));
        }

        model.addRoomService(roomService);
        assert model.getRoomServicesForBooking(roomService.getBookingId()).size() > 0;

        logger.info(String.format("Ordered room service: %s", roomService));
        return new CommandResult(String.format(MESSAGE_SUCCESS, roomService), false, false,
                false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomServiceCommand // instanceof handles nulls
                && roomService.equals(((RoomServiceCommand) other).roomService));
    }
}

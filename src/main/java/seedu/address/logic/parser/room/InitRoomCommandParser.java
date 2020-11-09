package seedu.address.logic.parser.room;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.room.InitRoomCommand.MESSAGE_USAGE;

import seedu.address.logic.commands.room.InitRoomCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input and creates new AddRoomsCommand object
 */
//@@author itssodium
public class InitRoomCommandParser implements Parser<InitRoomCommand> {
    public static final String NUMBER_OF_ROOMS_GIVEN_IN_DIGITS = "Please give the number of digits in integers\n"
            + "Example: initRoom 200";

    /**
     * Parses the given {@code String} of arguments in the context of the AddPatientCommand
     * and returns an AddRoomsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InitRoomCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
            //if user does not give number of room
        }
        int numberOfRooms = -1; //used so that it can be used to test for NumberFormatException
        try {
            numberOfRooms = Integer.parseInt(trimmedArgs);
        } catch (NumberFormatException nfe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NUMBER_OF_ROOMS_GIVEN_IN_DIGITS));
            //if user does not give number of rooms in terms of integers but gives in terms of words
        }
        return new InitRoomCommand(numberOfRooms);
    }

}

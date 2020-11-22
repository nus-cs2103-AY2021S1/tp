package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.EditRoomCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditRoomCommandParser object.
 */
public class EditRoomCommandParser implements Parser<EditRoomCommand> {
    private static final int MIN_ROOM_INDEX = 0;
    private static final int MAX_ROOM_INDEX = 1;
    private static final int ARGS_LEN = 2;

    /**
     * Parses the given {@code String} of arguments in the context of the EditRoomCommand
     * and returns a EditRoomCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditRoomCommand parse(String args) throws ParseException {
        try {
            // E.g. edit-room-range 1 4
            String[] split = args.stripLeading().split(" "); // [1, 4]
            if (split.length != ARGS_LEN) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditRoomCommand.MESSAGE_USAGE));
            }
            int minRoomSettings = Integer.parseInt(split[MIN_ROOM_INDEX]);
            int maxRoomSettings = Integer.parseInt(split[MAX_ROOM_INDEX]);

            return new EditRoomCommand(minRoomSettings, maxRoomSettings);
        } catch (NumberFormatException e) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditRoomCommand.MESSAGE_USAGE), e);
        }
    }
}

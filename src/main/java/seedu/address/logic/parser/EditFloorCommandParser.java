package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.EditFloorCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditFloorCommandParser object.
 */
public class EditFloorCommandParser implements Parser<EditFloorCommand> {
    private static final int MIN_FLOOR_INDEX = 0;
    private static final int MAX_FLOOR_INDEX = 1;
    private static final int ARGS_LEN = 2;

    /**
     * Parses the given {@code String} of arguments in the context of the EditFloorCommand
     * and returns a EditFloorCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditFloorCommand parse(String args) throws ParseException {
        try {
            // E.g. edit-floor-range 1 4
            String[] split = args.stripLeading().split(" "); // [1, 4]
            if (split.length != ARGS_LEN) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditFloorCommand.MESSAGE_USAGE));
            }
            int minFloorSettings = Integer.parseInt(split[MIN_FLOOR_INDEX]);
            int maxFloorSettings = Integer.parseInt(split[MAX_FLOOR_INDEX]);

            return new EditFloorCommand(minFloorSettings, maxFloorSettings);
        } catch (NumberFormatException e) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditFloorCommand.MESSAGE_USAGE), e);
        }
    }
}

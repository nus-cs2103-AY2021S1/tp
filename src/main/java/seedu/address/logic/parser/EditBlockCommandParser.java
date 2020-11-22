package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.EditBlockCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditBlockCommandParser object.
 */
public class EditBlockCommandParser implements Parser<EditBlockCommand> {
    private static final int FIRST_BLOCK_INDEX = 0;
    private static final int LAST_BLOCK_INDEX = 1;
    private static final int ARGS_LEN = 2;

    /**
     * Parses the given {@code String} of arguments in the context of the EditBlockCommand
     * and returns a EditBlockCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditBlockCommand parse(String args) throws ParseException {
        // E.g. edit-block-range A D
        String[] split = args.stripLeading().split(" "); // [A, D]
        if (split.length != ARGS_LEN) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBlockCommand.MESSAGE_USAGE));
        }
        String firstBlockLetter = split[FIRST_BLOCK_INDEX];
        String lastBlockLetter = split[LAST_BLOCK_INDEX];

        // check if the block letter contains only a single character
        if (firstBlockLetter.length() > 1 || lastBlockLetter.length() > 1) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBlockCommand.MESSAGE_USAGE));
        }

        return new EditBlockCommand(firstBlockLetter.charAt(0), lastBlockLetter.charAt(0));
    }
}

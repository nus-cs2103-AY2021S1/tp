package seedu.tr4cker.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.DeleteCommand;
import seedu.tr4cker.logic.commands.DeleteExpiredCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] trimmedArgsTokens = args.trim().split(" ");
        int lengthArgsTokens = trimmedArgsTokens.length;
        assert lengthArgsTokens > 0;

        boolean isDeleteExpiredTask = lengthArgsTokens == 2 && trimmedArgsTokens[0].equals("expired");

        if (!(lengthArgsTokens == 1 || isDeleteExpiredTask)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        try {
            if (isDeleteExpiredTask) {
                Index index = ParserUtil.parseTaskIndex(trimmedArgsTokens[1], DeleteCommand.MESSAGE_USAGE);
                return new DeleteExpiredCommand(index);
            } else {
                Index index = ParserUtil.parseTaskIndex(trimmedArgsTokens[0], DeleteExpiredCommand.MESSAGE_USAGE);
                return new DeleteCommand(index);
            }
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage());
        }
    }
}

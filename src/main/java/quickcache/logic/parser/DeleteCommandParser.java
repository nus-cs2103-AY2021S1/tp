package quickcache.logic.parser;

import quickcache.commons.core.Messages;
import quickcache.commons.core.index.Index;
import quickcache.logic.commands.DeleteCommand;
import quickcache.logic.parser.exceptions.ParseException;

import java.util.List;

import static quickcache.logic.parser.CliSyntax.PREFIX_QUESTION;
import static quickcache.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (isTagPrefixPresent(argMultimap)) {
            if (!argMultimap.getPreamble().isEmpty()) {
                // there shouldn't be a preamble together with the tag prefix
                // TODO: make sure the exception message is correct
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
            }
            return new DeleteCommand(argMultimap.getAllValues(PREFIX_TAG));
        } else {
            try {
                Index index = ParserUtil.parseIndex(args);
                return new DeleteCommand(index);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
            }
        }
    }

    /**
     * Returns true if the tag prefix has at least one value.
     */
    private static boolean isTagPrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_TAG).isPresent();
    }

}

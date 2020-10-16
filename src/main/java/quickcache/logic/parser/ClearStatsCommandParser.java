package quickcache.logic.parser;

import quickcache.commons.core.Messages;
import quickcache.commons.core.index.Index;
import quickcache.logic.commands.ClearStatsCommand;
import quickcache.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClearStatsCommand object
 */
public class ClearStatsCommandParser implements Parser<ClearStatsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearStatsCommand
     * and returns a ClearStatsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearStatsCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ClearStatsCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ClearStatsCommand.MESSAGE_USAGE), pe);
        }
    }

}

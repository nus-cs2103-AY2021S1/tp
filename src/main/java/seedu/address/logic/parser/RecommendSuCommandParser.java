package seedu.address.logic.parser;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.RecommendSuCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RecommendSUCommand object
 */
public class RecommendSuCommandParser implements Parser<RecommendSuCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RecommendSUCommand
     * and returns a RecommendSUCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public RecommendSuCommand parse(String userInput) throws ParseException {
        String trimmedInput = userInput.trim();
        if (!trimmedInput.equals("")) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RecommendSuCommand.MESSAGE_USAGE)
            );
        }
        return new RecommendSuCommand();
    }
}

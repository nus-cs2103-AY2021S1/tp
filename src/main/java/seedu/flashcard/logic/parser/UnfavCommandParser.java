package seedu.flashcard.logic.parser;

import static seedu.flashcard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.flashcard.commons.core.index.Index;
import seedu.flashcard.logic.commands.UnfavCommand;
import seedu.flashcard.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnfavCommand object.
 */
public class UnfavCommandParser implements Parser<UnfavCommand> {
    @Override
    public UnfavCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnfavCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnfavCommand.MESSAGE_USAGE), pe);
        }
    }
}

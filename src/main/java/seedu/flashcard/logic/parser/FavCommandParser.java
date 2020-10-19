package seedu.flashcard.logic.parser;

import seedu.flashcard.commons.core.index.Index;
import seedu.flashcard.logic.commands.DeleteCommand;
import seedu.flashcard.logic.commands.FavCommand;
import seedu.flashcard.logic.parser.exceptions.ParseException;

import static seedu.flashcard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class FavCommandParser implements Parser<FavCommand> {
    @Override
    public FavCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new FavCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavCommand.MESSAGE_USAGE), pe);
        }
    }
}

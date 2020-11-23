package seedu.address.logic.parser.deck;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.deck.SelectDeckCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class SelectDeckCommandParser implements Parser<SelectDeckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SelectDeckCommand
     * and returns a SelectDeckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SelectDeckCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SelectDeckCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectDeckCommand.MESSAGE_USAGE), pe);
        }
    }
}

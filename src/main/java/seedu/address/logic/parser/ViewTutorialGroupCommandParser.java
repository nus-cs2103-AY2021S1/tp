package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ViewTutorialGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import javax.swing.text.View;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ViewTutorialGroupCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewTutorialGroupCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewTutorialGroupCommand(index);
        } catch (ParseException pe) {
            throw new ParseException("INVALID");
        }
    }
}

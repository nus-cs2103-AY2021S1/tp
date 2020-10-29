package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewTutorialGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewTutorialGroupCommandParser implements Parser<ViewTutorialGroupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewTutorialGroupCommand
     * and returns a ViewTutorialGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewTutorialGroupCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewTutorialGroupCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTutorialGroupCommand.MESSAGE_USAGE), pe);
        }
    }
}

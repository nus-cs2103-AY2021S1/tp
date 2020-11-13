package seedu.address.logic.parser.deliverable;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.deliverable.MarkUndoneCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

public class MarkUndoneCommandParser implements Parser<MarkUndoneCommand> {
    @Override
    public MarkUndoneCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkUndoneCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkUndoneCommand.MESSAGE_USAGE), pe);
        }
    }
}

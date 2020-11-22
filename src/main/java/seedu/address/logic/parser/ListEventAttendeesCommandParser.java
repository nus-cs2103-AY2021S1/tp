package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListEventAttendeesCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListEventAttendeesCommandParser implements Parser<ListEventAttendeesCommand> {
    @Override
    public ListEventAttendeesCommand parse(String userInput) throws ParseException {
        try {
            Index eventIndex = ParserUtil.parseIndex(userInput);
            return new ListEventAttendeesCommand(eventIndex);
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListEventAttendeesCommand.MESSAGE_USAGE), e);
        }
    }
}

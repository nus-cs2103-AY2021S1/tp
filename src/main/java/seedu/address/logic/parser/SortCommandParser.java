package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.sort.SortClearCommand;
import seedu.address.logic.commands.sort.SortContactCommand;
import seedu.address.logic.commands.sort.SortTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SortCommandParser implements Parser<SortCommand> {
    @Override
    public SortCommand parse(String args) throws ParseException {
        if (args.trim().equals("task")) {
            return new SortTaskCommand();
        } else if (args.trim().equals("contact")) {
            return new SortContactCommand();
        } else if (args.trim().equals("clear")) {
            return new SortClearCommand();
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }
}

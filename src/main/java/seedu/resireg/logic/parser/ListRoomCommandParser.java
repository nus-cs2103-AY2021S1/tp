package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.resireg.logic.commands.ListRoomCommand;
import seedu.resireg.logic.commands.ListRoomFilter;
import seedu.resireg.logic.parser.exceptions.ParseException;

public class ListRoomCommandParser implements Parser<ListRoomCommand> {
    @Override
    public ListRoomCommand parse(String args) throws ParseException {
        String trimmedArgs = args.strip();

        if (trimmedArgs.isEmpty()) {
            return new ListRoomCommand(ListRoomFilter.ALL);
        }
        if (!trimmedArgs.startsWith("--")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRoomCommand.MESSAGE_USAGE));
        }
        trimmedArgs = trimmedArgs.substring(2); // "strip away the leading "--"
        if (trimmedArgs.equalsIgnoreCase(ListRoomCommand.COMMAND_VACANT_FLAG)) {
            return new ListRoomCommand(ListRoomFilter.VACANT);
        }
        if (trimmedArgs.equalsIgnoreCase(ListRoomCommand.COMMAND_ALLOCATED_FLAG)) {
            return new ListRoomCommand(ListRoomFilter.ALLOCATED);
        }
        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRoomCommand.MESSAGE_USAGE));
    }
}

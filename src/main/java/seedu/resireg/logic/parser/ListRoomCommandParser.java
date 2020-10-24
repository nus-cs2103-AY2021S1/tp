package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_KEYWORD;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_FLOOR;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;

import seedu.resireg.logic.commands.ListRoomCommand;
import seedu.resireg.logic.commands.ListRoomCommand.RoomFilter;
import seedu.resireg.logic.parser.exceptions.ParseException;

public class ListRoomCommandParser implements Parser<ListRoomCommand> {
    private static final String MESSAGE_INVALID_COMMAND = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            ListRoomCommand.HELP.getFullMessage());

    @Override
    public ListRoomCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ROOM_FLOOR,
                PREFIX_ROOM_NUMBER, PREFIX_ROOM_TYPE, PREFIX_KEYWORD);
        RoomFilter filter = new RoomFilter();

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(MESSAGE_INVALID_COMMAND);
        }

        // don't allow both --vacant and --allocated to be given
        if (argMultimap.getAllValues(PREFIX_KEYWORD).size() > 1) {
            throw new ParseException(MESSAGE_INVALID_COMMAND);
        }
        // handle --vacant and --allocated
        if (argMultimap.getValue(PREFIX_KEYWORD).isPresent()) {
            switch (argMultimap.getValue(PREFIX_KEYWORD).get()) {
            case ListRoomCommand.COMMAND_ALLOCATED_FLAG:
                filter.onlyAllocated();
                break;
            case ListRoomCommand.COMMAND_VACANT_FLAG:
                filter.onlyVacant();
                break;
            default:
                throw new ParseException(MESSAGE_INVALID_COMMAND);
            }
        }

        if (argMultimap.getValue(PREFIX_ROOM_FLOOR).isPresent()) {
            filter.addFloors(ParserUtil.parseCollection(argMultimap.getAllValues(PREFIX_ROOM_FLOOR),
                    ParserUtil::parseFloor));
        }
        if (argMultimap.getValue(PREFIX_ROOM_NUMBER).isPresent()) {
            filter.addRoomNumbers(ParserUtil.parseCollection(argMultimap.getAllValues(PREFIX_ROOM_NUMBER),
                    ParserUtil::parseRoomNumber));
        }
        if (argMultimap.getValue(PREFIX_ROOM_TYPE).isPresent()) {
            filter.addRoomTypes(ParserUtil.parseCollection(argMultimap.getAllValues(PREFIX_ROOM_TYPE),
                    ParserUtil::parseRoomType));
        }

        return new ListRoomCommand(filter);
    }
}

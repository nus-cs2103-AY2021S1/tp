package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_FLOOR;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.resireg.logic.commands.AddRoomCommand;
import seedu.resireg.logic.parser.exceptions.ParseException;
import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.model.tag.Tag;

public class AddRoomCommandParser implements Parser<AddRoomCommand> {
    @Override
    public AddRoomCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROOM_FLOOR, PREFIX_ROOM_NUMBER, PREFIX_ROOM_TYPE, PREFIX_TAG);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_ROOM_FLOOR, PREFIX_ROOM_NUMBER, PREFIX_ROOM_TYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddRoomCommand.HELP.getFullMessage()));
        }

        Floor floor = ParserUtil.parseFloor(argMultimap.getValue(PREFIX_ROOM_FLOOR).get());
        RoomNumber roomNumber = ParserUtil.parseRoomNumber(argMultimap.getValue(PREFIX_ROOM_NUMBER).get());
        RoomType roomType = ParserUtil.parseRoomType(argMultimap.getValue(PREFIX_ROOM_TYPE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Room room = new Room(floor, roomNumber, roomType, tagList);

        return new AddRoomCommand(room);
    }
}

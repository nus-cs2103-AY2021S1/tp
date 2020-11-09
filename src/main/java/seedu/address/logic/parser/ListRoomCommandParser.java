package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;

import seedu.address.logic.commands.ListRoomCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListRoomCommandParser implements Parser<ListRoomCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ListRoomCommand}
     * and returns a {@code ListRoomCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListRoomCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ROOM_TYPE);

        int roomType = 0;

        if (argMultimap.getValue(PREFIX_ROOM_TYPE).isPresent()) {
            roomType = ParserUtil.parseRoomType(argMultimap.getValue(PREFIX_ROOM_TYPE).get());
        }

        return new ListRoomCommand(roomType);
    }
}

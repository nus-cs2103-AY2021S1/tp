package seedu.resireg.logic.parser;

import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.commands.AddCommand;
import seedu.resireg.logic.commands.AllocateCommand;
import seedu.resireg.logic.parser.exceptions.ParseException;
import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.student.*;
import seedu.resireg.model.tag.Tag;

import java.util.Set;
import java.util.stream.Stream;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AllocateCommandParser implements Parser<AllocateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AllocateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_INDEX, PREFIX_ROOM_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AllocateCommand.MESSAGE_USAGE));
        }

        try {
            Index studentIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_STUDENT_INDEX).get());
            Index roomIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ROOM_INDEX).get());
            return new AllocateCommand(studentIndex, roomIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AllocateCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

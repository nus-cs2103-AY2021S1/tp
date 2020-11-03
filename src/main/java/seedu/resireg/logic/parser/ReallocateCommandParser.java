package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_INDEX;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;

import java.util.stream.Stream;

import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.commands.ReallocateCommand;
import seedu.resireg.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new ReallocateCommand object
 */
public class ReallocateCommandParser implements Parser<ReallocateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ReallocateCommand
     * and returns an ReallocateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReallocateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_INDEX, PREFIX_ROOM_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_INDEX, PREFIX_ROOM_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ReallocateCommand.HELP.getFullMessage()));
        }

        Index studentIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_STUDENT_INDEX).get());
        Index roomIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ROOM_INDEX).get());
        return new ReallocateCommand(studentIndex, roomIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

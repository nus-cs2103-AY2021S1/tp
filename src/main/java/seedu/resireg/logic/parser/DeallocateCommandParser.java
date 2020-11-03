package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;

import java.util.stream.Stream;

import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.commands.DeallocateCommand;
import seedu.resireg.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeallocateCommand object
 */
public class DeallocateCommandParser implements Parser<DeallocateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeallocateCommand
     * and returns an DeallocateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeallocateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeallocateCommand.HELP.getFullMessage()));
        }

        Index studentIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_STUDENT_INDEX).get());
        return new DeallocateCommand(studentIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

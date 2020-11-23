package seedu.tr4cker.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COMPLETION_STATUS;

import java.util.stream.Stream;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.DoneCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DoneCommand object
 */
public class DoneCommandParser implements Parser<DoneCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DoneCommand
     * and returns a DoneCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DoneCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_COMPLETION_STATUS);

        try {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            if (!arePrefixesPresent(argMultimap, PREFIX_COMPLETION_STATUS)) {
                return new DoneCommand(index, ParserUtil.parseCompletionStatus((Integer.toString(100))));
            } else {
                return new DoneCommand(index,
                        ParserUtil.parseCompletionStatus(argMultimap.getValue(PREFIX_COMPLETION_STATUS).get()));
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DoneCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        requireAllNonNull(argumentMultimap, prefixes);
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

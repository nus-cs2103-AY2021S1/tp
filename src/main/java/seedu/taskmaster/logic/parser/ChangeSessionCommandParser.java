package seedu.taskmaster.logic.parser;

import static seedu.taskmaster.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_SESSION_NAME;

import java.util.stream.Stream;

import seedu.taskmaster.logic.commands.ChangeSessionCommand;
import seedu.taskmaster.logic.parser.exceptions.ParseException;
import seedu.taskmaster.model.session.SessionName;

/**
 * Parses input arguments and creates a new ChangeSessionCommand object
 */
public class ChangeSessionCommandParser implements Parser<ChangeSessionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * ChangeSessionCommand and returns a ChangeSessionCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ChangeSessionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer
                        .tokenize(args, PREFIX_SESSION_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_SESSION_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeSessionCommand.MESSAGE_USAGE));
        }

        SessionName sessionName = ParserUtil.parseSessionName(argMultimap.getValue(PREFIX_SESSION_NAME).get());
        return new ChangeSessionCommand(sessionName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}


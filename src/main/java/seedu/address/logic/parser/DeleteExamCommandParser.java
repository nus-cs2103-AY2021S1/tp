package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.DeleteExamCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteExamCommand object.
 */
public class DeleteExamCommandParser implements Parser<DeleteExamCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteExamCommand
     * and returns an DeleteExamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public DeleteExamCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EXAM_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_EXAM_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        Index studentIndex;
        Index examIndex;

        try {
            studentIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            examIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EXAM_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }

        return new DeleteExamCommand(studentIndex, examIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

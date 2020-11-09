package seedu.flashcard.logic.parser;

import static seedu.flashcard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_FLAG;

import java.util.List;

import seedu.flashcard.logic.commands.SortCommand;
import seedu.flashcard.logic.parser.exceptions.ParseException;
import seedu.flashcard.model.flashcard.SortCriteria;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    public static final String FLAG_ASCENDING = "a";
    public static final String FLAG_DESCENDING = "d";

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FLAG);

        List<String> flagValueList = argMultimap.getAllValues(PREFIX_FLAG);

        if (!argMultimap.getValue(PREFIX_FLAG).isPresent() || argMultimap.getPreamble().isEmpty()
                || !ParserUtil.areValidFlagValues(flagValueList, FLAG_ASCENDING, FLAG_DESCENDING)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        try {
            SortCriteria sortCriteria = ParserUtil.parseSortCriteria(argMultimap.getPreamble(),
                    argMultimap.getValue(PREFIX_FLAG).get());
            return new SortCommand(sortCriteria);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE), pe);
        }
    }

}

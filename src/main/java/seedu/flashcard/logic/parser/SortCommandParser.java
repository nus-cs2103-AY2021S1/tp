package seedu.flashcard.logic.parser;

import static seedu.flashcard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_CRITERIA;

import seedu.flashcard.logic.commands.SortCommand;
import seedu.flashcard.logic.parser.exceptions.ParseException;
import seedu.flashcard.model.flashcard.SortCriteria;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CRITERIA);
        boolean isPrefixValid = argMultimap.getValue(PREFIX_CRITERIA).isPresent() && (
                argMultimap.getValue(PREFIX_CRITERIA).get().equals("a")
                        || argMultimap.getValue(PREFIX_CRITERIA).get().equals("d"));

        if (!isPrefixValid || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        try {
            SortCriteria sortCriteria = ParserUtil.parseSortCriteria(argMultimap.getPreamble(),
                    argMultimap.getValue(PREFIX_CRITERIA).get());
            return new SortCommand(sortCriteria);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE), pe);
        }
    }

}

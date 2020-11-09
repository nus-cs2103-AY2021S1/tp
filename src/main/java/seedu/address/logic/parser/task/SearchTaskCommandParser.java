package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_DUE_DATE;

import seedu.address.logic.commands.task.SearchTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.DateTimeDue;

/**
 * Parses input arguments and creates a new SearchTaskCommand object
 */
public class SearchTaskCommandParser implements Parser<SearchTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SearchTaskCommand
     * and returns an SearchTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public SearchTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DUE_DATE);

        if (!ParserUtil.isExactlyOnePrefixPresent(argMultimap, PREFIX_DUE_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchTaskCommand.MESSAGE_USAGE));
        }

        DateTimeDue dueAt = TaskParserUtil.parseDateTimeDue(argMultimap.getValue(PREFIX_DUE_DATE)); // optional


        return new SearchTaskCommand(dueAt);
    }
}

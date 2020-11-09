package seedu.fma.logic.parser;

import seedu.fma.commons.core.index.Index;
import seedu.fma.logic.commands.DeleteCommand;
import seedu.fma.logic.parser.exceptions.ParseException;
import seedu.fma.model.ReadOnlyLogBook;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args, ReadOnlyLogBook logBook) throws ParseException {
        Index index = ParserUtil.parseIndex(args);
        return new DeleteCommand(index);
    }
}

package seedu.fma.logic.parser;

import seedu.fma.commons.core.index.Index;
import seedu.fma.logic.commands.DeleteExCommand;
import seedu.fma.logic.parser.exceptions.ParseException;
import seedu.fma.model.ReadOnlyLogBook;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteExCommandParser implements Parser<DeleteExCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteExCommand
     * and returns a DeleteExCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteExCommand parse(String args, ReadOnlyLogBook logBook) throws ParseException {
        Index index = ParserUtil.parseIndex(args);
        return new DeleteExCommand(index);
    }

}

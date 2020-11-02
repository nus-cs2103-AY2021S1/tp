package seedu.address.logic.parser.recipe;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.recipe.SelectRecipeCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class SelectRecipeCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SelectRecipeCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            assert(index.getZeroBased() >= 0);
            return new SelectRecipeCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SelectRecipeCommand.MESSAGE_USAGE));
        }
    }
}


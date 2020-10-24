package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GetEditIngredientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class GetEditIngredientCommandParser implements Parser<GetEditIngredientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the GetEditIngredientCommand
     * and returns a GetEditIngredientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GetEditIngredientCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            assert(index.getZeroBased() >= 0);
            return new GetEditIngredientCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GetEditIngredientCommand.MESSAGE_USAGE));
        }
    }
}

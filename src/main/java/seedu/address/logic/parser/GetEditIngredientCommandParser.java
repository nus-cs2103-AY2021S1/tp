package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GetEditIngredientCommand;
import seedu.address.logic.commands.GetEditRecipeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class GetEditIngredientCommandParser implements Parser<GetEditIngredientCommand> {

    public GetEditIngredientCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new GetEditIngredientCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GetEditRecipeCommand.MESSAGE_USAGE));
        }
    }
}

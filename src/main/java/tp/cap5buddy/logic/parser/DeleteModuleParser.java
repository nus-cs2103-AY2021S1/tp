package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.commands.DeleteModuleCommand;
import tp.cap5buddy.logic.parser.exception.ParseException;

/**
 * Represents the parser that handles Delete Module command.
 */
public class DeleteModuleParser extends Parser {

    /**
     * Represents the function call that passes info into the Command object.
     *
     * @param userInput tokenised information.
     * @return Command the respective command type.
     */
    @Override
    public Command parse(String userInput) throws ParseException {
        Tokenizer token = new Tokenizer(userInput, PrefixList.MODULE_DELETE_PREFIX);
        ArgumentMap argumentMap = token.tokenize();
        if (!argumentMap.arePrefixesPresent(PrefixList.MODULE_DELETE_PREFIX)) {
            String error = "Missing prefix arguments";
            throw new ParseException(error);
        }
        int position = Integer.parseInt(argumentMap.getValue(PrefixList.MODULE_DELETE_PREFIX).get());

        return new DeleteModuleCommand(position);
    }
}

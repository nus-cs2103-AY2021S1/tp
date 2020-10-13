package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.commands.ViewModuleCommand;
import tp.cap5buddy.logic.parser.exception.ParseException;

/**
 * Represents the parser that handles View Module command.
 */
public class ViewModuleParser extends Parser {

    /**
     * Parses a user input for a command to view a stored module.
     *
     * @param userInput
     * @return
     */
    public Command parse(String userInput) throws ParseException {
        Tokenizer token = new Tokenizer(userInput, PrefixList.MODULE_VIEW_PREFIX);
        ArgumentMap argumentMap = token.tokenize();
        if (!argumentMap.arePrefixesPresent(PrefixList.MODULE_VIEW_PREFIX)) {
            String error = "Missing prefix arguments";
            throw new ParseException(error);
        }
        String modName = argumentMap.getValue(PrefixList.MODULE_VIEW_PREFIX).get();
        return new ViewModuleCommand(modName);
    }
}

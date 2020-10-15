package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.AddModuleCommand;
import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.parser.exception.ParseException;


/**
 * Represents the parser that handles Add Module command.
 */
public class AddModuleParser extends Parser {
    private AddModuleCommand command;

    /**
     * Represents the function call that passes info into the Command object.
     *
     * @param userInput tokenised information.
     * @return Command the respective command type.
     */
    public Command parse(String userInput) throws ParseException {
        Tokenizer token = new Tokenizer(userInput, PrefixList.MODULE_NAME_PREFIX, PrefixList.MODULE_LINK_PREFIX);
        ArgumentMap argumentMap = token.tokenize();
        if (!argumentMap.arePrefixesPresent(PrefixList.MODULE_NAME_PREFIX)) {
            String error = "Missing prefix arguments";
            throw new ParseException(error);
        }
        String modName = argumentMap.getValue(PrefixList.MODULE_NAME_PREFIX).get();
        String modLink = argumentMap.getValue(PrefixList.MODULE_LINK_PREFIX).orElse("");
        this.command = new AddModuleCommand(modName, modLink);
        return this.command;
    }

}

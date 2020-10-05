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
        System.out.println("Parser accessed");
        Tokenizer token = new Tokenizer(userInput, PrefixList.MODULE_NAME_PREFIX, PrefixList.MODULE_LINK_PREFIX);
        String[] parsedArguments = token.tokenize();
        String modName = parsedArguments[0];
        String modLink = parsedArguments[1];
        System.out.println("NAME: "+modName);
        System.out.println("LINK: "+modLink);
        return new AddModuleCommand(modName, modLink);
    }

}

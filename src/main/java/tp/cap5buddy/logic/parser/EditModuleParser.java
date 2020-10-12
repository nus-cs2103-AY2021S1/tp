package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.commands.EditModuleCommand;
import tp.cap5buddy.logic.commands.EditModuleCommand.EditModuleDescriptor;
import tp.cap5buddy.logic.parser.exception.ParseException;

/**
 * Represents the parser that handles Edit Module command.
 */
public class EditModuleParser extends Parser {
    /**
     * Represents a function that passes the user input into a Command object.
     *
     * @param userInput tokenized user input.
     * @return Command object based on the user input.
     */
    @Override
    public Command parse(String userInput) throws ParseException {
        Tokenizer token = new Tokenizer(userInput,
                PrefixList.MODULE_NAME_PREFIX,
                PrefixList.MODULE_NEWNAME_PREFIX,
                PrefixList.MODULE_LINK_PREFIX
        );
        String[] parsedArguments = token.tokenize();
        String moduleName = parsedArguments[0];
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        if (parsedArguments.length >= 2) {
            editModuleDescriptor.setZoomLink(parsedArguments[2]);
        }
        if (parsedArguments.length >= 3) {
            editModuleDescriptor.setName(parsedArguments[1]);
        }
        return new EditModuleCommand(moduleName, editModuleDescriptor);
    }
}

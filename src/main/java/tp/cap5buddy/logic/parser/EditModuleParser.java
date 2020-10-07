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
        ArgumentMap argumentMap = token.tokenize();
        if (!argumentMap.arePrefixesPresent(PrefixList.MODULE_NAME_PREFIX,
                PrefixList.MODULE_NEWNAME_PREFIX,
                PrefixList.MODULE_LINK_PREFIX)) {
            String error = "Missing prefix arguments";
            throw new ParseException(error);
        }
        String moduleName = argumentMap.getValue(PrefixList.MODULE_NAME_PREFIX).get();
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        String zoomLink = argumentMap.getValue(PrefixList.MODULE_LINK_PREFIX).get();
        editModuleDescriptor.setZoomLink(zoomLink);
        String newModuleName = argumentMap.getValue(PrefixList.MODULE_NEWNAME_PREFIX).get();
        editModuleDescriptor.setName(newModuleName);
        return new EditModuleCommand(moduleName, editModuleDescriptor);
    }
}

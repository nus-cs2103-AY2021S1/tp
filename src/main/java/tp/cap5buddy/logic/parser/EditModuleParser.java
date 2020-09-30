package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.commands.EditModuleCommand;
import tp.cap5buddy.logic.commands.EditModuleCommand.EditModuleDescriptor;

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
    public Command parse(String userInput) {
        Tokenizer token = new Tokenizer(userInput);
        String[] mod = token.getWords();
        String moduleName = mod[0];
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        if (mod.length >= 2) {
            editModuleDescriptor.setZoomLink(mod[1]);
        }
        if (mod.length >= 3) {
            editModuleDescriptor.setName(mod[2]);
        }
        return new EditModuleCommand(moduleName, editModuleDescriptor);
    }
}

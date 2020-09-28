package tp.cap5buddy.parser;

import java.util.ArrayList;

import tp.cap5buddy.commands.AddModuleCommand;
import tp.cap5buddy.commands.Command;
import tp.cap5buddy.commands.ResultCommand;
import tp.cap5buddy.modules.ModuleList;


/**
 * Represents the parser that handles Add Module command.
 */
public class AddModuleParser extends Parser {
    private AddModuleCommand command;

    /**
     * Represents the function call that passes info into the Command object.
     * @param userInput tokenised information.
     * @return Command the respective command type.
     */
    public Command parse(String userInput) {
        // this.command = new AddModuleCommand(words);
        Tokenizer token = new Tokenizer(userInput);
        String[] mod = token.getWords();
        // return this.command;
        return new AddModuleCommand(mod);
    }

}

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
        String[] mod = {"cs2103"};
        // return this.command;
        return new AddModuleCommand(mod);
    }

    /**
     * Executes the command based on the info inside.
     *
     * @return ResultCommand the result container.
     */
    public ResultCommand execute() {
        return this.command.execute(new ModuleList(new ArrayList<>()));
    }
}

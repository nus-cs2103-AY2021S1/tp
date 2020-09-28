package tp.cap5buddy.parser;

import tp.cap5buddy.commands.AddModuleCommand;
import tp.cap5buddy.commands.Command;
import tp.cap5buddy.commands.ResultCommand;

/**
 * Represents the parser that handles Add Module command.
 */
public class AddModuleParser extends Parser {
    private AddModuleCommand command;

    /**
     * Represents the function call that passes info into the Command object.
     * @param words tokenised information.
     * @return Command the respective command type.
     */
    public Command parse(String[] words) {
        this.command = new AddModuleCommand(words);
        return this.command;
    }

    /**
     * Executes the command based on the info inside.
     * @return ResultCommand the result container.
     */
    public ResultCommand execute() {
        return this.command.execute();
    }
}

package tp.cap5buddy.parser;

import tp.cap5buddy.commands.AddModuleCommand;
import tp.cap5buddy.commands.Command;
import tp.cap5buddy.commands.ResultCommand;

public class AddModuleParser extends Parser {
    private AddModuleCommand command;

    public Command parse(String[] words) {
        this.command = new AddModuleCommand(words);
        return this.command;
    }

    public ResultCommand execute() {
        return this.command.execute();
    }
}

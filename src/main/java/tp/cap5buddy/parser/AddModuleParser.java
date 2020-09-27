package tp.cap5buddy.parser;

import tp.cap5buddy.commands.AddModuleCommand;

public class AddModuleParser extends Parser {
    AddModuleCommand command;
    public AddModuleParser(String[] words) {
        this.command = new AddModuleCommand(words);
    }

    public String execute() {
        return this.command.execute();
    }
}

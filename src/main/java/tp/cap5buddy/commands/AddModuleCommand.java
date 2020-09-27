package tp.cap5buddy.commands;

import tp.cap5buddy.modules.Module;

public class AddModuleCommand extends Command {
    private final String name;
    private final String link;
    private static final String SUCCESS_MESSAGE = " has been added successfully!";
    public AddModuleCommand(String[] info) {
        this.name = info[0];
        this.link = info[1];
    }

    public String execute() {
        if (this.link == null) {
            Module mod = new Module(this.name);
        } else {
            Module mod = new Module(this.name, this.link);
        }
        return this.name + SUCCESS_MESSAGE;
    }
}

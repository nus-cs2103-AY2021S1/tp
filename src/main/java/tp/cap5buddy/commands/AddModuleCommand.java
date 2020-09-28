package tp.cap5buddy.commands;

import tp.cap5buddy.modules.Module;

/**
 * Represents the AddModuleCommand class.
 */
public class AddModuleCommand extends Command {
    private static final String SUCCESS_MESSAGE = " has been added successfully!";
    private final String name;
    private final String link;

    /**
     * Represents the constuctor to making the AddModuleCommand object.
     * @param info array of tokenisied info.
     */
    public AddModuleCommand(String[] info) {
        this.name = info[0];
        this.link = info[1];
    }

    /**
     * Executes the main function of this command, to create a new module.
     * @return ResultCommand ResultCommand object.
     */
    public ResultCommand execute() {
        if (this.link == null) {
            Module mod = new Module(this.name);
        } else {
            Module mod = new Module(this.name, this.link);
        }
        return new ResultCommand(this.name + SUCCESS_MESSAGE);
    }
}

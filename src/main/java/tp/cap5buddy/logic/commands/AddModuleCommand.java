package tp.cap5buddy.logic.commands;

import tp.cap5buddy.modules.Module;
import tp.cap5buddy.modules.ModuleList;

/**
 * Represents the AddModuleCommand class.
 */
public class AddModuleCommand extends Command {
    private static final String SUCCESS_MESSAGE = " has been added successfully!";
    private final String name;
    private final String link;

    /**
     * Represents the constructor to making the AddModuleCommand object.
     *
     * @param modName name of the module to add.
     * @param modLink the link to attach to this module.
     */
    public AddModuleCommand(String modName, String modLink) {
        this.name = modName;
        this.link = modLink;
    }

    /**
     * Executes the main function of this command, to create a new module.
     *
     * @return ResultCommand ResultCommand object.
     */
    public ResultCommand execute(ModuleList modules) {
        System.out.println("Execute accessed");
        if (this.link == null) {
            Module mod = new Module(this.name);
            modules.addModule(mod);
        } else {
            Module mod = new Module(this.name, this.link);
            modules.addModule(mod);
        }
        return new ResultCommand(this.name + SUCCESS_MESSAGE, isExit());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

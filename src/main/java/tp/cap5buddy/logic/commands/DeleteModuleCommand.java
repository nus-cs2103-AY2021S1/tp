package tp.cap5buddy.logic.commands;

import tp.cap5buddy.modules.Module;
import tp.cap5buddy.modules.ModuleList;

public class DeleteModuleCommand extends Command {
    private static final String SUCCESS_MESSAGE = " has been deleted successfully!";
    private final String name;
    private final String link;

    /**
     * Represents the constuctor to making the DeleteModuleCommand object.
     * @param info array of tokenisied info.
     */
    public DeleteModuleCommand(String[] info) {
        this.name = info[0];
        this.link = info[1];
    }

    /**
     * Executes the main function of this command, to remove a module.
     * @return ResultCommand ResultCommand object.
     */
    public ResultCommand execute(ModuleList modules) {
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

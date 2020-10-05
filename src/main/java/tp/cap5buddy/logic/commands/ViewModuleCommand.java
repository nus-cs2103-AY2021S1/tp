package tp.cap5buddy.logic.commands;

import java.util.ArrayList;

import tp.cap5buddy.modules.Module;
import tp.cap5buddy.modules.ModuleList;



/**
 * Represents the ViewModuleCommand class.
 */
public class ViewModuleCommand extends Command {
    private static final String SUCCESS_MESSAGE = "Module details have been displayed successfully!";
    private ArrayList<Module> modules;
    private String moduleName;
    //need a storage system
    public ViewModuleCommand(String modName) {
        this.moduleName = modName;
    }

    /**
     * Executes the main function of this command, to view a specified module.
     * @return String success message.
     */
    public ResultCommand execute(ModuleList modules) {
        Module moduleToBeDisplayed = modules.getModule(this.moduleName);
        return new ResultCommand(moduleToBeDisplayed.toString() + "\n"
                + SUCCESS_MESSAGE, isExit());
    }

    /**
     * Indicates if the application session has ended.
     *
     * @return False since the sessions has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

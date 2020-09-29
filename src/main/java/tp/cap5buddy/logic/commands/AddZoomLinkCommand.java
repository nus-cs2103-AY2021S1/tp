package tp.cap5buddy.logic.commands;

import tp.cap5buddy.modules.Module;
import tp.cap5buddy.modules.ModuleList;

/**
 * Represents the AddZoomLinkCommand class.
 */
public class AddZoomLinkCommand extends Command {

    private static final String SUCCESS_MESSAGE = " has been added successfully to ";
    private final String moduleName;
    private final String zoomLink;

    /**
     * Creates and initialises a new AddZoomLinkCommand object.
     * @param commandArguments array containing the tokenized command arguments.
     */
    public AddZoomLinkCommand(String[] commandArguments) {
        this.moduleName = commandArguments[0];
        this.zoomLink = commandArguments[1];
    }

    /**
     * Executes the command to add a string containing the zoom link to the desired module.
     *
     * @return ResultCommand ResultCommand object.
     */
    @Override
    public ResultCommand execute(ModuleList moduleList) {
        Module module = moduleList.getModule(this.moduleName);
        module.addZoomLink(this.zoomLink);
        return new ResultCommand(this.zoomLink + SUCCESS_MESSAGE + this.moduleName, isExit());
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

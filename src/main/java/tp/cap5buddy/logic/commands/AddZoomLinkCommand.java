package tp.cap5buddy.logic.commands;

import tp.cap5buddy.contacts.ContactList;
import tp.cap5buddy.logic.commands.exception.CommandException;
import tp.cap5buddy.modules.Module;
import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.todolist.TodoList;

/**
 * Represents the AddZoomLinkCommand class.
 */
public class AddZoomLinkCommand extends Command {

    private final int moduleID;
    private final String zoomLink;

    /**
     * Creates and initialises a new AddZoomLinkCommand object.
     *
     * @param moduleIndex Zero based index of the module in the list of modules.
     * @param zoomLink String containing the zoom link to be added to the module.
     */
    public AddZoomLinkCommand(int moduleIndex, String zoomLink) {
        this.moduleID = moduleIndex;
        this.zoomLink = zoomLink;
    }

    /**
     * Executes the command to add a string containing the zoom link to the desired module.
     *
     * @param modules List of modules belonging to the user.
     * @return ResultCommand ResultCommand object.
     */
    @Override
    public CommandResult execute(ModuleList modules, ContactList contacts, TodoList todolist) throws CommandException {
        if (this.moduleID > modules.getListSize() || this.moduleID <= 0) {
            String error = "Invalid module. The module ID you provided is not valid.";
            throw new CommandException(error);
        }
        int index = this.moduleID - 1;
        Module module = modules.getModuleByIndex(index);
        Module updatedModule = module.addZoomLink(this.zoomLink);
        modules.updateModule(index, updatedModule);
        String successMessage = createSuccessMessage(updatedModule.getName());
        return new CommandResult(successMessage, isExit());
    }

    /**
     * Creates a success message when the zoom link has been successfully added to the desired module.
     *
     * @param moduleName String containing the name of the module.
     * @return String containing the success message.
     */
    public String createSuccessMessage(String moduleName) {
        String message = this.zoomLink + " has been successfully added to " + moduleName;
        return message;
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

package tp.cap5buddy.logic.commands;

import tp.cap5buddy.contacts.ContactList;
import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.todolist.TodoList;

/**
 * Represents the DeleteModuleCommand class.
 */
public class DeleteModuleCommand extends Command {
    private static final String SUCCESS_MESSAGE_FIRST = "The module at position ";
    private static final String SUCCESS_MESSAGE_SECOND = " has been deleted successfully!";
    private final int position;

    /**
     * Represents the constructor to make the DeleteModuleCommand object.
     *
     * @param position position of module to delete.
     */
    public DeleteModuleCommand(int position) {
        this.position = position;
    }

    /**
     * Executes the main function of this command, to remove a module.
     *
     * @return ResultCommand ResultCommand object.
     */
    public CommandResult execute(ModuleList modules, ContactList contacts, TodoList todolist) {
        modules.deleteModule(position);
        return new CommandResult(SUCCESS_MESSAGE_FIRST + position + SUCCESS_MESSAGE_SECOND, isExit());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

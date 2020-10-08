package tp.cap5buddy.logic.commands;

import tp.cap5buddy.contacts.ContactList;
import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.todolist.TodoList;

/**
 * Represents the DeleteModuleCommand class.
 */
public class DeleteModuleCommand extends Command {
    private static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Module: %1$s";
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
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, position), isExit());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

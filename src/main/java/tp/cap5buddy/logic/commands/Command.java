package tp.cap5buddy.logic.commands;

import tp.cap5buddy.contacts.ContactList;
import tp.cap5buddy.logic.commands.exception.CommandException;
import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.todolist.TodoList;

/**
 * Represents a command to be executed by the application.
 */
public abstract class Command {

    /**
     * Executes the relevant command.
     *
     * @return ResultCommand object.
     */
    public abstract CommandResult execute(ModuleList modules, ContactList contacts, TodoList todolist)
            throws CommandException;

    /**
     * Indicates if the application session has ended.
     *
     * @return True if the session has been terminated, false otherwise.
     */
    public abstract boolean isExit();
}

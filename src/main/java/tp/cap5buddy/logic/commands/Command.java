package tp.cap5buddy.logic.commands;

import tp.cap5buddy.modules.ModuleList;

/**
 * Represents a command to be executed by the application.
 */
public abstract class Command {

    /**
     * Executes the relevant command.
     *
     * @return ResultCommand object.
     */
    public abstract ResultCommand execute(ModuleList modules);

    /**
     * Indicates if the application session has ended.
     *
     * @return True if the session has been terminated, false otherwise.
     */
    public abstract boolean isExit();
}

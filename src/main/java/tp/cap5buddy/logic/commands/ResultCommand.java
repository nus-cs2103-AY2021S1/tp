package tp.cap5buddy.logic.commands;

/**
 * Represents that command the stores the success or failure message.
 */
public class ResultCommand {
    private String result;
    private boolean isExit;

    /**
     * Represents the constructor of ResultCommand.
     * @param result the message to store.
     */
    public ResultCommand(String result, boolean isExit) {
        this.result = result;
        this.isExit = isExit;
    }

    /**
     * Returns the message inside the ResultCommand.
     * @return String message.
     */
    public String getMessage() {
        return this.result;
    }

    public boolean getExit() {
        return this.isExit;
    }
}

package tp.cap5buddy.commands;

/**
 * Represents that command the stores the success or failure message.
 */
public class ResultCommand {
    private String result;

    /**
     * Represents the constructor of ResultCommand.
     * @param result the message to store.
     */
    public ResultCommand(String result) {
        this.result = result;
    }

    /**
     * Returns the message inside the ResultCommand.
     * @return String message.
     */
    public String getMessage() {
        return this.result;
    }
}

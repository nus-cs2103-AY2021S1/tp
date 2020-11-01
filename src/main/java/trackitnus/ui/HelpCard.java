package trackitnus.ui;

/**
 * Represents a command as well as the action it executes
 */
public class HelpCard {
    private String command;
    private String action;

    /**
     * Constructor for a HelpCard
     * @param action is what results from the command
     * @param command is what to input
     */
    public HelpCard(String action, String command) {
        this.action = action;
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public String getAction() {
        return action;
    }
}

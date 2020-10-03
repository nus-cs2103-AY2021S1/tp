package seedu.address.model.history;

import java.util.Stack;

/**
 * Represents command history in the address book.
 */
public class CommandHistory {
    private static Stack<String> commandHistory = new Stack<>();
    public static final String str = "Here are your command history:%s";

    /**
     * Adds command to command history.
     *
     * @param command
     */
    public static void addUsedCommand(String command) {
        commandHistory.push(command);
    }

    /**
     * Returns true if command history is not empty.
     */
    public static boolean hasCommand() {
        return commandHistory.size() != 1;
    }

    /**
     * Prints a list of past used commands in the command history.
     *
     * @return history of commands
     */
    public static String getCommandHistory() {
        String history = "";
        for (int i = commandHistory.size() - 1; i > 0; i--) {
            history += ("\n -\t");
            history += (commandHistory.get(i - 1));
        }
        return String.format(str, history);
    }

    /**
     * Clears command history.
     */
    public static void clearHistory() {
        commandHistory.clear();
    }
}

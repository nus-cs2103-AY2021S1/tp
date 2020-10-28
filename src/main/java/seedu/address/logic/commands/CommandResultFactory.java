package seedu.address.logic.commands;

public class CommandResultFactory {
    public static CommandResult createCommandResultForExitCommand(String message) {
        return new CommandResult(message, false, true, false);
    }

    public static CommandResult createCommandResultForHelpCommand(String message) {
        return new CommandResult(message, true, false, false);
    }

    public static CommandResult createCommandResultForEntryListChangingCommand(String message) {
        return new CommandResult(message, false, false, true);
    }

    public static CommandResult createDefaultCommandResult(String message) {
        return new CommandResult(message, false, false, false);
    }

}

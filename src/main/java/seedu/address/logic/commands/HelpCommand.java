package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;
    public static final String STRING_FORMAT = "%-20s %s";
    public static final String SHOWING_HELP_MESSAGE = String.format("%-14s %s", "Command", "Usage\n")
            + String.format("%-21s %s", ClearCommand.COMMAND_WORD, ClearCommand.CLEAR_COMMAND_USAGE + "\n")
            + String.format("%-22s %s", ExitCommand.COMMAND_WORD, ExitCommand.EXIT_COMMAND_USAGE + "\n")
            + String.format("%-22s %s", FindCommand.COMMAND_WORD, FindCommand.FIND_MESSAGE_USAGE + "\n")
            + String.format("%-24s %s", ListCommand.COMMAND_WORD, ListCommand.LIST_MESSAGE_USAGE + "\n")
            + String.format(STRING_FORMAT, OpenCommand.COMMAND_WORD, OpenCommand.OPEN_MESSAGE_USAGE + "\n")
            + String.format("%-21s %s", RetagCommand.COMMAND_WORD, RetagCommand.RETAG_MESSAGE_USAGE + "\n")
            + String.format(STRING_FORMAT, ShowCommand.COMMAND_WORD, ShowCommand.SHOW_MESSAGE_USAGE + "\n")
            + String.format("%-22s %s", TagCommand.COMMAND_WORD, TagCommand.TAG_COMMAND_USAGE + "\n")
            + String.format(STRING_FORMAT, UntagCommand.COMMAND_WORD, UntagCommand.UNTAG_MESSAGE_USAGE + "\n")
            + String.format(STRING_FORMAT, LabelCommand.COMMAND_WORD, LabelCommand.LABEL_COMMAND_USAGE + "\n");

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}

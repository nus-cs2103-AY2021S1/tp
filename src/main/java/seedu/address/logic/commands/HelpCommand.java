package seedu.address.logic.commands;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

import static java.util.Objects.checkFromIndexSize;
import static java.util.Objects.requireNonNull;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;
    public static final String STRING_FORMAT = "%-20s %s";
    public static final String SHOWING_HELP_MESSAGE = String.format("%-14s %s", "Command", "Usage\n")
            + String.format("%-22s %s", CdCommand.COMMAND_WORD, CdCommand.CD_COMMAND_USAGE + "\n")
            + String.format("%-21s %s", ClearCommand.COMMAND_WORD   , ClearCommand.CLEAR_COMMAND_USAGE + "\n")
            + String.format("%-22s %s", ExitCommand.COMMAND_WORD, ExitCommand.EXIT_COMMAND_USAGE + "\n")
            + String.format("%-22s %s", FindCommand.COMMAND_WORD, FindCommand.FIND_MESSAGE_USAGE + "\n")
            + String.format("%-24s %s", ListCommand.COMMAND_WORD, ListCommand.LIST_MESSAGE_USAGE + "\n")
            + String.format(STRING_FORMAT,OpenCommand.COMMAND_WORD, OpenCommand.OPEN_MESSAGE_USAGE + "\n")
            + String.format("%-21s %s", RedoCommand.COMMAND_WORD, RedoCommand.REDO_COMMAND_USAGE + "\n")
            + String.format("%-21s %s", RetagCommand.COMMAND_WORD, RetagCommand.RETAG_MESSAGE_USAGE + "\n")
            + String.format(STRING_FORMAT, ShowCommand.COMMAND_WORD, ShowCommand.SHOW_MESSAGE_USAGE + "\n")
            + String.format("%-22s %s", TagCommand.COMMAND_WORD, TagCommand.TAG_COMMAND_USAGE + "\n")
            + String.format("%-20s %s", UndoCommand.COMMAND_WORD, UndoCommand.UNDO_COMMAND_USAGE + "\n")
            + String.format(STRING_FORMAT, UntagCommand.COMMAND_WORD, UntagCommand.UNTAG_MESSAGE_USAGE + "\n");
    public static final String INVALID_KEYWORD_MESSAGE = "%s is an unknown command";

    private final String commandWord;

    public HelpCommand() {
        this.commandWord = "";
    }

    public HelpCommand(String commandWord) {
        requireNonNull(commandWord);
        this.commandWord = commandWord;
    }
    @Override
    public CommandResult execute(Model model) {
        switch(commandWord) {
        case CdCommand.COMMAND_WORD:
            return new CommandResult(CdCommand.MESSAGE_USAGE);
        case ClearCommand.COMMAND_WORD:
            return new CommandResult(ClearCommand.MESSAGE_USAGE);
        case ExitCommand.COMMAND_WORD:
            return new CommandResult(ExitCommand.MESSAGE_USAGE);
        case FindCommand.COMMAND_WORD:
            return new CommandResult(FindCommand.MESSAGE_USAGE);
        case ListCommand.COMMAND_WORD:
            return new CommandResult(ListCommand.MESSAGE_USAGE);
        case OpenCommand.COMMAND_WORD:
            return new CommandResult(OpenCommand.MESSAGE_USAGE);
        case RedoCommand.COMMAND_WORD:
            return new CommandResult(RedoCommand.MESSAGE_USAGE);
        case RetagCommand.COMMAND_WORD:
            return new CommandResult(RetagCommand.MESSAGE_USAGE);
        case ShowCommand.COMMAND_WORD:
            return new CommandResult(ShowCommand.MESSAGE_USAGE);
        case TagCommand.COMMAND_WORD:
            return new CommandResult(TagCommand.MESSAGE_USAGE);
        case UndoCommand.COMMAND_WORD:
            return new CommandResult(UndoCommand.MESSAGE_USAGE);
        case UntagCommand.COMMAND_WORD:
            return new CommandResult(UntagCommand.MESSAGE_USAGE);
        case "":
            return new CommandResult(SHOWING_HELP_MESSAGE);
        default:
            return new CommandResult(String.format(INVALID_KEYWORD_MESSAGE,commandWord));
        }
    }
}

package seedu.zookeep.logic.parser;

import static seedu.zookeep.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zookeep.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.zookeep.logic.commands.AddCommand;
import seedu.zookeep.logic.commands.AppendCommand;
import seedu.zookeep.logic.commands.ClearCommand;
import seedu.zookeep.logic.commands.Command;
import seedu.zookeep.logic.commands.DeleteCommand;
import seedu.zookeep.logic.commands.ExitCommand;
import seedu.zookeep.logic.commands.FindCommand;
import seedu.zookeep.logic.commands.HelpCommand;
import seedu.zookeep.logic.commands.ListCommand;
import seedu.zookeep.logic.commands.RedoCommand;
import seedu.zookeep.logic.commands.ReplaceCommand;
import seedu.zookeep.logic.commands.SnapCommand;
import seedu.zookeep.logic.commands.SortCommand;
import seedu.zookeep.logic.commands.UndoCommand;
import seedu.zookeep.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ZooKeepBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case ReplaceCommand.COMMAND_WORD:
            return new ReplaceCommandParser().parse(arguments);

        case AppendCommand.COMMAND_WORD:
            return new AppendCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case SnapCommand.COMMAND_WORD:
            return new SnapCommandParser().parse(arguments);

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

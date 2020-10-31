package seedu.tasklist.logic.parser;

import static seedu.tasklist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tasklist.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.tasklist.logic.commands.AddCommand;
import seedu.tasklist.logic.commands.ClearCommand;
import seedu.tasklist.logic.commands.Command;
import seedu.tasklist.logic.commands.DeleteCommand;
import seedu.tasklist.logic.commands.DoneCommand;
import seedu.tasklist.logic.commands.EditCommand;
import seedu.tasklist.logic.commands.ExitCommand;
import seedu.tasklist.logic.commands.FindCommand;
import seedu.tasklist.logic.commands.HelpCommand;
import seedu.tasklist.logic.commands.ImportCommand;
import seedu.tasklist.logic.commands.ListCommand;
import seedu.tasklist.logic.commands.PrioritizeCommand;
import seedu.tasklist.logic.commands.RemindCommand;
import seedu.tasklist.logic.commands.ScheduleCommand;
import seedu.tasklist.logic.commands.UndoCommand;
import seedu.tasklist.logic.commands.UndoneCommand;
import seedu.tasklist.logic.commands.UnprioritizeCommand;
import seedu.tasklist.logic.commands.UnremindCommand;
import seedu.tasklist.logic.commands.UnscheduleCommand;
import seedu.tasklist.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ProductiveNusParser {

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

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case RemindCommand.COMMAND_WORD:
            return new RemindCommandParser().parse(arguments);

        case UnremindCommand.COMMAND_WORD:
            return new UnremindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ImportCommand.COMMAND_WORD:
            return new ImportCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case ScheduleCommand.COMMAND_WORD:
            return new ScheduleCommandParser().parse(arguments);

        case UnscheduleCommand.COMMAND_WORD:
            return new UnscheduleCommandParser().parse(arguments);

        case PrioritizeCommand.COMMAND_WORD:
            return new PrioritizeCommandParser().parse(arguments);

        case UnprioritizeCommand.COMMAND_WORD:
            return new UnprioritizeCommandParser().parse(arguments);

        case DoneCommand.COMMAND_WORD:
            return new DoneCommandParser().parse(arguments);

        case UndoneCommand.COMMAND_WORD:
            return new UndoneCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

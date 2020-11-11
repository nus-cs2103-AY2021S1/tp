package seedu.bookmark.logic.parser;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.bookmark.logic.commands.AddCommand;
import seedu.bookmark.logic.commands.AddNoteCommand;
import seedu.bookmark.logic.commands.ClearCommand;
import seedu.bookmark.logic.commands.Command;
import seedu.bookmark.logic.commands.DeleteCommand;
import seedu.bookmark.logic.commands.DeleteNoteCommand;
import seedu.bookmark.logic.commands.EditCommand;
import seedu.bookmark.logic.commands.ExitCommand;
import seedu.bookmark.logic.commands.FindCommand;
import seedu.bookmark.logic.commands.GoalCommand;
import seedu.bookmark.logic.commands.GoalDelCommand;
import seedu.bookmark.logic.commands.HelpCommand;
import seedu.bookmark.logic.commands.ListCommand;
import seedu.bookmark.logic.commands.RedoCommand;
import seedu.bookmark.logic.commands.SortCommand;
import seedu.bookmark.logic.commands.UndoCommand;
import seedu.bookmark.logic.commands.ViewCommand;
import seedu.bookmark.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class CommandParser {

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

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case GoalCommand.COMMAND_WORD:
            return new GoalCommandParser().parse(arguments);

        case GoalDelCommand.COMMAND_WORD:
            return new GoalDelCommandParser().parse(arguments);

        case AddNoteCommand.COMMAND_WORD:
            return new AddNoteCommandParser().parse(arguments);
        case DeleteNoteCommand.COMMAND_WORD:
            return new DeleteNoteCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

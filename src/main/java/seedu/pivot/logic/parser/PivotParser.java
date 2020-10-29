package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.pivot.logic.commands.AddCommand;
import seedu.pivot.logic.commands.ClearCommand;
import seedu.pivot.logic.commands.Command;
import seedu.pivot.logic.commands.DeleteCommand;
import seedu.pivot.logic.commands.EditCommand;
import seedu.pivot.logic.commands.ExitCommand;
import seedu.pivot.logic.commands.FindCommand;
import seedu.pivot.logic.commands.HelpCommand;
import seedu.pivot.logic.commands.ListCommand;
import seedu.pivot.logic.commands.OpenCommand;
import seedu.pivot.logic.commands.RedoCommand;
import seedu.pivot.logic.commands.ReturnCommand;
import seedu.pivot.logic.commands.UndoCommand;
import seedu.pivot.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class PivotParser {

    /**
     * Used for initial separation of command word and args.
     */
    static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

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
            return new ListCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case OpenCommand.COMMAND_WORD:
            return new OpenCommandParser().parse(arguments);

        case ReturnCommand.COMMAND_WORD:
            return new ReturnCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

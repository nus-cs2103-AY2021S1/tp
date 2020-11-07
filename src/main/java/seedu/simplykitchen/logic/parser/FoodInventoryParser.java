package seedu.simplykitchen.logic.parser;

import static seedu.simplykitchen.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.simplykitchen.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.simplykitchen.logic.commands.AddCommand;
import seedu.simplykitchen.logic.commands.ChangeQuantityCommand;
import seedu.simplykitchen.logic.commands.ClearCommand;
import seedu.simplykitchen.logic.commands.Command;
import seedu.simplykitchen.logic.commands.DeleteCommand;
import seedu.simplykitchen.logic.commands.EditCommand;
import seedu.simplykitchen.logic.commands.ExitCommand;
import seedu.simplykitchen.logic.commands.ExpiredCommand;
import seedu.simplykitchen.logic.commands.FindCommand;
import seedu.simplykitchen.logic.commands.HelpCommand;
import seedu.simplykitchen.logic.commands.ListCommand;
import seedu.simplykitchen.logic.commands.RedoCommand;
import seedu.simplykitchen.logic.commands.SortDescCommand;
import seedu.simplykitchen.logic.commands.SortExpiryCommand;
import seedu.simplykitchen.logic.commands.SortPriorityCommand;
import seedu.simplykitchen.logic.commands.UndoCommand;
import seedu.simplykitchen.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class FoodInventoryParser {

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

        case ChangeQuantityCommand.COMMAND_WORD:
            return new ChangeQuantityCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case SortDescCommand.COMMAND_WORD:
            return new SortDescCommand();

        case SortExpiryCommand.COMMAND_WORD:
            return new SortExpiryCommand();

        case SortPriorityCommand.COMMAND_WORD:
            return new SortPriorityCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case ExpiredCommand.COMMAND_WORD:
            return new ExpiredCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

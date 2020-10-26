package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddItemCommand;
import seedu.address.logic.commands.AddItemTagCommand;
import seedu.address.logic.commands.AddQuantityToItemCommand;
import seedu.address.logic.commands.AddRecipeCommand;
import seedu.address.logic.commands.ClearItemCommand;
import seedu.address.logic.commands.ClearRecipeCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CraftItemCommand;
import seedu.address.logic.commands.DeleteItemCommand;
import seedu.address.logic.commands.DeleteRecipeCommand;
import seedu.address.logic.commands.EditItemCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindByTagCommand;
import seedu.address.logic.commands.FindItemCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListItemCommand;
import seedu.address.logic.commands.ListRecipeCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.ViewDetailsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class InventoryParser {

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
    public Command parseCommand(String userInput) throws ParseException, IOException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        // Items commands start here
        case AddItemCommand.COMMAND_WORD:
            return new AddItemCommandParser().parse(arguments);
        case EditItemCommand.COMMAND_WORD:
            return new EditItemCommandParser().parse(arguments);
        case AddQuantityToItemCommand.COMMAND_WORD:
            return new AddQuantityToItemCommandParser().parse(arguments);
        case AddItemTagCommand.COMMAND_WORD:
            return new AddItemTagCommandParser().parse(arguments);
        case FindItemCommand.COMMAND_WORD:
            return new FindItemCommandParser().parse(arguments);
        case FindByTagCommand.COMMAND_WORD:
            return new FindByTagCommandParser().parse(arguments);
        case ClearItemCommand.COMMAND_WORD:
            return new ClearItemCommand();
        case ListItemCommand.COMMAND_WORD:
            return new ListItemCommandParser().parse(arguments);
        case DeleteItemCommand.COMMAND_WORD:
            return new DeleteItemCommandParser().parse(arguments);
        case ViewDetailsCommand.COMMAND_WORD:
            return new ViewDetailsCommandParser().parse(arguments);
        case CraftItemCommand.COMMAND_WORD:
            return new CraftItemCommandParser().parse(arguments);

        // Recipe commands start here
        case AddRecipeCommand.COMMAND_WORD:
            return new AddRecipeCommandParser().parse(arguments);
        case DeleteRecipeCommand.COMMAND_WORD:
            return new DeleteRecipeCommandParser().parse(arguments);
        case ListRecipeCommand.COMMAND_WORD:
            return new ListRecipeCommandParser().parse(arguments);
        case ClearRecipeCommand.COMMAND_WORD:
            return new ClearRecipeCommand();

        // Utility commands start here
        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();
        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ArchiveAllCommand;
import seedu.address.logic.commands.ArchiveCommand;
import seedu.address.logic.commands.ArchiveListCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindByTagCommand;
import seedu.address.logic.commands.FindByTagTodayCommand;
import seedu.address.logic.commands.FindByTagTomorrowCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SalesFindCommand;
import seedu.address.logic.commands.SalesListCommand;
import seedu.address.logic.commands.SalesUpdateCommand;
import seedu.address.logic.commands.UnarchiveCommand;
import seedu.address.logic.commands.ingredientcommands.IngredientFindCommand;
import seedu.address.logic.commands.ingredientcommands.IngredientListCommand;
import seedu.address.logic.commands.ingredientcommands.IngredientResetAllCommand;
import seedu.address.logic.commands.ingredientcommands.IngredientRestockCommand;
import seedu.address.logic.commands.ingredientcommands.IngredientSetAllCommand;
import seedu.address.logic.commands.ingredientcommands.IngredientSetCommand;
import seedu.address.logic.commands.ingredientcommands.IngredientSetDefaultCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TCheckParser {

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

        //@@author yanbingtao
        case ArchiveCommand.COMMAND_WORD:
            return new ArchiveCommandParser().parse(arguments);

        case UnarchiveCommand.COMMAND_WORD:
            return new UnarchiveCommandParser().parse(arguments);

        case ArchiveListCommand.COMMAND_WORD:
            return new ArchiveListCommand();

        case ArchiveAllCommand.COMMAND_WORD:
            return new ArchiveAllCommand();

        case FindByTagTodayCommand.COMMAND_WORD:
            return new FindByTagTodayCommand();

        case FindByTagTomorrowCommand.COMMAND_WORD:
            return new FindByTagTomorrowCommand();

        case FindByTagCommand.COMMAND_WORD:
            return new TagFindCommandParser().parse(arguments);
        //@@author

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        //@@author Diwu-Yi
        case IngredientSetCommand.COMMAND_WORD:
            return new IngredientSetCommandParser().parse(arguments);

        //@@author WM71811
        case IngredientFindCommand.COMMAND_WORD:
            return new IngredientFindCommandParser().parse(arguments);

        case IngredientListCommand.COMMAND_WORD:
            return new IngredientListCommand();

        //@@author Diwu-Yi
        case IngredientSetAllCommand.COMMAND_WORD:
            return new IngredientSetAllCommandParser().parse(arguments);

        case IngredientSetDefaultCommand.COMMAND_WORD:
            return new IngredientSetDefaultCommand();
        //@@author

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        //@@author WM71811
        case IngredientResetAllCommand.COMMAND_WORD:
            return new IngredientResetAllCommand();

        case IngredientRestockCommand.COMMAND_WORD:
            return new IngredientRestockCommand();

        // @@author ureshiiYing
        case SalesUpdateCommand.COMMAND_WORD:
            return new SalesUpdateCommandParser().parse(arguments);

        case SalesListCommand.COMMAND_WORD:
            return new SalesListCommand();

        // @@author
        case SalesFindCommand.COMMAND_WORD:
            return new SalesFindCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

    }

}

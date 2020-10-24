package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddIngredientCommand;
import seedu.address.logic.commands.AddRecipeCommand;
import seedu.address.logic.commands.ClearConsumptionCommand;
import seedu.address.logic.commands.ClearIngredientCommand;
import seedu.address.logic.commands.ClearRecipeCommand;
import seedu.address.logic.commands.CloseCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteConsumptionCommand;
import seedu.address.logic.commands.DeleteIngredientCommand;
import seedu.address.logic.commands.DeleteRecipeCommand;
import seedu.address.logic.commands.EatRecipeCommand;
import seedu.address.logic.commands.EditIngredientCommand;
import seedu.address.logic.commands.EditRecipeCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListConsumptionCommand;
import seedu.address.logic.commands.ListIngredientsCommand;
import seedu.address.logic.commands.ListRecipesCommand;
import seedu.address.logic.commands.RecommendCommand;
import seedu.address.logic.commands.SearchIngredientCommand;
import seedu.address.logic.commands.SearchRecipeCommand;
import seedu.address.logic.commands.SelectRecipeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class WishfulShrinkingParser {

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

        case AddRecipeCommand.COMMAND_WORD:
            return new AddRecipeCommandParser().parse(arguments);

        case AddIngredientCommand.COMMAND_WORD:
            return new AddIngredientCommandParser().parse(arguments);

        case EatRecipeCommand.COMMAND_WORD:
            return new EatRecipeCommandParser().parse(arguments);

        case EditIngredientCommand.COMMAND_WORD:
            if (arguments.split(" ").length == 2) {
                return new GetEditIngredientCommandParser().parse(arguments);
            } else {
                return new EditIngredientCommandParser().parse(arguments);
            }

        case EditRecipeCommand.COMMAND_WORD:
            if (arguments.split(" ").length == 2) {
                return new GetEditRecipeCommandParser().parse(arguments);
            } else {
                return new EditRecipeCommandParser().parse(arguments);
            }

        case DeleteIngredientCommand.COMMAND_WORD:
            return new DeleteIngredientCommandParser().parse(arguments);

        case DeleteRecipeCommand.COMMAND_WORD:
            return new DeleteRecipeCommandParser().parse(arguments);

        case DeleteConsumptionCommand.COMMAND_WORD:
            return new DeleteConsumptionCommandParser().parse(arguments);

        case ClearIngredientCommand.COMMAND_WORD:
            return new ClearIngredientCommand();

        case ClearRecipeCommand.COMMAND_WORD:
            return new ClearRecipeCommand();

        case ClearConsumptionCommand.COMMAND_WORD:
            return new ClearConsumptionCommand();

        case SearchRecipeCommand.COMMAND_WORD:
            return new SearchRecipeCommandParser().parse(arguments);

        case SearchIngredientCommand.COMMAND_WORD:
            return new SearchIngredientCommandParser().parse(arguments);

        case ListIngredientsCommand.COMMAND_WORD:
            return new ListIngredientsCommand();

        case ListRecipesCommand.COMMAND_WORD:
            return new ListRecipesCommand();

        case ListConsumptionCommand.COMMAND_WORD:
            return new ListConsumptionCommand();

        case RecommendCommand.COMMAND_WORD:
            return new RecommendCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case SelectRecipeCommand.COMMAND_WORD:
            return new SelectRecipeCommandParser().parse(arguments);

        case CloseCommand.COMMAND_WORD:
            return new CloseCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

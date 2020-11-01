package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.consumption.ClearConsumptionCommand;
import seedu.address.logic.commands.consumption.DeleteConsumptionCommand;
import seedu.address.logic.commands.consumption.EatRecipeCommand;
import seedu.address.logic.commands.consumption.ListConsumptionCommand;
import seedu.address.logic.commands.ingredient.AddIngredientCommand;
import seedu.address.logic.commands.ingredient.ClearIngredientCommand;
import seedu.address.logic.commands.ingredient.DeleteIngredientCommand;
import seedu.address.logic.commands.ingredient.EditIngredientCommand;
import seedu.address.logic.commands.ingredient.ListIngredientsCommand;
import seedu.address.logic.commands.ingredient.SearchIngredientCommand;
import seedu.address.logic.commands.recipe.AddRecipeCommand;
import seedu.address.logic.commands.recipe.ClearRecipeCommand;
import seedu.address.logic.commands.recipe.CloseCommand;
import seedu.address.logic.commands.recipe.DeleteRecipeCommand;
import seedu.address.logic.commands.recipe.EditRecipeCommand;
import seedu.address.logic.commands.recipe.ListRecipesCommand;
import seedu.address.logic.commands.recipe.RecommendCommand;
import seedu.address.logic.commands.recipe.SearchRecipeCommand;
import seedu.address.logic.commands.recipe.SelectRecipeCommand;
import seedu.address.logic.parser.consumption.DeleteConsumptionCommandParser;
import seedu.address.logic.parser.consumption.EatRecipeCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.ingredient.AddIngredientCommandParser;
import seedu.address.logic.parser.ingredient.DeleteIngredientCommandParser;
import seedu.address.logic.parser.ingredient.EditIngredientCommandParser;
import seedu.address.logic.parser.ingredient.GetEditIngredientCommandParser;
import seedu.address.logic.parser.ingredient.SearchIngredientCommandParser;
import seedu.address.logic.parser.recipe.AddRecipeCommandParser;
import seedu.address.logic.parser.recipe.DeleteRecipeCommandParser;
import seedu.address.logic.parser.recipe.EditRecipeCommandParser;
import seedu.address.logic.parser.recipe.GetEditRecipeCommandParser;
import seedu.address.logic.parser.recipe.SearchRecipeCommandParser;
import seedu.address.logic.parser.recipe.SelectRecipeCommandParser;

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
    public Command parseCommand(String userInput) throws ParseException, IOException, URISyntaxException {
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

        case SearchRecipeCommand.COMMAND_WORD:
            return new SearchRecipeCommandParser().parse(arguments);

        case SearchIngredientCommand.COMMAND_WORD:
            return new SearchIngredientCommandParser().parse(arguments);

        case SelectRecipeCommand.COMMAND_WORD:
            return new SelectRecipeCommandParser().parse(arguments);

        // Command with Empty Argument
        case ListIngredientsCommand.COMMAND_WORD:
        case ListConsumptionCommand.COMMAND_WORD:
        case ListRecipesCommand.COMMAND_WORD:
        case ClearRecipeCommand.COMMAND_WORD:
        case ClearIngredientCommand.COMMAND_WORD:
        case ClearConsumptionCommand.COMMAND_WORD:
        case CloseCommand.COMMAND_WORD:
        case RecommendCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_WORD:
        case HelpCommand.COMMAND_WORD:
            if (!arguments.isEmpty()) {

                String noArgumentCommandUsage = commandWord
                        + ": should not have any arguments\n"
                        + "Example: " + commandWord;

                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        noArgumentCommandUsage));
            }
            switch (commandWord) {
            case ListRecipesCommand.COMMAND_WORD:
                return new ListRecipesCommand();

            case ListIngredientsCommand.COMMAND_WORD:
                return new ListIngredientsCommand();

            case ListConsumptionCommand.COMMAND_WORD:
                return new ListConsumptionCommand();

            case ClearIngredientCommand.COMMAND_WORD:
                return new ClearIngredientCommand();

            case ClearRecipeCommand.COMMAND_WORD:
                return new ClearRecipeCommand();

            case ClearConsumptionCommand.COMMAND_WORD:
                return new ClearConsumptionCommand();

            case CloseCommand.COMMAND_WORD:
                return new CloseCommand();

            case RecommendCommand.COMMAND_WORD:
                return new RecommendCommand();

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

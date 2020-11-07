package seedu.address.logic.parser.ingredient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ingredient.EditIngredientCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.data.IngredientParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;

public class EditIngredientCommandParser implements Parser<EditIngredientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditIngredientCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INGREDIENT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditIngredientCommand.MESSAGE_USAGE), pe);
        }

        EditIngredientCommand.EditIngredientDescriptor editIngredientDescriptor =
                new EditIngredientCommand.EditIngredientDescriptor();
        if (argMultimap.getValue(PREFIX_INGREDIENT).isPresent()) {
            String ingredientString = ParserUtil.parseIngredient(argMultimap
                    .getValue(PREFIX_INGREDIENT).get());
            ArrayList<Ingredient> ingredients = IngredientParser.parse(ingredientString);
            editIngredientDescriptor.setIngredient(ingredients.get(0));
        }

        if (!editIngredientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditIngredientCommand.MESSAGE_NOT_EDITED);
        }

        return new EditIngredientCommand(index, editIngredientDescriptor);
    }

}

package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;

import java.util.stream.Stream;

import seedu.address.logic.commands.ingredientcommands.IngredientSetCommand;
import seedu.address.logic.commands.ingredientcommands.IngredientSetCommand.SetIngredientDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.IngredientName;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class IngredientSetCommandParser implements Parser<IngredientSetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public IngredientSetCommand parse(String args) throws ParseException {

        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INGREDIENT, PREFIX_AMOUNT);

        if (!arePrefixesPresent(argMultimap, PREFIX_INGREDIENT, PREFIX_AMOUNT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, IngredientSetCommand.MESSAGE_USAGE));
        }

        IngredientName ingredientToSet = ParserUtil.parseIngredientName(argMultimap.getValue(PREFIX_INGREDIENT).get());

        IngredientSetCommand.SetIngredientDescriptor descriptor = new SetIngredientDescriptor();

        if (argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            descriptor.setAmount(ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get()));
        }

        if (!descriptor.isAnyFieldEdited()) {
            throw new ParseException(IngredientSetCommand.MESSAGE_NO_CHANGE);
        }

        return new IngredientSetCommand(ingredientToSet, descriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

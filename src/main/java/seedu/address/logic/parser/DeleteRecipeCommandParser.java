package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_PRODUCT_NAME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteRecipeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteRecipeCommand object
 */
public class DeleteRecipeCommandParser implements Parser<DeleteRecipeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteRecipeCommand
     * and returns a DeleteRecipeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */

    public DeleteRecipeCommand parse(String args) throws ParseException {
        String productName;
        Index index;
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_RECIPE_PRODUCT_NAME, PREFIX_RECIPE_ID);
            if (!arePrefixesPresent(argMultimap, PREFIX_RECIPE_PRODUCT_NAME, PREFIX_RECIPE_ID)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteRecipeCommand.MESSAGE_USAGE));
            }
            productName = ItemParserUtil.parseName(argMultimap.getValue(PREFIX_RECIPE_PRODUCT_NAME).get());
            index = ItemParserUtil.parseIndex(argMultimap.getValue(PREFIX_RECIPE_ID).get());
            return new DeleteRecipeCommand(productName, index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteRecipeCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

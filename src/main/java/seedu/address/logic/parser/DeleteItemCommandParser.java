package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteItemCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteItemCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteRecipeCommand
     * and returns a DeleteRecipeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */

    public DeleteItemCommand parse(String args) throws ParseException {
        String productName;
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_ITEM_NAME);
            if (!arePrefixesPresent(argMultimap, PREFIX_ITEM_NAME)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteItemCommand.MESSAGE_USAGE));
            }
            productName = ItemParserUtil.parseName(argMultimap.getValue(PREFIX_ITEM_NAME).get());
            return new DeleteItemCommand(productName);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItemCommand.MESSAGE_USAGE), pe);
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

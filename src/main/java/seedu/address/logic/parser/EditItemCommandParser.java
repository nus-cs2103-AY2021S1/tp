package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.EditItemCommand.MESSAGE_NO_ORIGINAL_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORIGINAL_ITEM_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.EditItemCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditItemCommandParser implements Parser<EditItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditItemCommand
     * and returns an EditItemCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public EditItemCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ORIGINAL_ITEM_NAME, PREFIX_ITEM_NAME,
                        PREFIX_ITEM_QUANTITY, PREFIX_ITEM_DESCRIPTION, PREFIX_ITEM_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_ORIGINAL_ITEM_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditItemCommand.MESSAGE_USAGE));
        }
        String itemName;

        EditItemCommand.EditItemDescriptor editItemDescriptor = new EditItemCommand.EditItemDescriptor();
        if (argMultimap.getValue(PREFIX_ORIGINAL_ITEM_NAME).isPresent()) {
            itemName = ItemParserUtil.parseName(argMultimap.getValue(PREFIX_ORIGINAL_ITEM_NAME).get());
        } else {
            throw new ParseException(MESSAGE_NO_ORIGINAL_ITEM);
        }
        if (argMultimap.getValue(PREFIX_ITEM_NAME).isPresent()) {
            editItemDescriptor.setName(ItemParserUtil.parseName(argMultimap.getValue(PREFIX_ITEM_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_ITEM_QUANTITY).isPresent()) {
            editItemDescriptor.setQuantity(ItemParserUtil.parseQuantity(
                    argMultimap.getValue(PREFIX_ITEM_QUANTITY).get()));
        }
        if (argMultimap.getValue(PREFIX_ITEM_DESCRIPTION).isPresent()) {
            editItemDescriptor.setDescription(ItemParserUtil.parseDescription(
                    argMultimap.getValue(PREFIX_ITEM_DESCRIPTION).get()));
        }

        if (argMultimap.getValue(PREFIX_ITEM_TAG).isPresent()) {
            editItemDescriptor.setTags(ItemParserUtil.parseTags(
                    argMultimap.getAllValues(PREFIX_ITEM_TAG)));
        }

        return new EditItemCommand(itemName, editItemDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

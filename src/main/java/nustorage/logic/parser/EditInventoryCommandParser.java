package nustorage.logic.parser;

import static java.util.Objects.requireNonNull;
import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.parser.CliSyntax.PREFIX_ITEM_COST;
import static nustorage.logic.parser.CliSyntax.PREFIX_ITEM_DESCRIPTION;
import static nustorage.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static nustorage.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import nustorage.commons.core.index.Index;
import nustorage.logic.commands.EditInventoryCommand;
import nustorage.logic.commands.EditInventoryCommand.EditInventoryDescriptor;
import nustorage.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditInventoryCommand object.
 */
public class EditInventoryCommandParser implements Parser<EditInventoryCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditInventoryCommand
     * and returns an EditInventoryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditInventoryCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUANTITY, PREFIX_ITEM_DESCRIPTION,
                        PREFIX_ITEM_COST);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            if (pe.getMessage().equals(MESSAGE_INVALID_INDEX)) {
                throw pe;
            }
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditInventoryCommand.MESSAGE_USAGE), pe);
        }

        EditInventoryCommand.EditInventoryDescriptor editInventoryDescriptor = new EditInventoryDescriptor();

        if (argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            int quantity = ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get());
            editInventoryDescriptor.setQuantity(quantity);
        }

        if (argMultimap.getValue(PREFIX_ITEM_DESCRIPTION).isPresent()) {
            String description = ParserUtil.parseItemDescription(
                    argMultimap.getValue(PREFIX_ITEM_DESCRIPTION).get());
            editInventoryDescriptor.setDescription(description);
        }

        if (argMultimap.getValue(PREFIX_ITEM_COST).isPresent()) {
            editInventoryDescriptor.setUnitCost(ParserUtil.parseItemCost(
                    argMultimap.getValue(PREFIX_ITEM_COST).get()));
        }
        if (!editInventoryDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditInventoryCommand.MESSAGE_NOT_EDITED);
        }

        return new EditInventoryCommand(index, editInventoryDescriptor);
    }
}

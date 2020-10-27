package nustorage.logic.parser;

import nustorage.commons.core.index.Index;
import nustorage.logic.commands.UpdateInventoryCommand;
import nustorage.logic.commands.UpdateInventoryCommand.UpdateInventoryDescriptor;
import nustorage.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.parser.CliSyntax.PREFIX_QUANTITY;

public class UpdateInventoryCommandParser implements Parser<UpdateInventoryCommand> {

    public UpdateInventoryCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUANTITY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdateInventoryCommand.MESSAGE_USAGE), pe);
        }

        UpdateInventoryCommand.UpdateInventoryDescriptor updateInventoryDescriptor = new UpdateInventoryDescriptor();
        if (argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            updateInventoryDescriptor
                    .setChangeInQuantity(ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get()));
        }

        if (!updateInventoryDescriptor.isAnyFieldEdited()) {
            throw new ParseException(UpdateInventoryCommand.MESSAGE_NOT_UPDATED);
        }

        return new UpdateInventoryCommand(index, updateInventoryDescriptor);
    }
}

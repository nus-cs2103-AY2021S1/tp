package nustorage.logic.parser;

import static java.util.Objects.requireNonNull;
import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.parser.CliSyntax.PREFIX_QUANTITY;

import nustorage.commons.core.index.Index;
import nustorage.logic.commands.UpdateInventoryCommand;
import nustorage.logic.commands.UpdateInventoryCommand.UpdateInventoryDescriptor;
import nustorage.logic.parser.exceptions.ParseException;

public class UpdateInventoryCommandParser implements Parser<UpdateInventoryCommand> {

    /**
     *
     * Parses the given {@code String} of arguments in the context of the UpdateInventoryCommand
     * and returns an UpdateInventoryCommand object for execution.
     * @param args the string of arguments
     * @return the UpdateInventoryCommand for execution
     * @throws ParseException if the user input does not conform the expected format
     */
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

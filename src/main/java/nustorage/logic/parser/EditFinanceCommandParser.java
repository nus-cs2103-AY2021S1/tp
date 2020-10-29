package nustorage.logic.parser;

import static java.util.Objects.requireNonNull;
import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static nustorage.logic.parser.CliSyntax.PREFIX_DATETIME;

import nustorage.commons.core.index.Index;
import nustorage.logic.commands.EditFinanceCommand;
import nustorage.logic.commands.EditFinanceCommand.EditFinanceDescriptor;
import nustorage.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditInventoryCommand object.
 */
public class EditFinanceCommandParser implements Parser<EditFinanceCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditFinanceCommand
     * and returns an EditFinanceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditFinanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_AMOUNT, PREFIX_DATETIME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditFinanceCommand.MESSAGE_USAGE), pe);
        }

        EditFinanceDescriptor editFinanceDescriptor = new EditFinanceDescriptor();
        if (argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            editFinanceDescriptor.setAmount(ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get()));
        } else {
            throw new ParseException(EditFinanceCommand.MESSAGE_AMT_MISSING);
        }
        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            editFinanceDescriptor.setDatetime(ParserUtil.parseDatetime(
                    argMultimap.getValue(PREFIX_DATETIME).get()));
        }
        if (!editFinanceDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditFinanceCommand.MESSAGE_NOT_EDITED);
        }

        return new EditFinanceCommand(index, editFinanceDescriptor);
    }
}

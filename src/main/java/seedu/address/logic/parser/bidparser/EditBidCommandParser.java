package seedu.address.logic.parser.bidparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ID;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.bidcommands.EditBidCommand;
import seedu.address.logic.commands.bidcommands.EditBidCommand.EditBidDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.id.IdParserUtil;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditBidCommandParser implements Parser<EditBidCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditBidCommand
     * and returns an EditBidCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditBidCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PROPERTY_ID, PREFIX_CLIENT, PREFIX_MONEY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBidCommand.MESSAGE_USAGE), pe);
        }

        EditBidDescriptor editBidDescriptor = new EditBidDescriptor();
        if (argMultimap.getValue(PREFIX_PROPERTY_ID).isPresent()) {
            editBidDescriptor.setPropertyId(IdParserUtil.parsePropertyId(argMultimap.getValue(PREFIX_PROPERTY_ID)
                    .get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT).isPresent()) {
            editBidDescriptor.setBidderId(IdParserUtil.parseBidderId(argMultimap.getValue(PREFIX_CLIENT).get()));
        }
        if (argMultimap.getValue(PREFIX_MONEY).isPresent()) {
            editBidDescriptor.setBidAmount(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_MONEY).get()));
        }

        if (!editBidDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditBidCommand.MESSAGE_NOT_EDITED);
        }

        return new EditBidCommand(index, editBidDescriptor);
    }
}

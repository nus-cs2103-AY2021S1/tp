package seedu.address.logic.parser.sellerparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.person.seller.Seller.SELLER_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.sellercommands.EditSellerCommand;
import seedu.address.logic.commands.sellercommands.EditSellerCommand.EditSellerDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.id.SellerId;

/**
 * Parses input arguments and creates a new EditSellerCommand object
 */
public class EditSellerCommandParser implements Parser<EditSellerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditSellerCommand
     * and returns an EditSellerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditSellerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditSellerCommand.MESSAGE_USAGE), pe);
        }

        EditSellerDescriptor editSellerDescriptor = new EditSellerDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editSellerDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editSellerDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }

        if (!editSellerDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditSellerCommand.MESSAGE_NOT_EDITED);
        }

        editSellerDescriptor.setTag(SELLER_TAG);
        editSellerDescriptor.setSellerId(SellerId.DEFAULT_SELLER_ID);

        return new EditSellerCommand(index, editSellerDescriptor);
    }


}

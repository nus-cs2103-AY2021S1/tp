package seedu.address.logic.parser.bidderparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.person.bidder.Bidder.BIDDER_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.biddercommands.EditBidderCommand;
import seedu.address.logic.commands.biddercommands.EditBidderCommand.EditBidderDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.id.BidderId;

/**
 * Parses input arguments and creates a new EditBidderCommand object
 */
public class EditBidderCommandParser implements Parser<EditBidderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditBidderCommand
     * and returns an EditBidderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditBidderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBidderCommand.MESSAGE_USAGE), pe);
        }

        EditBidderDescriptor editBidderDescriptor = new EditBidderDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editBidderDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editBidderDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }

        if (!editBidderDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditBidderCommand.MESSAGE_NOT_EDITED);
        }

        editBidderDescriptor.setTag(BIDDER_TAG);
        editBidderDescriptor.setBidderId(BidderId.DEFAULT_BIDDER_ID);

        return new EditBidderCommand(index, editBidderDescriptor);
    }

}

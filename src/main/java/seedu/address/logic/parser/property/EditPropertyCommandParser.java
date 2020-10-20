package seedu.address.logic.parser.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_IS_RENTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_SELLER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.property.EditPropertyCommand;
import seedu.address.logic.commands.property.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.id.IdParserUtil;

/**
 * Parses input arguments and creates a new EditPropertyCommand object
 */
public class EditPropertyCommandParser implements Parser<EditPropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPropertyCommand
     * and returns an EditPropertyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPropertyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PROPERTY_NAME, PREFIX_PROPERTY_SELLER_ID,
                        PREFIX_PROPERTY_ADDRESS, PREFIX_PROPERTY_ASKING_PRICE, PREFIX_PROPERTY_TYPE,
                        PREFIX_PROPERTY_IS_RENTAL);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditPropertyCommand.MESSAGE_USAGE), pe);
        }

        EditPropertyDescriptor editPropertyDescriptor = new EditPropertyDescriptor();

        if (argMultimap.getValue(PREFIX_PROPERTY_NAME).isPresent()) {
            editPropertyDescriptor.setPropertyName(PropertyParserUtil
                    .parsePropertyName(argMultimap.getValue(PREFIX_PROPERTY_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PROPERTY_ADDRESS).isPresent()) {
            editPropertyDescriptor.setAddress(PropertyParserUtil
                    .parseAddress(argMultimap.getValue(PREFIX_PROPERTY_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_PROPERTY_SELLER_ID).isPresent()) {
            editPropertyDescriptor.setSellerId(IdParserUtil
                    .parseSellerId(argMultimap.getValue(PREFIX_PROPERTY_SELLER_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_PROPERTY_ASKING_PRICE).isPresent()) {
            editPropertyDescriptor.setAskingPrice(ParserUtil
                    .parsePrice(argMultimap.getValue(PREFIX_PROPERTY_ASKING_PRICE).get()));
        }
        if (argMultimap.getValue(PREFIX_PROPERTY_TYPE).isPresent()) {
            editPropertyDescriptor.setPropertyType(PropertyParserUtil
                    .parsePropertyType(argMultimap.getValue(PREFIX_PROPERTY_TYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_PROPERTY_IS_RENTAL).isPresent()) {
            editPropertyDescriptor.setIsRental(PropertyParserUtil
                    .parseIsRental(argMultimap.getValue(PREFIX_PROPERTY_IS_RENTAL).get()));
        }

        if (!editPropertyDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPropertyCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPropertyCommand(index, editPropertyDescriptor);
    }
}

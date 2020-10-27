package seedu.address.logic.parser.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_IS_CLOSED_DEAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_IS_RENTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_PROPERTY_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_SELLER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;

import seedu.address.logic.commands.property.FindPropertyCommand;
import seedu.address.logic.commands.property.FindPropertyCommand.FindPropertyDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.AskingPricePredicate;
import seedu.address.model.property.PropertyAddressContainsKeywordsPredicate;
import seedu.address.model.property.PropertyIdContainsKeywordsPredicate;
import seedu.address.model.property.PropertyIsClosedDealPredicate;
import seedu.address.model.property.PropertyIsRentalPredicate;
import seedu.address.model.property.PropertyNameContainsKeywordsPredicate;
import seedu.address.model.property.PropertyTypeContainsKeywordsPredicate;
import seedu.address.model.property.SellerIdContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindPropertyCommand object
 */
public class FindPropertyCommandParser implements Parser<FindPropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPropertyCommand
     * and returns an FindPropertyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPropertyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_PROPERTY_PROPERTY_ID,
                        PREFIX_PROPERTY_NAME,
                        PREFIX_PROPERTY_ADDRESS,
                        PREFIX_PROPERTY_SELLER_ID,
                        PREFIX_PROPERTY_TYPE,
                        PREFIX_PROPERTY_ASKING_PRICE,
                        PREFIX_PROPERTY_IS_RENTAL,
                        PREFIX_PROPERTY_IS_CLOSED_DEAL);

        FindPropertyDescriptor findPropertyDescriptor = new FindPropertyDescriptor();
        if (argMultimap.getValue(PREFIX_PROPERTY_PROPERTY_ID).isPresent()) {
            findPropertyDescriptor.setPropertyIdPredicate(new PropertyIdContainsKeywordsPredicate(
                    PropertyParserUtil
                    .parseKeywords(argMultimap.getValue(PREFIX_PROPERTY_PROPERTY_ID).get())));
        }
        if (argMultimap.getValue(PREFIX_PROPERTY_NAME).isPresent()) {
            findPropertyDescriptor.setPropertyNameContainsKeywordsPredicate(
                    new PropertyNameContainsKeywordsPredicate(
                            PropertyParserUtil.parseKeywords(argMultimap.getValue(PREFIX_PROPERTY_NAME).get())
                    )
            );
        }
        if (argMultimap.getValue(PREFIX_PROPERTY_ADDRESS).isPresent()) {
            findPropertyDescriptor.setPropertyAddressContainsKeywordsPredicate(
                    new PropertyAddressContainsKeywordsPredicate(
                    PropertyParserUtil
                            .parseKeywords(argMultimap.getValue(PREFIX_PROPERTY_ADDRESS).get())));
        }
        if (argMultimap.getValue(PREFIX_PROPERTY_SELLER_ID).isPresent()) {
            findPropertyDescriptor.setSellerIdContainsKeywordsPredicate(
                    new SellerIdContainsKeywordsPredicate(
                    PropertyParserUtil
                            .parseKeywords(argMultimap.getValue(PREFIX_PROPERTY_SELLER_ID).get())));
        }
        if (argMultimap.getValue(PREFIX_PROPERTY_TYPE).isPresent()) {
            findPropertyDescriptor.setPropertyTypeContainsKeywordsPredicate(
                    new PropertyTypeContainsKeywordsPredicate(
                    PropertyParserUtil
                            .parseKeywords(argMultimap.getValue(PREFIX_PROPERTY_TYPE).get())));
        }
        if (argMultimap.getValue(PREFIX_PROPERTY_ASKING_PRICE).isPresent()) {
            findPropertyDescriptor.setAskingPricePredicate(new AskingPricePredicate(
                    PropertyParserUtil
                            .parsePriceFilter(argMultimap.getValue(PREFIX_PROPERTY_ASKING_PRICE).get())));
        }
        if (argMultimap.getValue(PREFIX_PROPERTY_IS_RENTAL).isPresent()) {
            findPropertyDescriptor.setIsRentalPredicate(
                    new PropertyIsRentalPredicate(
                            PropertyParserUtil
                                    .parseIsRental(argMultimap.getValue(PREFIX_PROPERTY_IS_RENTAL).get())));
        }
        if (argMultimap.getValue(PREFIX_PROPERTY_IS_CLOSED_DEAL).isPresent()) {
            findPropertyDescriptor.setIsClosedDealPredicate(
                    new PropertyIsClosedDealPredicate(
                            PropertyParserUtil
                                    .parseIsClosedDeal(argMultimap.getValue(PREFIX_PROPERTY_IS_CLOSED_DEAL).get())));
        }

        if (!findPropertyDescriptor.isAnyFieldFound()) {
            throw new ParseException(FindPropertyCommand.MESSAGE_NO_FILTERS);
        }

        return new FindPropertyCommand(findPropertyDescriptor);
    }
}

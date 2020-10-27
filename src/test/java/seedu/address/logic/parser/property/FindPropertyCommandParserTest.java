package seedu.address.logic.parser.property;

import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_ASKING_PRICE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_IS_CLOSED_DEAL;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_IS_RENTAL;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_ADDRESS_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_ASKING_PRICE_DESC_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_ID_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_IS_CLOSED_DEAL_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_IS_RENTAL_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_IS_RENTAL_DESC_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_NAME_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_NAME_DESC_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_PRICE_FILTER_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_SELLER_ID_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_TYPE_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_TYPE_DESC_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_ADDRESS_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_IS_RENTAL_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_IS_RENTAL_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_NAME_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_NAME_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_PRICE_FILTER_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_PROPERTY_ID_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_PROPERTY_TYPE_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_SELLER_ID_ANCHORVALE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.property.FindPropertyCommand;
import seedu.address.logic.commands.property.FindPropertyCommand.FindPropertyDescriptor;
import seedu.address.model.price.PriceFilter;
import seedu.address.model.property.IsClosedDeal;
import seedu.address.model.property.IsRental;
import seedu.address.testutil.property.FindPropertyDescriptorBuilder;

public class FindPropertyCommandParserTest {

    private FindPropertyCommandParser parser = new FindPropertyCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // no field specified
        assertParseFailure(parser, "", FindPropertyCommand.MESSAGE_NO_FILTERS);

    }

    @Test
    public void parse_invalidPreamble_failure() {

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", FindPropertyCommand.MESSAGE_NO_FILTERS);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", FindPropertyCommand.MESSAGE_NO_FILTERS);
    }

    @Test
    public void parse_invalidValue_failure() {

        // only for askingPrice, isRental, isClosedDeal

        // invalid asking price
        assertParseFailure(parser, INVALID_PROPERTY_ASKING_PRICE, PriceFilter.MESSAGE_CONSTRAINTS);

        // invalid is rental
        assertParseFailure(parser, INVALID_PROPERTY_IS_RENTAL, IsRental.MESSAGE_CONSTRAINTS);

        // invalid isClosedDeal
        assertParseFailure(parser, INVALID_PROPERTY_IS_CLOSED_DEAL, IsClosedDeal.MESSAGE_CONSTRAINTS);

        // invalid askingPrice followed by valid address
        assertParseFailure(parser, INVALID_PROPERTY_ASKING_PRICE + VALID_PROPERTY_ADDRESS_ANCHORVALE,
                PriceFilter.MESSAGE_CONSTRAINTS);

        // valid isRental followed by invalid isRental.
        assertParseFailure(parser, PROPERTY_IS_RENTAL_DESC_ANCHORVALE + INVALID_PROPERTY_IS_RENTAL,
                IsRental.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_PROPERTY_ASKING_PRICE + INVALID_PROPERTY_IS_CLOSED_DEAL,
                PriceFilter.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = PROPERTY_NAME_DESC_ANCHORVALE
                + PROPERTY_ADDRESS_DESC_ANCHORVALE
                + PROPERTY_PRICE_FILTER_DESC_ANCHORVALE
                + PROPERTY_SELLER_ID_DESC_ANCHORVALE
                + PROPERTY_TYPE_DESC_ANCHORVALE
                + PROPERTY_IS_RENTAL_DESC_ANCHORVALE
                + PROPERTY_IS_CLOSED_DEAL_DESC_ANCHORVALE
                + PROPERTY_ID_DESC_ANCHORVALE;

        FindPropertyDescriptor descriptor = new FindPropertyDescriptorBuilder()
                .withPropertyNamePredicate(VALID_PROPERTY_NAME_ANCHORVALE)
                .withAddressPredicate(VALID_PROPERTY_ADDRESS_ANCHORVALE)
                .withAskingPricePredicate(VALID_PROPERTY_PRICE_FILTER_ANCHORVALE)
                .withSellerIdPredicate(VALID_PROPERTY_SELLER_ID_ANCHORVALE)
                .withPropertyTypePredicate(VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE)
                .withIsRentalPredicate(VALID_PROPERTY_IS_RENTAL_ANCHORVALE)
                .withIsClosedDealPredicate(VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE)
                .withPropertyIdPredicate(VALID_PROPERTY_PROPERTY_ID_ANCHORVALE)
                .build();
        FindPropertyCommand expectedCommand = new FindPropertyCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = PROPERTY_NAME_DESC_BEDOK + PROPERTY_TYPE_DESC_BEDOK;
        FindPropertyDescriptor descriptor = new FindPropertyDescriptorBuilder()
                .withPropertyNamePredicate(VALID_PROPERTY_NAME_BEDOK)
                .withPropertyTypePredicate(VALID_PROPERTY_PROPERTY_TYPE_BEDOK)
                .build();
        FindPropertyCommand expectedCommand = new FindPropertyCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {

        // property name
        String userInput = PROPERTY_NAME_DESC_ANCHORVALE;
        FindPropertyDescriptor descriptor = new FindPropertyDescriptorBuilder()
                .withPropertyNamePredicate(VALID_PROPERTY_NAME_ANCHORVALE)
                .build();
        FindPropertyCommand expectedCommand = new FindPropertyCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = PROPERTY_ADDRESS_DESC_ANCHORVALE;
        descriptor = new FindPropertyDescriptorBuilder()
                .withAddressPredicate(VALID_PROPERTY_ADDRESS_ANCHORVALE)
                .build();
        expectedCommand = new FindPropertyCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // seller id
        userInput = PROPERTY_SELLER_ID_DESC_ANCHORVALE;
        descriptor = new FindPropertyDescriptorBuilder()
                .withSellerIdPredicate(VALID_PROPERTY_SELLER_ID_ANCHORVALE)
                .build();
        expectedCommand = new FindPropertyCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // asking price
        userInput = PROPERTY_PRICE_FILTER_DESC_ANCHORVALE;
        descriptor = new FindPropertyDescriptorBuilder()
                .withAskingPricePredicate(VALID_PROPERTY_PRICE_FILTER_ANCHORVALE)
                .build();
        expectedCommand = new FindPropertyCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // property type
        userInput = PROPERTY_TYPE_DESC_ANCHORVALE;
        descriptor = new FindPropertyDescriptorBuilder()
                .withPropertyTypePredicate(VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE)
                .build();
        expectedCommand = new FindPropertyCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // is rental
        userInput = PROPERTY_IS_RENTAL_DESC_ANCHORVALE;
        descriptor = new FindPropertyDescriptorBuilder()
                .withIsRentalPredicate(VALID_PROPERTY_IS_RENTAL_ANCHORVALE).build();
        expectedCommand = new FindPropertyCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // is closed deal
        userInput = PROPERTY_IS_CLOSED_DEAL_DESC_ANCHORVALE;
        descriptor = new FindPropertyDescriptorBuilder()
                .withIsClosedDealPredicate(VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE).build();
        expectedCommand = new FindPropertyCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // property id
        userInput = PROPERTY_ID_DESC_ANCHORVALE;
        descriptor = new FindPropertyDescriptorBuilder()
                .withPropertyIdPredicate(VALID_PROPERTY_PROPERTY_ID_ANCHORVALE)
                .build();
        expectedCommand = new FindPropertyCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = PROPERTY_TYPE_DESC_ANCHORVALE
                + PROPERTY_IS_RENTAL_DESC_ANCHORVALE + PROPERTY_TYPE_DESC_ANCHORVALE
                + PROPERTY_IS_RENTAL_DESC_ANCHORVALE + PROPERTY_TYPE_DESC_BEDOK + PROPERTY_IS_RENTAL_DESC_BEDOK;

        FindPropertyDescriptor descriptor = new FindPropertyDescriptorBuilder()
                .withPropertyTypePredicate(VALID_PROPERTY_PROPERTY_TYPE_BEDOK)
                .withIsRentalPredicate(VALID_PROPERTY_IS_RENTAL_BEDOK).build();
        FindPropertyCommand expectedCommand = new FindPropertyCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_fail() {

        String userInput = INVALID_PROPERTY_ASKING_PRICE + PROPERTY_ASKING_PRICE_DESC_BEDOK;
        assertParseFailure(parser, userInput, PriceFilter.MESSAGE_CONSTRAINTS);

    }

}

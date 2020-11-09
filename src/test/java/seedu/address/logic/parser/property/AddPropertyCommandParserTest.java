package seedu.address.logic.parser.property;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_ASKING_PRICE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_IS_RENTAL;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_NAME;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_PROPERTY_TYPE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_SELLER_ID;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_ADDRESS_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_ADDRESS_DESC_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_ASKING_PRICE_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_ASKING_PRICE_DESC_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_IS_RENTAL_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_IS_RENTAL_DESC_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_NAME_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_NAME_DESC_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_SELLER_ID_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_SELLER_ID_DESC_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_TYPE_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_TYPE_DESC_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_ADDRESS_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_ASKING_PRICE_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_IS_RENTAL_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_NAME_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_PROPERTY_TYPE_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_SELLER_ID_BEDOK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.id.PropertyId.DEFAULT_PROPERTY_ID;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.property.AddPropertyCommand;
import seedu.address.model.id.SellerId;
import seedu.address.model.price.Price;
import seedu.address.model.property.IsRental;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;
import seedu.address.model.property.PropertyType;
import seedu.address.testutil.property.PropertyBuilder;

public class AddPropertyCommandParserTest {
    private AddPropertyCommandParser parser = new AddPropertyCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Property expectedProperty = new PropertyBuilder()
                .withPropertyId(DEFAULT_PROPERTY_ID.toString())
                .withPropertyName(VALID_PROPERTY_NAME_BEDOK)
                .withAddress(VALID_PROPERTY_ADDRESS_BEDOK)
                .withPropertyType(VALID_PROPERTY_PROPERTY_TYPE_BEDOK)
                .withAskingPrice(VALID_PROPERTY_ASKING_PRICE_BEDOK)
                .withIsClosedDeal("Active")
                .withIsRental(VALID_PROPERTY_IS_RENTAL_BEDOK)
                .withSellerId(VALID_PROPERTY_SELLER_ID_BEDOK)
                .build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                        + PROPERTY_NAME_DESC_BEDOK
                        + PROPERTY_ADDRESS_DESC_BEDOK
                        + PROPERTY_TYPE_DESC_BEDOK
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + PROPERTY_SELLER_ID_DESC_BEDOK,
                new AddPropertyCommand(expectedProperty));

        // multiple property names - last property name accepted
        assertParseSuccess(parser,
                PROPERTY_NAME_DESC_ANCHORVALE
                + PROPERTY_NAME_DESC_BEDOK
                        + PROPERTY_ADDRESS_DESC_BEDOK
                        + PROPERTY_TYPE_DESC_BEDOK
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + PROPERTY_SELLER_ID_DESC_BEDOK,
                new AddPropertyCommand(expectedProperty));

        // multiple addresses - last address accepted
        assertParseSuccess(parser,
                PROPERTY_NAME_DESC_BEDOK
                        + PROPERTY_ADDRESS_DESC_ANCHORVALE
                        + PROPERTY_ADDRESS_DESC_BEDOK
                        + PROPERTY_TYPE_DESC_BEDOK
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + PROPERTY_SELLER_ID_DESC_BEDOK,
                new AddPropertyCommand(expectedProperty));

        // multiple property types - last property type accepted
        assertParseSuccess(parser,
                PROPERTY_NAME_DESC_BEDOK
                        + PROPERTY_ADDRESS_DESC_BEDOK
                        + PROPERTY_TYPE_DESC_ANCHORVALE
                        + PROPERTY_TYPE_DESC_BEDOK
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + PROPERTY_SELLER_ID_DESC_BEDOK,
                new AddPropertyCommand(expectedProperty));

        // multiple asking prices - last asking price accepted
        assertParseSuccess(parser,
                PROPERTY_NAME_DESC_BEDOK
                        + PROPERTY_ADDRESS_DESC_BEDOK
                        + PROPERTY_TYPE_DESC_BEDOK
                        + PROPERTY_ASKING_PRICE_DESC_ANCHORVALE
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + PROPERTY_SELLER_ID_DESC_BEDOK,
                new AddPropertyCommand(expectedProperty));

        // multiple isRental - last isRental accepted
        assertParseSuccess(parser,
                PROPERTY_NAME_DESC_BEDOK
                        + PROPERTY_ADDRESS_DESC_BEDOK
                        + PROPERTY_TYPE_DESC_BEDOK
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + PROPERTY_IS_RENTAL_DESC_ANCHORVALE
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + PROPERTY_SELLER_ID_DESC_BEDOK,
                new AddPropertyCommand(expectedProperty));

        // multiple sellerId - last sellerId accepted
        assertParseSuccess(parser,
                PROPERTY_NAME_DESC_BEDOK
                        + PROPERTY_ADDRESS_DESC_BEDOK
                        + PROPERTY_TYPE_DESC_BEDOK
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + PROPERTY_SELLER_ID_DESC_ANCHORVALE
                        + PROPERTY_SELLER_ID_DESC_BEDOK,
                new AddPropertyCommand(expectedProperty));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE);

        // missing property name prefix
        assertParseFailure(parser, VALID_PROPERTY_NAME_BEDOK
                + PROPERTY_ADDRESS_DESC_BEDOK
                + PROPERTY_TYPE_DESC_BEDOK
                + PROPERTY_ASKING_PRICE_DESC_BEDOK
                + PROPERTY_IS_RENTAL_DESC_BEDOK
                + PROPERTY_SELLER_ID_DESC_BEDOK,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, PROPERTY_NAME_DESC_BEDOK
                        + VALID_PROPERTY_ADDRESS_BEDOK
                        + PROPERTY_TYPE_DESC_BEDOK
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + PROPERTY_SELLER_ID_DESC_BEDOK,
                expectedMessage);

        // missing property type prefix
        assertParseFailure(parser, PROPERTY_ADDRESS_DESC_BEDOK
                        + VALID_PROPERTY_PROPERTY_TYPE_BEDOK
                        + PROPERTY_NAME_DESC_BEDOK
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + PROPERTY_SELLER_ID_DESC_BEDOK,
                expectedMessage);

        // missing asking price prefix
        assertParseFailure(parser, PROPERTY_ADDRESS_DESC_BEDOK
                + VALID_PROPERTY_ASKING_PRICE_BEDOK
                        + PROPERTY_TYPE_DESC_BEDOK
                        + PROPERTY_NAME_DESC_BEDOK
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + PROPERTY_SELLER_ID_DESC_BEDOK,
                expectedMessage);

        // missing isRental prefix
        assertParseFailure(parser, PROPERTY_ADDRESS_DESC_BEDOK
                        + PROPERTY_TYPE_DESC_BEDOK
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + PROPERTY_NAME_DESC_BEDOK
                        + VALID_PROPERTY_IS_RENTAL_BEDOK
                        + PROPERTY_SELLER_ID_DESC_BEDOK,
                expectedMessage);

        // missing sellerId prefix
        assertParseFailure(parser, PROPERTY_ADDRESS_DESC_BEDOK
                        + PROPERTY_TYPE_DESC_BEDOK
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + VALID_PROPERTY_SELLER_ID_BEDOK
                        + PROPERTY_NAME_DESC_BEDOK,
                expectedMessage);

        // missing all prefix
        assertParseFailure(parser, VALID_PROPERTY_ADDRESS_BEDOK
                        + VALID_PROPERTY_PROPERTY_TYPE_BEDOK
                        + VALID_PROPERTY_ASKING_PRICE_BEDOK
                        + VALID_PROPERTY_IS_RENTAL_BEDOK
                        + VALID_PROPERTY_SELLER_ID_BEDOK
                        + VALID_PROPERTY_NAME_BEDOK,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid property name
        assertParseFailure(parser, INVALID_PROPERTY_NAME
                        + PROPERTY_ADDRESS_DESC_BEDOK
                        + PROPERTY_TYPE_DESC_BEDOK
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + PROPERTY_SELLER_ID_DESC_BEDOK,
                PropertyName.MESSAGE_CONSTRAINTS);

        // invalid property type
        assertParseFailure(parser, PROPERTY_NAME_DESC_BEDOK
                        + PROPERTY_ADDRESS_DESC_BEDOK
                        + INVALID_PROPERTY_PROPERTY_TYPE
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + PROPERTY_SELLER_ID_DESC_BEDOK,
                PropertyType.MESSAGE_CONSTRAINTS);

        // invalid asking price
        assertParseFailure(parser, PROPERTY_NAME_DESC_BEDOK
                        + PROPERTY_ADDRESS_DESC_BEDOK
                        + PROPERTY_TYPE_DESC_BEDOK
                        + INVALID_PROPERTY_ASKING_PRICE
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + PROPERTY_SELLER_ID_DESC_BEDOK,
                Price.MESSAGE_CONSTRAINTS);

        // invalid isRental
        assertParseFailure(parser, PROPERTY_NAME_DESC_BEDOK
                        + PROPERTY_ADDRESS_DESC_BEDOK
                        + PROPERTY_TYPE_DESC_BEDOK
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + INVALID_PROPERTY_IS_RENTAL
                        + PROPERTY_SELLER_ID_DESC_BEDOK,
                IsRental.MESSAGE_CONSTRAINTS);

        // invalid seller id
        assertParseFailure(parser, PROPERTY_NAME_DESC_BEDOK
                        + PROPERTY_ADDRESS_DESC_BEDOK
                        + PROPERTY_TYPE_DESC_BEDOK
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + INVALID_PROPERTY_SELLER_ID,
                SellerId.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_PROPERTY_NAME
                        + PROPERTY_ADDRESS_DESC_BEDOK
                        + PROPERTY_TYPE_DESC_BEDOK
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + INVALID_PROPERTY_SELLER_ID,
                PropertyName.MESSAGE_CONSTRAINTS);

        // non empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY
                        + PROPERTY_NAME_DESC_BEDOK
                        + PROPERTY_ADDRESS_DESC_BEDOK
                        + PROPERTY_TYPE_DESC_BEDOK
                        + PROPERTY_ASKING_PRICE_DESC_BEDOK
                        + PROPERTY_IS_RENTAL_DESC_BEDOK
                        + PROPERTY_SELLER_ID_DESC_BEDOK,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE));

    }
}

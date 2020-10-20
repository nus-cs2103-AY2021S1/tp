package seedu.address.logic.parser.property;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_ASKING_PRICE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_IS_RENTAL;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_NAME;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_PROPERTY_TYPE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_SELLER_ID;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_ADDRESS_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_ASKING_PRICE_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_IS_RENTAL_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_IS_RENTAL_DESC_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_NAME_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_NAME_DESC_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_SELLER_ID_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_TYPE_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_TYPE_DESC_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_ADDRESS_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_ASKING_PRICE_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_IS_RENTAL_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_IS_RENTAL_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_NAME_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_NAME_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_PROPERTY_TYPE_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_SELLER_ID_ANCHORVALE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROPERTY;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PROPERTY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.property.EditPropertyCommand;
import seedu.address.logic.commands.property.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.model.id.SellerId;
import seedu.address.model.price.Price;
import seedu.address.model.property.IsRental;
import seedu.address.model.property.PropertyName;
import seedu.address.model.property.PropertyType;
import seedu.address.testutil.property.EditPropertyDescriptorBuilder;

public class EditPropertyCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPropertyCommand.MESSAGE_USAGE);

    private EditPropertyCommandParser parser = new EditPropertyCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_PROPERTY_NAME_ANCHORVALE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditPropertyCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + PROPERTY_NAME_DESC_ANCHORVALE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + PROPERTY_NAME_DESC_ANCHORVALE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid property name
        assertParseFailure(parser, "1" + INVALID_PROPERTY_NAME, PropertyName.MESSAGE_CONSTRAINTS);

        // invalid seller id
        assertParseFailure(parser, "1" + INVALID_PROPERTY_SELLER_ID, SellerId.MESSAGE_CONSTRAINTS);

        // invalid asking price
        assertParseFailure(parser, "1" + INVALID_PROPERTY_ASKING_PRICE, Price.MESSAGE_CONSTRAINTS);

        // invalid property type
        assertParseFailure(parser, "1" + INVALID_PROPERTY_PROPERTY_TYPE, PropertyType.MESSAGE_CONSTRAINTS);

        // invalid is rental
        assertParseFailure(parser, "1" + INVALID_PROPERTY_IS_RENTAL, IsRental.MESSAGE_CONSTRAINTS);

        // invalid property name followed by valid address
        assertParseFailure(parser, "1" + INVALID_PROPERTY_NAME + VALID_PROPERTY_ADDRESS_ANCHORVALE,
                PropertyName.MESSAGE_CONSTRAINTS);

        // valid property name followed by invalid property name. The test case for invalid property name followed
        // by valid property name is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PROPERTY_NAME_DESC_ANCHORVALE + INVALID_PROPERTY_NAME,
                PropertyName.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_PROPERTY_NAME + INVALID_PROPERTY_ASKING_PRICE,
                PropertyName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PROPERTY;
        String userInput = targetIndex.getOneBased() + PROPERTY_NAME_DESC_ANCHORVALE
                + PROPERTY_ADDRESS_DESC_ANCHORVALE
                + PROPERTY_ASKING_PRICE_DESC_ANCHORVALE
                + PROPERTY_SELLER_ID_DESC_ANCHORVALE
                + PROPERTY_TYPE_DESC_ANCHORVALE
                + PROPERTY_IS_RENTAL_DESC_ANCHORVALE;

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder()
                .withPropertyName(VALID_PROPERTY_NAME_ANCHORVALE)
                .withAddress(VALID_PROPERTY_ADDRESS_ANCHORVALE)
                .withAskingPrice(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE)
                .withSellerId(VALID_PROPERTY_SELLER_ID_ANCHORVALE)
                .withPropertyType(VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE)
                .withIsRental(VALID_PROPERTY_IS_RENTAL_ANCHORVALE)
                .build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PROPERTY;
        String userInput = targetIndex.getOneBased() + PROPERTY_NAME_DESC_BEDOK;

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder()
                .withPropertyName(VALID_PROPERTY_NAME_BEDOK)
                .build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // property name
        Index targetIndex = INDEX_THIRD_PROPERTY;
        String userInput = targetIndex.getOneBased() + PROPERTY_NAME_DESC_ANCHORVALE;
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder()
                .withPropertyName(VALID_PROPERTY_NAME_ANCHORVALE).build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + PROPERTY_ADDRESS_DESC_ANCHORVALE;
        descriptor = new EditPropertyDescriptorBuilder().withAddress(VALID_PROPERTY_ADDRESS_ANCHORVALE).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // seller id
        userInput = targetIndex.getOneBased() + PROPERTY_SELLER_ID_DESC_ANCHORVALE;
        descriptor = new EditPropertyDescriptorBuilder().withSellerId(VALID_PROPERTY_SELLER_ID_ANCHORVALE).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // asking price
        userInput = targetIndex.getOneBased() + PROPERTY_ASKING_PRICE_DESC_ANCHORVALE;
        descriptor = new EditPropertyDescriptorBuilder().withAskingPrice(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE)
                .build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // property type
        userInput = targetIndex.getOneBased() + PROPERTY_TYPE_DESC_ANCHORVALE;
        descriptor = new EditPropertyDescriptorBuilder().withPropertyType(VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE)
                .build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // is rental
        userInput = targetIndex.getOneBased() + PROPERTY_IS_RENTAL_DESC_ANCHORVALE;
        descriptor = new EditPropertyDescriptorBuilder().withIsRental(VALID_PROPERTY_IS_RENTAL_ANCHORVALE).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PROPERTY;
        String userInput = targetIndex.getOneBased() + PROPERTY_TYPE_DESC_ANCHORVALE
                + PROPERTY_IS_RENTAL_DESC_ANCHORVALE + PROPERTY_TYPE_DESC_ANCHORVALE
                + PROPERTY_IS_RENTAL_DESC_ANCHORVALE + PROPERTY_TYPE_DESC_BEDOK + PROPERTY_IS_RENTAL_DESC_BEDOK;

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder()
                .withPropertyType(VALID_PROPERTY_PROPERTY_TYPE_BEDOK)
                .withIsRental(VALID_PROPERTY_IS_RENTAL_BEDOK).build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {

        Index targetIndex = INDEX_FIRST_PROPERTY;
        String userInput = targetIndex.getOneBased() + INVALID_PROPERTY_NAME + PROPERTY_NAME_DESC_BEDOK;
        EditPropertyCommand.EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder()
                .withPropertyName(VALID_PROPERTY_NAME_BEDOK).build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

}

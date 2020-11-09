package seedu.address.logic.parser.property;

import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_ADDRESS_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_ASKING_PRICE_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_ID_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_IS_RENTAL_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_NAME_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_PRICE_FILTER_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_SELLER_ID_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_TYPE_DESC_ANCHORVALE;

import seedu.address.logic.commands.property.AddPropertyCommand;
import seedu.address.logic.commands.property.EditPropertyCommand;
import seedu.address.logic.commands.property.FindPropertyCommand;

/** Util class for preparing a user input for testing. */
public class PropertyCommandInputBuilder {

    public final String commandWord;
    public final String index;
    public final String propertyName;
    public final String address;
    public final String propertyType;
    public final String sellerId;
    public final String askingPrice;
    public final String isRental;
    public final String propertyId;

    /**
     * Constructor.
     *
     * @param commandWord The command word.
     * @param index The index.
     * @param propertyName The property name.
     * @param address The address.
     * @param propertyType The property type.
     * @param sellerId The seller id.
     * @param askingPrice The asking price.
     * @param isRental The is rental.
     * @param propertyId The property id.
     */
    public PropertyCommandInputBuilder(String commandWord, String index, String propertyName, String address,
                                       String propertyType, String sellerId, String askingPrice,
                                       String isRental, String propertyId) {
        this.commandWord = commandWord;
        this.index = index;
        this.propertyName = propertyName;
        this.address = address;
        this.propertyType = propertyType;
        this.sellerId = sellerId;
        this.askingPrice = askingPrice;
        this.isRental = isRental;
        this.propertyId = propertyId;
    }

    /**
     * Constructs the {@code PropertyCommandInputBuilder} with no inputs.
     */
    public PropertyCommandInputBuilder() {
        this.commandWord = "";
        this.index = "";
        this.propertyName = "";
        this.address = "";
        this.propertyType = "";
        this.sellerId = "";
        this.askingPrice = "";
        this.isRental = "";
        this.propertyId = "";
    }

    /**
     * Builds the string user input.
     *
     * @return The user input.
     */
    public String build() {
        return commandWord + " " + index + propertyId + propertyName + address + propertyType
                + sellerId + askingPrice + isRental;
    }

    /** Returns a valid input for AddPropertyCommand. */
    public static PropertyCommandInputBuilder getValidAddPropertyCommandInput() {
        return new PropertyCommandInputBuilder(AddPropertyCommand.COMMAND_WORD,
                "",
                PROPERTY_NAME_DESC_ANCHORVALE, PROPERTY_ADDRESS_DESC_ANCHORVALE, PROPERTY_TYPE_DESC_ANCHORVALE,
                PROPERTY_SELLER_ID_DESC_ANCHORVALE, PROPERTY_ASKING_PRICE_DESC_ANCHORVALE,
                PROPERTY_IS_RENTAL_DESC_ANCHORVALE, "");
    }
    /** Returns a valid input for EditPropertyCommand. */
    public static PropertyCommandInputBuilder getValidEditPropertyCommand() {
        return getValidAddPropertyCommandInput()
                .withCommandWord(EditPropertyCommand.COMMAND_WORD)
                .withIndex("1");
    }

    /** Returns a valid input for FindPropertyCommand. */
    public static PropertyCommandInputBuilder getValidFindPropertyCommand() {
        return getValidAddPropertyCommandInput()
                .withCommandWord(FindPropertyCommand.COMMAND_WORD)
                .withPropertyId(PROPERTY_ID_DESC_ANCHORVALE)
                .withAskingPrice(PROPERTY_PRICE_FILTER_DESC_ANCHORVALE);
    }

    /** Returns a new PropertyCommandInputBuilder with the specified command word. */
    public PropertyCommandInputBuilder withCommandWord(String commandWord) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, propertyId);
    }

    /** Returns a new PropertyCommandInputBuilder with the specified index. */
    public PropertyCommandInputBuilder withIndex(String index) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, propertyId);
    }

    /** Returns a new PropertyCommandInputBuilder with the specified property name. */
    public PropertyCommandInputBuilder withPropertyName(String propertyName) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, propertyId);
    }

    /** Returns a new PropertyCommandInputBuilder with the specified address. */
    public PropertyCommandInputBuilder withAddress(String address) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, propertyId);
    }

    /** Returns a new PropertyCommandInputBuilder with the specified property type. */
    public PropertyCommandInputBuilder withPropertyType(String propertyType) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, propertyId);
    }

    /** Returns a new PropertyCommandInputBuilder with the specified seller id. */
    public PropertyCommandInputBuilder withSellerId(String sellerId) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, propertyId);
    }
    /** Returns a new PropertyCommandInputBuilder with the specified asking price. */
    public PropertyCommandInputBuilder withAskingPrice(String askingPrice) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, propertyId);
    }

    /** Returns a new PropertyCommandInputBuilder with the specified is rental. */
    public PropertyCommandInputBuilder withIsRental(String isRental) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, propertyId);
    }

    /** Returns a new PropertyCommandInputBuilder with the specified property id. */
    public PropertyCommandInputBuilder withPropertyId(String propertyId) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, propertyId);
    }

}

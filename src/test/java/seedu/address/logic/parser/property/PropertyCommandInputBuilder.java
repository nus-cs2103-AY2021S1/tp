package seedu.address.logic.parser.property;

import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_ADDRESS_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_ASKING_PRICE_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_ID_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_IS_CLOSED_DEAL_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_IS_RENTAL_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_NAME_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_PRICE_FILTER_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_SELLER_ID_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_TYPE_DESC_ANCHORVALE;

import seedu.address.logic.commands.property.AddPropertyCommand;
import seedu.address.logic.commands.property.EditPropertyCommand;
import seedu.address.logic.commands.property.FindPropertyCommand;

public class PropertyCommandInputBuilder {

    public final String commandWord;
    public final String index;
    public final String propertyName;
    public final String address;
    public final String propertyType;
    public final String sellerId;
    public final String askingPrice;
    public final String isRental;
    public final String isClosedDeal;
    public final String propertyId;

    public PropertyCommandInputBuilder(String commandWord, String index, String propertyName, String address,
                                       String propertyType, String sellerId, String askingPrice,
                                       String isRental, String isClosedDeal, String propertyId) {
        this.commandWord = commandWord;
        this.index = index;
        this.propertyName = propertyName;
        this.address = address;
        this.propertyType = propertyType;
        this.sellerId = sellerId;
        this.askingPrice = askingPrice;
        this.isRental = isRental;
        this.isClosedDeal = isClosedDeal;
        this.propertyId = propertyId;
    }

    public PropertyCommandInputBuilder() {
        this.commandWord = "";
        this.index = "";
        this.propertyName = "";
        this.address = "";
        this.propertyType = "";
        this.sellerId = "";
        this.askingPrice = "";
        this.isRental = "";
        this.isClosedDeal = "";
        this.propertyId = "";
    }

    public String build() {
        return commandWord + " " + index + propertyId + propertyName + address + propertyType
                + sellerId + askingPrice + isRental + isClosedDeal;
    }

    public static PropertyCommandInputBuilder getValidAddPropertyCommandInput() {
        return new PropertyCommandInputBuilder(AddPropertyCommand.COMMAND_WORD,
                "",
                PROPERTY_NAME_DESC_ANCHORVALE, PROPERTY_ADDRESS_DESC_ANCHORVALE, PROPERTY_TYPE_DESC_ANCHORVALE,
                PROPERTY_SELLER_ID_DESC_ANCHORVALE, PROPERTY_ASKING_PRICE_DESC_ANCHORVALE,
                PROPERTY_IS_RENTAL_DESC_ANCHORVALE, "", "");
    }

    public static PropertyCommandInputBuilder getValidEditPropertyCommand() {
        return getValidAddPropertyCommandInput()
                .withCommandWord(EditPropertyCommand.COMMAND_WORD)
                .withIndex("1");
    }

    public static PropertyCommandInputBuilder getValidFindPropertyCommand() {
        return getValidAddPropertyCommandInput()
                .withCommandWord(FindPropertyCommand.COMMAND_WORD)
                .withPropertyId(PROPERTY_ID_DESC_ANCHORVALE)
                .withAskingPrice(PROPERTY_PRICE_FILTER_DESC_ANCHORVALE)
                .withIsClosedDeal(PROPERTY_IS_CLOSED_DEAL_DESC_ANCHORVALE);
    }

    public PropertyCommandInputBuilder withCommandWord(String commandWord) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, isClosedDeal, propertyId);
    }

    public PropertyCommandInputBuilder withIndex(String index) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, isClosedDeal, propertyId);
    }

    public PropertyCommandInputBuilder withPropertyName(String propertyName) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, isClosedDeal, propertyId);
    }

    public PropertyCommandInputBuilder withAddress(String address) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, isClosedDeal, propertyId);
    }

    public PropertyCommandInputBuilder withPropertyType(String propertyType) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, isClosedDeal, propertyId);
    }

    public PropertyCommandInputBuilder withSellerId(String sellerId) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, isClosedDeal, propertyId);
    }

    public PropertyCommandInputBuilder withAskingPrice(String askingPrice) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, isClosedDeal, propertyId);
    }

    public PropertyCommandInputBuilder withIsRental(String isRental) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, isClosedDeal, propertyId);
    }

    public PropertyCommandInputBuilder withIsClosedDeal(String isClosedDeal) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, isClosedDeal, propertyId);
    }

    public PropertyCommandInputBuilder withPropertyId(String propertyId) {
        return new PropertyCommandInputBuilder(commandWord, index, propertyName, address,
                propertyType, sellerId, askingPrice, isRental, isClosedDeal, propertyId);
    }

}

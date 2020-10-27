package seedu.address.logic.commands.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_IS_CLOSED_DEAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_IS_RENTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_PROPERTY_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_SELLER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.property.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.model.Model;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyNameContainsKeywordsPredicate;
import seedu.address.testutil.property.EditPropertyDescriptorBuilder;

public class PropertyCommandTestUtil {

    public static final String VALID_PROPERTY_NAME_ANCHORVALE = "Anchorvale Vista";
    public static final String VALID_PROPERTY_NAME_BEDOK = "Bedok Vista";

    public static final String VALID_PROPERTY_ADDRESS_ANCHORVALE = "Block 123 Anchorvale Road";
    public static final String VALID_PROPERTY_ADDRESS_BEDOK = "Block 456 Bedok Road";

    public static final String VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE = "Active";
    public static final String VALID_PROPERTY_IS_CLOSED_DEAL_BEDOK = "Close";

    public static final String VALID_PROPERTY_IS_RENTAL_ANCHORVALE = "No";
    public static final String VALID_PROPERTY_IS_RENTAL_BEDOK = "Yes";

    public static final String VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE = "HDB 5 room";
    public static final String VALID_PROPERTY_PROPERTY_TYPE_BEDOK = "Landed";

    public static final String VALID_PROPERTY_PROPERTY_ID_ANCHORVALE = "P1";
    public static final String VALID_PROPERTY_PROPERTY_ID_BEDOK = "P2";

    public static final double VALID_PROPERTY_ASKING_PRICE_ANCHORVALE = 100;
    public static final double VALID_PROPERTY_ASKING_PRICE_BEDOK = 1000.20;

    public static final String VALID_PROPERTY_SELLER_ID_ANCHORVALE = "S1";
    public static final String VALID_PROPERTY_SELLER_ID_BEDOK = "S2";

    public static final String VALID_PROPERTY_PRICE_FILTER_ANCHORVALE = "== 100";

    // desc

    public static final String PROPERTY_NAME_DESC_ANCHORVALE =
            " " + PREFIX_PROPERTY_NAME + VALID_PROPERTY_NAME_ANCHORVALE;
    public static final String PROPERTY_NAME_DESC_BEDOK =
            " " + PREFIX_PROPERTY_NAME + VALID_PROPERTY_NAME_BEDOK;

    public static final String PROPERTY_ADDRESS_DESC_ANCHORVALE =
            " " + PREFIX_PROPERTY_ADDRESS + VALID_PROPERTY_ADDRESS_ANCHORVALE;
    public static final String PROPERTY_ADDRESS_DESC_BEDOK =
            " " + PREFIX_PROPERTY_ADDRESS + VALID_PROPERTY_ADDRESS_BEDOK;

    public static final String PROPERTY_IS_CLOSED_DEAL_DESC_ANCHORVALE =
            " " + PREFIX_PROPERTY_IS_CLOSED_DEAL + VALID_PROPERTY_IS_CLOSED_DEAL_ANCHORVALE;
    public static final String PROPERTY_IS_CLOSED_DEAL_DESC_BEDOK =
            " " + PREFIX_PROPERTY_IS_CLOSED_DEAL + VALID_PROPERTY_IS_CLOSED_DEAL_BEDOK;

    public static final String PROPERTY_IS_RENTAL_DESC_ANCHORVALE =
            " " + PREFIX_PROPERTY_IS_RENTAL + VALID_PROPERTY_IS_RENTAL_ANCHORVALE;
    public static final String PROPERTY_IS_RENTAL_DESC_BEDOK =
            " " + PREFIX_PROPERTY_IS_RENTAL + VALID_PROPERTY_IS_RENTAL_BEDOK;

    public static final String PROPERTY_TYPE_DESC_ANCHORVALE =
            " " + PREFIX_PROPERTY_TYPE + VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE;
    public static final String PROPERTY_TYPE_DESC_BEDOK =
            " " + PREFIX_PROPERTY_TYPE + VALID_PROPERTY_PROPERTY_TYPE_BEDOK;

    public static final String PROPERTY_ID_DESC_ANCHORVALE =
            " " + PREFIX_PROPERTY_PROPERTY_ID + VALID_PROPERTY_PROPERTY_ID_ANCHORVALE;
    public static final String PROPERTY_ID_DESC_BEDOK =
            " " + PREFIX_PROPERTY_PROPERTY_ID + VALID_PROPERTY_PROPERTY_ID_BEDOK;

    public static final String PROPERTY_ASKING_PRICE_DESC_ANCHORVALE =
            " " + PREFIX_PROPERTY_ASKING_PRICE + VALID_PROPERTY_ASKING_PRICE_ANCHORVALE;
    public static final String PROPERTY_ASKING_PRICE_DESC_BEDOK =
            " " + PREFIX_PROPERTY_ASKING_PRICE + VALID_PROPERTY_ASKING_PRICE_BEDOK;

    public static final String PROPERTY_SELLER_ID_DESC_ANCHORVALE =
            " " + PREFIX_PROPERTY_SELLER_ID + VALID_PROPERTY_SELLER_ID_ANCHORVALE;
    public static final String PROPERTY_SELLER_ID_DESC_BEDOK =
            " " + PREFIX_PROPERTY_SELLER_ID + VALID_PROPERTY_SELLER_ID_BEDOK;

    public static final String PROPERTY_PRICE_FILTER_DESC_ANCHORVALE =
            " " + PREFIX_PROPERTY_ASKING_PRICE + VALID_PROPERTY_PRICE_FILTER_ANCHORVALE;

    // invalid desc
    public static final String INVALID_PROPERTY_NAME = " " + PREFIX_PROPERTY_NAME + "abc*&";
    public static final String INVALID_PROPERTY_IS_RENTAL = " " + PREFIX_PROPERTY_IS_RENTAL + "blah";
    public static final String INVALID_PROPERTY_PROPERTY_TYPE = " " + PREFIX_PROPERTY_TYPE + "abc&*";
    public static final String INVALID_PROPERTY_ASKING_PRICE = " " + PREFIX_PROPERTY_ASKING_PRICE + "-20";
    public static final String INVALID_PROPERTY_SELLER_ID = " " + PREFIX_PROPERTY_SELLER_ID + "D1";
    public static final String INVALID_PROPERTY_IS_CLOSED_DEAL = " " + PREFIX_PROPERTY_IS_CLOSED_DEAL + "hey";

    public static final EditPropertyDescriptor DESC_ANCHORVALE;
    public static final EditPropertyDescriptor DESC_BEDOK;

    static {
        DESC_ANCHORVALE = new EditPropertyDescriptorBuilder()
                .withPropertyName(VALID_PROPERTY_NAME_ANCHORVALE)
                .withAddress(VALID_PROPERTY_ADDRESS_ANCHORVALE)
                .withSellerId(VALID_PROPERTY_SELLER_ID_ANCHORVALE)
                .withPropertyType(VALID_PROPERTY_PROPERTY_TYPE_ANCHORVALE)
                .withAskingPrice(VALID_PROPERTY_ASKING_PRICE_ANCHORVALE)
                .withIsRental(VALID_PROPERTY_IS_RENTAL_ANCHORVALE)
                .build();
        DESC_BEDOK = new EditPropertyDescriptorBuilder()
                .withPropertyName(VALID_PROPERTY_NAME_BEDOK)
                .withAddress(VALID_PROPERTY_ADDRESS_BEDOK)
                .withSellerId(VALID_PROPERTY_SELLER_ID_BEDOK)
                .withPropertyType(VALID_PROPERTY_PROPERTY_TYPE_BEDOK)
                .withAskingPrice(VALID_PROPERTY_ASKING_PRICE_BEDOK)
                .withIsRental(VALID_PROPERTY_IS_RENTAL_BEDOK)
                .build();
    }

    /**
     * Updates {@code model}'s filtered list to show only the property at the given {@code targetIndex} in the
     * {@code model}'s property book.
     */
    public static void showPropertyAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPropertyList().size());

        Property property = model.getFilteredPropertyList().get(targetIndex.getZeroBased());
        final String[] splitName = property.getPropertyName().propertyName.split("\\s+");
        model.updateFilteredPropertyList(new PropertyNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPropertyList().size());
    }
}

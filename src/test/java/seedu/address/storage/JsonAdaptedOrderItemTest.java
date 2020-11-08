package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedMenuItem.INVALID_PRICE_FORMAT;
import static seedu.address.storage.JsonAdaptedOrderItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.OrderItem;
import seedu.address.model.vendor.Name;
import seedu.address.testutil.OrderItemBuilder;

public class JsonAdaptedOrderItemTest {
    public static final OrderItem EGG_PRATA = new OrderItemBuilder().withName("Egg Prata")
            .withPrice(1.2).withTags("bestseller").withQuantity(3).build();

    private static final String VALID_NAME = "Egg Prata";
    private static final double VALID_PRICE = 1.20;
    private static final double INVALID_PRICE = -1.20;
    private static final String INVALID_TAG = "#tasty";
    private static final List<JsonAdaptedTag> VALID_TAGS = EGG_PRATA.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());


    @Test
    public void toModelType_validOrderItemDetails_returnsOrderItem() throws Exception {
        JsonAdaptedOrderItem orderItem = new JsonAdaptedOrderItem(EGG_PRATA);
        assertEquals(EGG_PRATA, orderItem.toModelType());
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedOrderItem orderItem = new JsonAdaptedOrderItem(null, VALID_PRICE, VALID_TAGS, 3);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, orderItem::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedOrderItem orderItem = new JsonAdaptedOrderItem(VALID_NAME, INVALID_PRICE, VALID_TAGS, 3);
        assertThrows(IllegalValueException.class, INVALID_PRICE_FORMAT, orderItem::toModelType);
    }


    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedOrderItem orderItem =
                new JsonAdaptedOrderItem(VALID_NAME, VALID_PRICE, invalidTags, 3);
        assertThrows(IllegalValueException.class, orderItem::toModelType);
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedOrderItem orderItem =
                new JsonAdaptedOrderItem(VALID_NAME, VALID_PRICE, VALID_TAGS, -1);
        assertThrows(IllegalValueException.class, orderItem::toModelType);
    }

}

package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedFood.INVALID_PRICE_FORMAT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.OrderItem;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link seedu.address.model.order.OrderItem}.
 */
class JsonAdaptedOrderItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "OrderItem's %s field is missing!";

    private final String name;
    private final double price;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final int quantity;

    /**
     * Constructs a {@code JsonAdaptedOrderItem} with the given orderItem details.
     */
    @JsonCreator
    public JsonAdaptedOrderItem(@JsonProperty("name") String name, @JsonProperty("price") double price,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                                @JsonProperty("quantity") int quantity) {
        this.name = name;
        this.price = price;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.quantity = quantity;
    }

    /**
     * Converts a given {@code OrderItem} into this class for Jackson use.
     */
    public JsonAdaptedOrderItem(OrderItem source) {
        name = source.getName();
        price = source.getPrice();
        quantity = source.getQuantity();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }


    /**
     * Converts this Jackson-friendly adapted orderItem object into the model's {@code OrderItem} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted orderItem.
     */
    public OrderItem toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Name"));
        }

        if (!OrderItem.isValidPrice(price)) {
            throw new IllegalValueException(INVALID_PRICE_FORMAT);
        }

        if (!OrderItem.isValidQuantity(quantity)) {
            throw new IllegalValueException("Quantity must be positive.");
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new OrderItem(name, price, modelTags, quantity);
    }

}

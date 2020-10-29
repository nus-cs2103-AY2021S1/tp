package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.OrderItem;
import seedu.address.model.preset.Preset;
import seedu.address.model.tag.Tag;

import static seedu.address.storage.JsonAdaptedFood.INVALID_PRICE_FORMAT;
import static seedu.address.storage.JsonAdaptedOrderItem.MISSING_FIELD_MESSAGE_FORMAT;

public class JsonAdaptedPreset {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Preset's %s field is missing!";

    private String name;
    private List<JsonAdaptedOrderItem> orderItems;


    /**
     * Constructs a {@code JsonAdaptedPreset} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPreset(@JsonProperty("name") String name,
                             @JsonProperty("orderItems") List<JsonAdaptedOrderItem> orderItems) {
        this.name = name;
        this.orderItems = orderItems;
    }

    /**
     * Converts a given {@code Preset} into this class for Jackson use.
     */
    public JsonAdaptedPreset(Preset source) {
        this.name = source.getName();
        this.orderItems = source
                .getOrderItems()
                .stream()
                .map(JsonAdaptedOrderItem::new)
                .collect(Collectors.toList());
    }

    public String getName() {
        return this.name;
    }

    public List<JsonAdaptedOrderItem> getOrderItems() {
        return this.orderItems;
    }

    /**
     * Appends all of a given {@code orderItems} into the orderItems in this class
     */
    public void addAllOrderItems(List<JsonAdaptedOrderItem> orderItems) {
        this.orderItems.addAll(orderItems);
    }

    /**
     * Converts this Jackson-friendly adapted orderItem object into the model's {@code OrderItem} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted orderItem.
     */
    public Preset toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Name"));
        }

        if (orderItems == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Order Items"));
        }

        List<OrderItem> newOrderItems = orderItems.stream().map(x -> {
            try {
                return x.toModelType();
            } catch (IllegalValueException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        return new Preset(name,  newOrderItems);
    }
}

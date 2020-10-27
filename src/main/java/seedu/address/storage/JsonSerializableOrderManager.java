package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.ReadOnlyOrderManager;

/**
 * An Immutable OrderManager that is serializable to JSON format.
 */
public class JsonSerializableOrderManager {
    public static final String MESSAGE_DUPLICATE_ORDERITEM = "Order contains duplicate orderItems.";

    private final List<List<JsonAdaptedOrderItem>> presets = new ArrayList<>(new ArrayList<>());

    /**
     * Constructs a {@code JsonSerializableOrderManager} with the given orderItems.
     */
    @JsonCreator
    public JsonSerializableOrderManager(@JsonProperty("presets") List<List<JsonAdaptedOrderItem>> orderItems) {
        this.presets.addAll(orderItems);
    }

    /**
     * Converts a given {@code ReadOnlyOrderManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableOrderManager}.
     */
    public JsonSerializableOrderManager(ReadOnlyOrderManager source, int index) {
        List<JsonAdaptedOrderItem> orderItems = new ArrayList<>();
        orderItems.addAll(
                source.getOrderItemList()
                        .stream()
                        .map(JsonAdaptedOrderItem::new)
                        .collect(Collectors.toList())
        );
        try {
            List<JsonAdaptedOrderItem> prevPreset = this.presets.get(index);
            prevPreset.addAll(orderItems);
            this.presets.set(index,orderItems);
        } catch(IndexOutOfBoundsException ioe) {
            for (int i = 0; i < index; i++) {
                this.presets.add(new ArrayList<>());
            }
            this.presets.add(index, orderItems);
        }
    }

    /**
     * Converts this address book into the model's {@code OrderManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public List<List<OrderItem>> toModelType() throws IllegalValueException {
        List<List<OrderItem>> orderItemsList = new ArrayList<>();
        for (List<JsonAdaptedOrderItem> jsonAdaptedOrderItems: presets) {
            List<OrderItem> orderItems = new ArrayList<>();
            for (JsonAdaptedOrderItem jsonAdaptedOrderItem : jsonAdaptedOrderItems) {
                OrderItem orderItem = jsonAdaptedOrderItem.toModelType();
                if (orderItems.contains(orderItem)) {
                    throw new IllegalValueException(MESSAGE_DUPLICATE_ORDERITEM);
                }
                orderItems.add(orderItem);
            }
            orderItemsList.add(orderItems);
        }
        return orderItemsList;
    }

}


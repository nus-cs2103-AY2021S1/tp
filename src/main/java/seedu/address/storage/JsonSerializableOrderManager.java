package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.OrderManager;
import seedu.address.model.order.ReadOnlyOrderManager;

/**
 * An Immutable OrderManager that is serializable to JSON format.
 */
@JsonRootName(value = "orderManager")
public class JsonSerializableOrderManager {
    public static final String MESSAGE_DUPLICATE_ORDERITEM = "Order contains duplicate orderItems.";

    private final List<JsonAdaptedOrderItem> orderItems = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableOrderManager} with the given orderItems.
     */
    @JsonCreator
    public JsonSerializableOrderManager(@JsonProperty("orderItems") List<JsonAdaptedOrderItem> orderItems) {
        this.orderItems.addAll(orderItems);
    }

    /**
     * Converts a given {@code ReadOnlyOrderManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableOrderManager}.
     */
    public JsonSerializableOrderManager(ReadOnlyOrderManager source) {
        orderItems.addAll(
                source.getOrderItemList()
                        .stream()
                        .map(JsonAdaptedOrderItem::new)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Converts this address book into the model's {@code OrderManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public OrderManager toModelType() throws IllegalValueException {
        OrderManager orderManager = new OrderManager();
        for (JsonAdaptedOrderItem jsonAdaptedOrderItem : orderItems) {
            OrderItem orderItem = jsonAdaptedOrderItem.toModelType();
            if (orderManager.hasOrderItem(orderItem)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ORDERITEM);
            }
            try {
                orderManager.addOrderItem(orderItem);
            } catch (CommandException e) {
                throw new IllegalValueException(Messages.MESSAGE_ORDERITEM_QUANTITY_EXCEED);
            }
        }
        return orderManager;
    }

}

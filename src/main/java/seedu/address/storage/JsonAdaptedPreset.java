package seedu.address.storage;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.preset.Preset;

public class JsonAdaptedPreset {

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
}

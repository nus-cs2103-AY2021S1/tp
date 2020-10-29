package seedu.address.testutil;

import seedu.address.model.order.OrderItem;
import seedu.address.model.preset.Preset;

import java.util.ArrayList;
import java.util.List;

public class PresetBuilder {

    public static final String DEFAULT_NAME = "Default Preset";
    public static final List<OrderItem> TYPICAL_ORDER_ITEMS = TypicalOrderItems.getTypicalOrderItems();

    public String name;
    public List<OrderItem> orderItems;

    /**
     * Creates a {@code PresetBuilder} with the default details.
     */
    public PresetBuilder() {
        this.name = DEFAULT_NAME;
        this.orderItems = new ArrayList<>();
    }

    /**
     * Sets the {@code orderItems} of the {@code Preset} that we are building.
     */
    public PresetBuilder withOrderItems (List<OrderItem> orderItemsToCopy) {
        orderItems = orderItemsToCopy;
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Preset} that we are building.
     */
    public PresetBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Preset build() {
        return new Preset(name, orderItems);
    }



}

package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Preset.Preset;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.ReadOnlyOrderManager;

/**
 * An Immutable OrderManager that is serializable to JSON format.
 */
public class JsonSerializablePresetManager {

    private final List<JsonAdaptedPreset> presets = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableOrderManager} with the given orderItems.
     */
    @JsonCreator
    public JsonSerializablePresetManager(@JsonProperty("presets") List<JsonAdaptedPreset> presetOrderItems) {
        this.presets.addAll(presetOrderItems);
    }

    /**
     * Converts a given {@code ReadOnlyOrderManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableOrderManager}.
     */
    public JsonSerializablePresetManager(ReadOnlyOrderManager source, String name, int index) {
        List<JsonAdaptedOrderItem> orderItems = new ArrayList<>();
        orderItems.addAll(
                source.getOrderItemList()
                        .stream()
                        .map(JsonAdaptedOrderItem::new)
                        .collect(Collectors.toList())
        );

        try {
            JsonAdaptedPreset prevPreset = this.presets.get(index);
            prevPreset.addAllOrderItems(orderItems);

            this.presets.set(index, prevPreset);
        } catch(IndexOutOfBoundsException ioe) {
            for (int i = 0; i < index - 1; i++) {
                this.presets.add(new JsonAdaptedPreset("", new ArrayList<>()));
                // Empty preset used as buffer
            }
            this.presets.add(new JsonAdaptedPreset(name, new ArrayList<>()));
        }
    }

    /**
     * Converts this address book into the model's {@code OrderManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public List<Preset> toModelType() throws IllegalValueException {
        List<Preset> presetsList = new ArrayList<>();
        for (JsonAdaptedPreset jsonAdaptedPreset: presets) {
            String presetName = jsonAdaptedPreset.getName();
            List<JsonAdaptedOrderItem> jsonAdaptedOrderItems = jsonAdaptedPreset.getOrderItems();

            Preset preset = new Preset(presetName, new ArrayList<>());

            for (JsonAdaptedOrderItem jsonAdaptedOrderItem : jsonAdaptedOrderItems) {
                OrderItem orderItem = jsonAdaptedOrderItem.toModelType();
                preset.addOrderItem(orderItem);
            }

            presetsList.add(preset);
        }
        return presetsList;
    }
}


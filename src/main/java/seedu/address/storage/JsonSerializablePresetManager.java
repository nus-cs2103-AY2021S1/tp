package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.OrderItem;
import seedu.address.model.preset.Preset;

/**
 * An Immutable OrderManager that is serializable to JSON format.
 */
public class JsonSerializablePresetManager {

    private final List<List<JsonAdaptedPreset>> presets = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableOrderManager} with the given orderItems.
     */
    @JsonCreator
    public JsonSerializablePresetManager(@JsonProperty("presets") List<List<JsonAdaptedPreset>> presetOrderItems) {
        this.presets.addAll(presetOrderItems);

    }

    /**
     * Converts a given {@code ReadOnlyOrderManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableOrderManager}.
     */
    public JsonSerializablePresetManager(List<List<Preset>> allPresets, boolean bool) {
        if (!bool) {

        }

        List<List<JsonAdaptedPreset>> finalList = new ArrayList<>();

        for (List<Preset> allPreset : allPresets) {
            List<JsonAdaptedPreset> vendorList = allPreset
                    .stream()
                    .map(JsonAdaptedPreset::new)
                    .collect(Collectors.toList());
            finalList.add(vendorList);
        }
        this.presets.addAll(finalList);
    }

    /**
     * Converts this address book into the model's {@code OrderManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public List<List<Preset>> toModelType() throws IllegalValueException {
        List<List<Preset>> vendorPresetList = new ArrayList<>();
        for (List<JsonAdaptedPreset> jsonAdaptedPresetList: presets) {
            List<Preset> presetsList = new ArrayList<>();
            for (JsonAdaptedPreset jsonAdaptedPreset: jsonAdaptedPresetList) {
                String presetName = jsonAdaptedPreset.getName();
                List<JsonAdaptedOrderItem> jsonAdaptedOrderItems = jsonAdaptedPreset.getOrderItems();

                Preset preset = new Preset(presetName, new ArrayList<>());

                for (JsonAdaptedOrderItem jsonAdaptedOrderItem : jsonAdaptedOrderItems) {
                    OrderItem orderItem = jsonAdaptedOrderItem.toModelType();
                    preset.addOrderItem(orderItem);
                }

                presetsList.add(preset);
            }
            vendorPresetList.add(presetsList);
        }
        return vendorPresetList;
    }
}


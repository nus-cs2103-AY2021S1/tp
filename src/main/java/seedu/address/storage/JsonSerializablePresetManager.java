package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.preset.Preset;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.ReadOnlyOrderManager;

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
    public JsonSerializablePresetManager(ReadOnlyOrderManager source, String name, int index) {
        List<JsonAdaptedOrderItem> orderItems = new ArrayList<>();

        orderItems.addAll(
                source.getOrderItemList()
                        .stream()
                        .map(JsonAdaptedOrderItem::new)
                        .collect(Collectors.toList())
        );

        try {
            List<JsonAdaptedPreset> vendorPresets = this.presets.get(index);
            JsonAdaptedPreset prevPreset = null;
            int presetIndex = 0;
            for (int i = 0; i < vendorPresets.size(); i++) {
                JsonAdaptedPreset currPreset = vendorPresets.get(i);
                if (currPreset.getName().equals(name)) {
                    prevPreset = currPreset;
                    presetIndex = i;
                }
            }

            if (prevPreset == null) {
                throw new NoSuchElementException();
            }

            prevPreset.addAllOrderItems(orderItems);
            this.presets.get(index).set(presetIndex, prevPreset);
//            this.presets.set(index, prevPreset);
        } catch (IndexOutOfBoundsException ioe) {

            for (int i = 0; i < index - 1; i++) {
                this.presets.add(new ArrayList<>());
                // Empty vendor list used as buffer
            }
            List<JsonAdaptedPreset> presetList = new ArrayList<>();
            presetList.add(new JsonAdaptedPreset(name, new ArrayList<>()));
            this.presets.add(presetList);
        } catch (NoSuchElementException ne) {
            List<JsonAdaptedPreset> vendorPresets = this.presets.get(index);
            vendorPresets.add(new JsonAdaptedPreset(name, new ArrayList<>()));
        }
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


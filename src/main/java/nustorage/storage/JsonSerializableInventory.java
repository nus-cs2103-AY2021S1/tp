package nustorage.storage;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import nustorage.commons.exceptions.IllegalValueException;
import nustorage.model.Inventory;
import nustorage.model.ReadOnlyInventory;
import nustorage.model.record.InventoryRecord;


/**
 * An Immutable Inventory that's serializable to the JSON format.
 */
@JsonRootName("inventory")
class JsonSerializableInventory {

    public static final String MESSAGE_DUPLICATE_INVENTORY_RECORD = "Inventory record list contains duplicate records!";

    public final List<JsonAdaptedInventoryRecord> inventoryRecords = new ArrayList<>();


    /**
     * Constructs a {@code JsonSerializableInventory} with the given inventory records.
     */
    @JsonCreator
    public JsonSerializableInventory(
            @JsonProperty("inventoryRecords") List<JsonAdaptedInventoryRecord> inventoryRecords) {

        this.inventoryRecords.addAll(inventoryRecords);
    }


    /**
     * Converts a given {@code ReadOnlyInventory} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInventory}.
     */
    public JsonSerializableInventory(ReadOnlyInventory source) {
        assert source != null : "Source inventory is null!";

        this.inventoryRecords.addAll(
                source.getInventoryRecordList()
                        .stream()
                        .map(JsonAdaptedInventoryRecord::new)
                        .collect(Collectors.toList())
        );
    }


    /**
     * Converts this inventory into the model's {@link Inventory} object.
     *
     * @throws IllegalValueException if there were any data constrains violated.
     */
    public Inventory toModelType() throws IllegalValueException {
        Inventory inventory = new Inventory();
        for (JsonAdaptedInventoryRecord jsonInvRecord : this.inventoryRecords) {
            InventoryRecord invRecord = jsonInvRecord.toModelType();

            /*
            This section prevents adding of duplicate inventory records.
             */
            // if (inventory.hasInventoryRecord(invRecord)) {
            //     throw new IllegalValueException(MESSAGE_DUPLICATE_INVENTORY_RECORD);
            // }
            inventory.addInventoryRecord(invRecord);
        }
        return inventory;
    }

}

package nustorage.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import nustorage.commons.exceptions.IllegalValueException;
import nustorage.model.record.InventoryRecord;


class JsonAdaptedInventoryRecord {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Inventory record's %s field is missing!";

    private final int quantity;
    private final String itemName;
    private final LocalDateTime dateTime;
    private final int financeId;


    /**
     * Constructs a {@code JsonAdaptedInventoryRecord} with the given record details.
     */
    @JsonCreator
    public JsonAdaptedInventoryRecord(@JsonProperty("itemName") String itemName,
                                      @JsonProperty("quantity") int quantity,
                                      @JsonProperty("dateTime") LocalDateTime dateTime,
                                      @JsonProperty("financeId") int financeId) {
        this.quantity = quantity;
        this.itemName = itemName;
        this.dateTime = dateTime;
        this.financeId = financeId;
    }


    /**
     * Converts a given {@code InventoryRecord} into this class for Jackson use.
     */
    public JsonAdaptedInventoryRecord(InventoryRecord source) {
        assert source != null : "Source inventory record is null!";

        this.quantity = source.getQuantity();
        this.itemName = source.getItemName();
        this.dateTime = source.getDateTime();
        this.financeId = source.getFinanceId();
    }


    /**
     * Converts this Jackson-friendly adapted inventory record object into the model's {@code InventoryRecord} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public InventoryRecord toModelType() throws IllegalValueException {
        if (this.itemName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "item name"));
        }
        final String modelItemName = this.itemName;

        if (this.quantity < 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "quantity"));
        }
        final int modelQuantity = this.quantity;

        if (this.dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "dateTime"));
        }
        final LocalDateTime modelDateTime = this.dateTime;

        if (this.financeId < -1) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "financeId"));
        }
        final int modelFinanceId = this.financeId;

        return new InventoryRecord(modelItemName, modelQuantity, modelDateTime, modelFinanceId);

    }

}

package nustorage.model.record;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static nustorage.commons.util.DateTimeUtil.*;

/**
 * Class to record movement in the InventoryWindow.
 */
public class InventoryRecord {

    private final LocalDateTime dateTime;
    private final int quantity;
    private final String itemName;
    private final Double unitCost;
    private int financeId;
    private String uiUsableIndex;

    /**
     * Constructs an InventoryRecord.
     * @param itemName Name of item added..
     * @param quantity Number of items added/removed.
     * @param dateTime Date and time movement.
     */
    public InventoryRecord(String itemName, int quantity, Double unitCost, LocalDateTime dateTime) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.unitCost = unitCost;
        this.uiUsableIndex = "" + uiUsableIndex;
    }

    /**
     * Constructs an InventoryRecord.
     * @param itemName Name of item added..
     * @param quantity Number of items added/removed.
     * @param dateTime Date and time movement.
     * @param financeId ID of the finance record attached to this item.
     */
    public InventoryRecord(String itemName, int quantity, Double unitCost, LocalDateTime dateTime, int financeId) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.unitCost = unitCost;
        this.financeId = financeId;
        this.uiUsableIndex = "" + uiUsableIndex;
    }


    public void setFinanceRecord(FinanceRecord financeRecord) {
        this.financeId = financeRecord.getID();
    }

    /**
     * Get the number of this item in the inventory
     */
    public int getQuantity() {
        return quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public int getFinanceId() {
        return financeId;
    }

    public String getUiUsableIndex() {
        return this.uiUsableIndex;
    }

    public void setUiUsableIndex(int i) {
        this.uiUsableIndex = "" + i;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof InventoryRecord) {
            return ((InventoryRecord) obj).itemName.equals(this.itemName);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Record on " + DATETIME_FORMAT.format(dateTime)
                + ": " + itemName + ", " + quantity + " in stock";
    }


}

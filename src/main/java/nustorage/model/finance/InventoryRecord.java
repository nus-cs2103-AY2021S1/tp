package nustorage.model.finance;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class to record movement in the Inventory.
 */
public class InventoryRecord {

    private final LocalDate date;
    private final LocalTime time;
    private final int quantity;
    private final String itemName;

    /**
     * Constructs an InventoryRecord.
     * @param itemName Item changed.
     * @param quantity Number of items added/removed.
     */
    public InventoryRecord(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    /**
     * Constructs an InventoryRecord.
     * @param itemName Item changed.
     * @param quantity Number of items added/removed.
     * @param date Date movement.
     */
    public InventoryRecord(String itemName, int quantity, LocalDate date) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.date = date;
        this.time = LocalTime.of(0, 0);
    }

    /**
     * Constructs an InventoryRecord.
     * @param itemName Item changed.
     * @param quantity Number of items added/removed.
     * @param date Date movement.
     * @param time Time of movement.
     */
    public InventoryRecord(String itemName, int quantity, LocalDate date, LocalTime time) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.date = date;
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof InventoryRecord) {
            return (((InventoryRecord) obj).quantity == this.quantity
                    && ((InventoryRecord) obj).itemName.equals(this.itemName));
        }
        return false;
    }
    @Override
    public String toString() {
        return "Record on " + date + " at " + time + ": " + itemName + " changed by " + quantity;
    }

}

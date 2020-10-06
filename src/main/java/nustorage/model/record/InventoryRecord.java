package nustorage.model.record;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import nustorage.model.record.exceptions.NegativeNumberOfItemException;

/**
 * Class to record movement in the Inventory.
 */
public class InventoryRecord {

    private final LocalDateTime dateTime;
    private int quantity;
    private final String itemName;

    /**
     * Constructs an InventoryRecord.
     * @param itemName Item added.
     */
    public InventoryRecord(String itemName) {
        this.itemName = itemName;
        this.quantity = 0;
        this.dateTime = LocalDateTime.now();
    }

    /**
     * Constructs an InventoryRecord.
     * @param itemName Item added.
     * @param quantity Number of items added/removed.
     */
    public InventoryRecord(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.dateTime = LocalDateTime.now();
    }

    /**
     * Constructs an InventoryRecord.
     * @param itemName Item added.
     * @param quantity Number of items added/removed.
     * @param dateTime Date and time movement.
     */
    public InventoryRecord(String itemName, int quantity, LocalDateTime dateTime) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.dateTime = dateTime;
    }


    /**
     * Increase or decrease the number of this item in stock.
     * @param change Positive or negative integer.
     * @throws NegativeNumberOfItemException Thrown when number of items goes below 0.
     */
    public void changeAmount(int change) throws NegativeNumberOfItemException {
        if (quantity + change < 0) {
            throw new NegativeNumberOfItemException(this);
        }
        this.quantity += change;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
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
        return "Record on " + getDate() + " at " + getTime()
                + ": " + itemName + ", " + quantity + " in stock";
    }

}

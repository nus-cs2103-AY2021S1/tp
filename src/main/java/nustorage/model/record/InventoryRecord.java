package nustorage.model.record;

import java.time.LocalDate;
import java.time.LocalTime;

import nustorage.model.item.exceptions.NegativeNumberOfItemException;

/**
 * Class to record movement in the Inventory.
 */
public class InventoryRecord {

    private final LocalDate date;
    private final LocalTime time;
    private int quantity;
    private final String itemName;

    /**
     * Constructs an InventoryRecord.
     * @param itemName Item added.
     */
    public InventoryRecord(String itemName) {
        this.itemName = itemName;
        this.quantity = 0;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    /**
     * Constructs an InventoryRecord.
     * @param itemName Item added.
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
     * @param itemName Item added.
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
     * @param itemName Item added.
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
            return ((InventoryRecord) obj).itemName.equals(this.itemName);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Record on " + date + " at " + time + ": " + itemName + ", " + quantity + " in stock";
    }

}

package nustorage.model.record;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to record movement in the Inventory.
 */
public class InventoryRecord {

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    private final LocalDateTime dateTime;
    private final int quantity;
    private final String itemName;
    private int financeID;

    /**
     * Constructs an InventoryRecord.
     * @param itemName Item added.
     */
    public InventoryRecord(String itemName) {
        this.itemName = itemName;
        this.quantity = 0;
        this.dateTime = LocalDateTime.now();
        this.financeID = -1;
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
        this.financeID = -1;
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
        this.financeID = -1;
    }

    public void setFinanceRecord(FinanceRecord financeRecord) {
        this.financeID = financeRecord.getID();
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

    public int getFinanceID() {
        return financeID;
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

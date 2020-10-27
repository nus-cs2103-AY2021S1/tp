package nustorage.model.record;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FinanceRecord {

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    private final int id;
    private LocalDateTime dateTime;
    private final boolean hasInventory;
    private double amount;
    private String uiUsableIndex;
    private double cost;

    /**
     * Constructs a {@code Finance Record}.
     *
     * @param amount Amount of the transaction.
     */
    public FinanceRecord(double amount) {
        id = this.hashCode();
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
        this.hasInventory = false;
        this.uiUsableIndex = "" + uiUsableIndex;
    }

    /**
     * Constructs a {@code Finance Record}.
     *
     * @param amount Amount of the transaction.
     */
    public FinanceRecord(double amount, boolean hasInventory) {
        id = this.hashCode();
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
        this.hasInventory = hasInventory;
        this.uiUsableIndex = "" + uiUsableIndex;
    }

    /**
     * Constructs a {@code Finance Record}.
     *
     * @param amount Amount of the transaction.
     * @param dateTime Date of the transaction.
     */
    public FinanceRecord(double amount, LocalDateTime dateTime) {
        id = this.hashCode();
        this.amount = amount;
        this.dateTime = dateTime;
        this.hasInventory = false;
        this.uiUsableIndex = "" + uiUsableIndex;
    }

    /**
     * Constructs a {@code Finance Record}.
     *
     * @param id ID of the transaction.
     * @param amount Amount of the transaction.
     * @param dateTime Date of the transaction.
     */
    public FinanceRecord(int id, double amount, LocalDateTime dateTime, boolean hasInventory) {
        this.id = id;
        this.amount = amount;
        this.dateTime = dateTime;
        this.hasInventory = hasInventory;
        this.uiUsableIndex = "" + uiUsableIndex;
    }

    /**
     * Constructs a {@code Finance Record}.
     *
     * @param amount the amount of the transaction
     * @param cost the cost of each item
     */
    public FinanceRecord(double amount, double cost) {
        this.amount = amount;
        this.id = this.hashCode();;
        this.dateTime = LocalDateTime.now();
        this.cost = cost;
        this.hasInventory = true;
        this.uiUsableIndex = "" + uiUsableIndex;
    }

    public int getID() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public double getCost() {
        return cost;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDatetimeString() {
        return DATETIME_FORMAT.format(dateTime);
    }

    public boolean taggedToInventory() {
        return hasInventory;
    }

    public String getUiUsableIndex() {
        return uiUsableIndex;
    }

    public void setUiUsableIndex(int i) {
        this.uiUsableIndex = "" + i;
    }

    /**
     * Compares if {@code obj} is a {@code Finance Record} and has the same amount and datetime value.
     *
     * @param obj Object to compare with
     * @return True if {@code obj} is a {@code Finance Record} and has the same amount and datetime value.
     */
    public boolean hasSameData(Object obj) {
        if (obj instanceof FinanceRecord) {
            return ((FinanceRecord) obj).amount == this.amount
                    && ((FinanceRecord) obj).getDatetimeString().equals(this.getDatetimeString());
        }
        return false;
    }

    /**
     * Compares if {@code obj} is a {@code Finance Record} and has the same amount and datetime value.
     *
     * @param obj Object to compare with
     * @return True if {@code obj} is a {@code Finance Record} and has the same amount and datetime value.
     */
    public boolean isSameRecord(Object obj) {
        if (obj instanceof FinanceRecord) {
            return this.id == ((FinanceRecord) obj).id;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FinanceRecord) {
            return this.isSameRecord(obj)
                    && this.hasSameData(obj);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Transaction #%d on %s: $%.2f",
                id,
                getDatetimeString(),
                amount);
    }
}

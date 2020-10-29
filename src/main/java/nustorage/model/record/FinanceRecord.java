package nustorage.model.record;

import static nustorage.commons.util.DateTimeUtil.DATETIME_FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FinanceRecord {

    private final int id;
    private final LocalDateTime dateTime;
    private final boolean hasInventory;
    private double amount;
    private String uiUsableIndex;

    /**
     * Constructs a {@code FinanceWindow Record}.
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
     * Constructs a {@code FinanceWindow Record}.
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
     * Constructs a {@code FinanceWindow Record}.
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
     * Constructs a {@code FinanceWindow Record}.
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
     * Constructs a {@code FinanceWindow Record}.
     *
     * @param amount Amount of the transaction.
     * @param dateTime Date of the transaction.
     */
    public FinanceRecord(double amount, LocalDateTime dateTime, boolean hasInventory) {
        this.id = this.hashCode();
        this.amount = amount;

        this.dateTime = dateTime;
        this.hasInventory = hasInventory;
        this.uiUsableIndex = "" + uiUsableIndex;
    }

    public int getID() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getDate() {
        return this.dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return this.dateTime.toLocalTime();
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
     * Compares if {@code obj} is a {@code FinanceWindow Record} and has the same amount and datetime value.
     *
     * @param obj Object to compare with
     * @return True if {@code obj} is a {@code FinanceWindow Record} and has the same amount and datetime value.
     */
    public boolean hasSameData(Object obj) {
        if (obj instanceof FinanceRecord) {
            return ((FinanceRecord) obj).amount == this.amount
                    && ((FinanceRecord) obj).getDatetimeString().equals(this.getDatetimeString());
        }
        return false;
    }

    /**
     * Compares if {@code obj} is a {@code FinanceWindow Record} and has the same amount and datetime value.
     *
     * @param obj Object to compare with
     * @return True if {@code obj} is a {@code FinanceWindow Record} and has the same amount and datetime value.
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

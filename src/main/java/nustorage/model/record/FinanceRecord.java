package nustorage.model.record;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FinanceRecord {

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    private final int id;
    private LocalDateTime datetime;
    private double amount;

    /**
     * Constructs a {@code Finance Record}.
     *
     * @param amount Amount of the transaction.
     */
    public FinanceRecord(double amount) {
        id = this.hashCode();
        this.amount = amount;
        this.datetime = LocalDateTime.now();
    }

    /**
     * Constructs a {@code Finance Record}.
     *
     * @param amount Amount of the transaction.
     * @param datetime Date of the transaction.
     */
    public FinanceRecord(double amount, LocalDateTime datetime) {
        id = this.hashCode();
        this.amount = amount;
        this.datetime = datetime;
    }

    /**
     * Constructs a {@code Finance Record}.
     *
     * @param id ID of the transaction.
     * @param amount Amount of the transaction.
     * @param datetime Date of the transaction.
     */
    public FinanceRecord(int id, double amount, LocalDateTime datetime) {
        this.id = id;
        this.amount = amount;
        this.datetime = datetime;
    }

    public int getID() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public String getDatetimeString() {
        return DATETIME_FORMAT.format(datetime);
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

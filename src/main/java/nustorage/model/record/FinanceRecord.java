package nustorage.model.record;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FinanceRecord {

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    private LocalDateTime datetime;
    private double amount;

    /**
     * Constructs a {@code Finance Record}.
     *
     * @param amount Amount of the transaction.
     */
    public FinanceRecord(double amount) {
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
        this.amount = amount;
        this.datetime = datetime;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return datetime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FinanceRecord) {
            return ((FinanceRecord) obj).amount == this.amount;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Record on %s: $%.2f",
                DATETIME_FORMAT.format(datetime),
                amount);
    }
}

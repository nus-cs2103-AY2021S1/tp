package nustorage.model.record;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FinanceRecord {

    private final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private final static DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private LocalDate date;
    private LocalTime time;
    private double amount;

    /**
     * Constructs a {@code Finance Record}.
     *
     * @param amount Amount of the transaction.
     */
    public FinanceRecord(double amount) {
        this.amount = amount;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    /**
     * Constructs a {@code Finance Record}.
     *
     * @param amount Amount of the transaction.
     * @param date Date of the transaction.
     */
    public FinanceRecord(double amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
        this.time = LocalTime.now();
    }

    /**
     * Constructs a {@code Finance Record}.
     *
     * @param amount Amount of the transaction.
     * @param date Date of the transaction.
     * @param time Time of the transaction.
     */
    public FinanceRecord(double amount, LocalDate date, LocalTime time) {
        this.amount = amount;
        this.date = date;
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
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
        return String.format("Record on %s at %s: $%.2f",
                DATE_FORMAT.format(date),
                TIME_FORMAT.format(time),
                amount);
    }
}

package nustorage.model.finance;

import java.time.LocalDate;
import java.time.LocalTime;

public class FinanceRecord {

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
        return "Record on " + date + " at " + time + ": $" + String.format("%.2f", amount);
    }
}

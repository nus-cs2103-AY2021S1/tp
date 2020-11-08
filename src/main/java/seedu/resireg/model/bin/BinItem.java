package seedu.resireg.model.bin;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Objects;

/*
Implementation below adapted from https://github.com/AY1920S1-CS2103-F09-4/main.
 */
/**
 * Represents a BinItem in ResiReg.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class BinItem {

    // Identity fields
    private final Binnable item;
    private final LocalDate dateDeleted;

    /**
     * Every field must be present and not null.
     */
    public BinItem(Binnable item) {
        requireNonNull(item);
        this.item = item;
        this.dateDeleted = LocalDate.now();
    }

    /**
     * Every field must be present and not null.
     */
    public BinItem(Binnable item, LocalDate dateDeleted) {
        requireNonNull(item);
        requireNonNull(dateDeleted);
        this.item = item;
        this.dateDeleted = dateDeleted;
    }

    public Binnable getBinnedItem() {
        return item;
    }

    public LocalDate getDateDeleted() {
        return dateDeleted;
    }

    public boolean isExpired(int daysStoredInBin) {
        return dateDeleted.plusDays(daysStoredInBin).isBefore(LocalDate.now());
    }

    /**
     * Returns true if both rooms have the same data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof BinItem)) {
            return false;
        }

        BinItem otherItem =
            (BinItem) other;
        return otherItem.getBinnedItem().equals(getBinnedItem())
               && otherItem.getDateDeleted().equals(getDateDeleted());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(item, dateDeleted);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Item: ")
            .append(getBinnedItem().toString())
            .append(" Date Deleted: ")
            .append(getDateDeleted().toString());
        return builder.toString();
    }
}

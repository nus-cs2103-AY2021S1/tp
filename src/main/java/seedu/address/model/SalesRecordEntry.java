package seedu.address.model;

import java.util.Objects;


/**
 * Represents a SalesRecordEntry in the sales book.
 */
public class SalesRecordEntry {

    private final Drink drink;
    private final int numberSold;

    /**
     * Creates a SalesRecordEntry which records the number of {@Code drink} sold.
     *
     * @param drink the type of Drink
     * @param numberSold the number of drink sold
     */
    public SalesRecordEntry(Drink drink, int numberSold) {
        this.drink = drink;
        this.numberSold = numberSold;
    }

    public Drink getDrink() {
        return drink;
    }

    public int getNumberSold() {
        return numberSold;
    }

    /**
     * A record entry is the same as another record entry if they record the same Drink item.
     *
     * @param otherEntry the other record entry to compare to
     * @return true if they record the same Drink item, and false otherwise
     */
    public boolean isSameRecord(SalesRecordEntry otherEntry) {
        if (otherEntry == this) {
            return true;
        }

        return this.drink.equals(otherEntry.drink);
    }

    @Override
    public String toString() {
        return drink.getName() + " (" + drink + ") : " + numberSold;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SalesRecordEntry)) {
            return false;
        }

        SalesRecordEntry otherEntry = (SalesRecordEntry) other;
        return otherEntry.drink.equals(drink)
                && otherEntry.numberSold == numberSold;
    }

    @Override
    public int hashCode() {
        return Objects.hash(drink, numberSold);
    }

}

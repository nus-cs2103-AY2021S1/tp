package nustorage.model.item;

import nustorage.model.item.exceptions.NegativeNumberOfItemException;

/**
 * Class to store and keep count of item.
 */
public class Item {

    private final String description;
    private int amount;

    /**
     * Constructs an Item class.
     * @param description description of item.
     */
    public Item (String description) {
        this.description = description;
        this.amount = 0;
    }

    /**
     * Constructs an Items class.
     * @param description Description of item.
     * @param amount Number of item to be stored.
     */
    public Item (String description, int amount) {
        this.description = description;
        this.amount = amount;
    }

    /**
     * Get the number item stocked.
     * @return Number of this item.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Increase or decrease the number of this item in stock.
     * @param change Positive or negative integer.
     * @throws NegativeNumberOfItemException Thrown when number of items goes below 0.
     */
    public void changeAmount(int change) throws NegativeNumberOfItemException {
        if (amount + change < 0) {
            throw new NegativeNumberOfItemException(this);
        }
        this.amount += change;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Item) {
            return ((Item) object).description.equals(this.description);
        }
        return false;
    }

    @Override
    public String toString() {
        return description;
    }
}

package nustorage.model.item;

import nustorage.model.item.exceptions.NegativeNumberOfItemException;

public class Item {

    private final String description;
    private int amount;

    public Item (String description) {
        this.description = description;
        this.amount = 0;
    }

    public Item (String description, int amount) {
        this.description = description;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

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

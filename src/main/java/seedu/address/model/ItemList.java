package seedu.address.model;

import java.util.HashMap;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameItem comparison)
 */
public class ItemList {

    private final HashMap<String, Integer> items;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */


    public ItemList() {
        items = new HashMap<>();
    }

    //// util methods

    @Override
    public String toString() {
        return items.size() + " items";
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemList // instanceof handles nulls
                && items.equals(((ItemList) other).items));
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}

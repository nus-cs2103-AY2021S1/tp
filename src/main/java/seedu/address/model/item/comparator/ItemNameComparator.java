package seedu.address.model.item.comparator;

import java.util.Comparator;

import seedu.address.model.item.Item;

public class ItemNameComparator implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        return o1.getName().fullName.compareTo(o2.getName().fullName);
    }
}

package seedu.address.model.menu;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.food.Food;
import seedu.address.model.food.MenuItem;
import seedu.address.model.food.exceptions.DuplicateFoodException;
import seedu.address.model.food.exceptions.FoodNotFoundException;
import seedu.address.storage.JsonAdaptedMenuItem;

/**
 * A list of menu items that enforces uniqueness between its elements and does not allow nulls.
 * A menuItem is considered unique by comparing using {@code Food#equals(Food)}. As such, adding and updating of
 * menu items uses Food#equals(Food) for equality so as to ensure that the menu item being added or updated is
 * unique in terms of identity in the Menu. The removal of a menu item also uses Food#equals(Object) so
 * as to ensure that the food with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Food#equals(Object) (Food)
 */
public class Menu implements Iterable<MenuItem> {

    private final ObservableList<MenuItem> internalList = FXCollections.observableArrayList();
    private final ObservableList<MenuItem> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent food as the given argument.
     */
    public boolean contains(MenuItem toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a menu item to the list.
     * The menu item must not already exist in the list.
     */
    public void add(MenuItem toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateFoodException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the menu item {@code target} in the list with {@code editedMenuItem}.
     * {@code target} must exist in the list.
     * The menu item identity of {@code editedMenuItem} must not be the same as another existing menu item in the list.
     */
    public void setMenuItem(MenuItem target, MenuItem editedItem) {
        requireAllNonNull(target, editedItem);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new FoodNotFoundException();
        }

        if (!target.equals(editedItem) && contains(editedItem)) {
            throw new DuplicateFoodException();
        }

        internalList.set(index, editedItem);
    }

    /**
     * Removes the equivalent menu item from the list.
     * The menu item must exist in the list.
     */
    public void remove(MenuItem toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new FoodNotFoundException();
        }
    }

    public void setMenuItems(Menu replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }


    /**
     * Replaces the contents of this list with {@code menuItems}.
     * {@code menuItems} must not contain duplicate menu items.
     */
    public void setMenuItems(List<MenuItem> items) {
        requireAllNonNull(items);
        if (!itemsAreUnique(items)) {
            throw new DuplicateFoodException();
        }

        internalList.setAll(items);
    }

    /**
     * Sort Menu items in menu by name
     * @param ascending decide whether to sort ascending or descending
     */
    public void sortMenuItemByName(boolean ascending) {
        Comparator<MenuItem> itemComparator = Comparator.comparing(MenuItem::getName);
        if (ascending) {
            internalList.sort(itemComparator);
        } else {
            internalList.sort(itemComparator.reversed());
        }
    }

    /**
     * Sort Menu items in menu by price
     * @param ascending decide whether to sort ascending or descending
     */
    public void sortMenuItemByPrice(boolean ascending) {
        Comparator<MenuItem> itemComparator = Comparator.comparing(MenuItem::getPrice);
        if (ascending) {
            internalList.sort(itemComparator);
        } else {
            internalList.sort(itemComparator.reversed());
        }
    }

    /**
     * Gets the contents of the list.
     * {@code menuItems} must not contain duplicate menuItems.
     */
    public List<JsonAdaptedMenuItem> getMenuItems() {
        requireAllNonNull(internalList);
        ArrayList<JsonAdaptedMenuItem> menuItemList = new ArrayList<>();

        for (MenuItem menuItem : internalList) {
            menuItemList.add(new JsonAdaptedMenuItem(menuItem));
        }
        return menuItemList;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<MenuItem> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<MenuItem> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Menu // instanceof handles nulls
                        && internalList.equals(((Menu) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code menuItems} contains only unique menu items.
     */
    private boolean itemsAreUnique(List<MenuItem> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).equals(items.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

package seedu.address.model.menu;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.food.MenuItem;


/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameFood comparison)
 */
public class MenuManager implements ReadOnlyMenuManager {

    private Menu menu;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        menu = new Menu();
    }

    public MenuManager() {}

    /**
     * Creates a MenuManager using the Foods in the {@code toBeCopied}
     */
    public MenuManager(ReadOnlyMenuManager toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Creates a MenuManager using the menu in the {@code toBeCopied}
     */
    public MenuManager(Menu toBeCopied) {
        this.menu = toBeCopied;
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the menu item list with {@code items}.
     * {@code items} must not contain duplicate items.
     */
    public void setMenu(List<MenuItem> items) {
        this.menu.setMenuItems(items);
    }

    /**
     * Resets the existing data of this {@code MenuManager} with {@code newData}.
     */
    public void resetData(ReadOnlyMenuManager newData) {
        requireNonNull(newData);

        setMenu(newData.getMenuItemList());
    }

    //// food-level operations

    /**
     * Returns true if a food with the same identity as {@code food} exists in the menu manager.
     */
    public boolean hasMenuItem(MenuItem item) {
        requireNonNull(item);
        return menu.contains(item);
    }

    /**
     * Adds a menuItem to the address book.
     * The menuItem must not already exist in the address book.
     */
    public void addMenuItem(MenuItem f) {
        menu.add(f);
    }

    /**
     * Replaces the given menuItem {@code target} in the list with {@code editedMenuItem}.
     * {@code target} must exist in the address book.
     * The menuItem identity of {@code editedMenuItem} must not be the same as another existing menu item in the
     * address book.
     */
    public void setMenuItem(MenuItem target, MenuItem editedItem) {
        requireNonNull(editedItem);

        menu.setMenuItem(target, editedItem);
    }

    public void sortMenuItemByName(boolean ascending) {
        menu.sortMenuItemByName(ascending);
    }

    public void sortMenuItemByPrice(boolean ascending) {
        menu.sortMenuItemByPrice(ascending);
    }

    /**
     * Removes {@code key} from this {@code MenuManager}.
     * {@code key} must exist in the address book.
     */
    public void removeMenuItem(MenuItem key) {
        menu.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return menu.asUnmodifiableObservableList().size() + " foods";
        // TODO: refine later
    }

    @Override
    public ObservableList<MenuItem> getMenuItemList() {
        return menu.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MenuManager // instanceof handles nulls
                && menu.equals(((MenuManager) other).menu));
    }

    @Override
    public int hashCode() {
        return menu.hashCode();
    }
}

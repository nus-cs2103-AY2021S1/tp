package seedu.address.model.menu;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.food.Food;



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
     * Replaces the contents of the food list with {@code foods}.
     * {@code foods} must not contain duplicate foods.
     */
    public void setMenu(List<Food> foods) {
        this.menu.setFoods(foods);
    }

    /**
     * Resets the existing data of this {@code MenuManager} with {@code newData}.
     */
    public void resetData(ReadOnlyMenuManager newData) {
        requireNonNull(newData);

        setMenu(newData.getFoodList());
    }

    //// food-level operations

    /**
     * Returns true if a food with the same identity as {@code food} exists in the menu manager.
     */
    public boolean hasFood(Food food) {
        requireNonNull(food);
        return menu.contains(food);
    }

    /**
     * Adds a food to the address book.
     * The food must not already exist in the address book.
     */
    public void addFood(Food f) {
        menu.add(f);
    }

    /**
     * Replaces the given food {@code target} in the list with {@code editedFood}.
     * {@code target} must exist in the address book.
     * The food identity of {@code editedFood} must not be the same as another existing food in the address book.
     */
    public void setFood(Food target, Food editedFood) {
        requireNonNull(editedFood);

        menu.setFood(target, editedFood);
    }

    public void sortFoodByName(boolean ascending) {
        menu.sortFoodByName(ascending);
    }

    public void sortFoodByPrice(boolean ascending) {
        menu.sortFoodByPrice(ascending);
    }

    /**
     * Removes {@code key} from this {@code MenuManager}.
     * {@code key} must exist in the address book.
     */
    public void removeFood(Food key) {
        menu.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return menu.asUnmodifiableObservableList().size() + " foods";
        // TODO: refine later
    }

    @Override
    public ObservableList<Food> getFoodList() {
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

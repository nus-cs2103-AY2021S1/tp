package jimmy.mcgymmy.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import jimmy.mcgymmy.model.food.Food;
import jimmy.mcgymmy.model.food.Fridge;
import jimmy.mcgymmy.model.food.exceptions.DuplicateFoodException;

/**
 * Wraps all data at mcgymmy level
 * Duplicates are not allowed (by .isSameFood comparison)
 */
public class McGymmy implements ReadOnlyMcGymmy {

    private final Fridge foodItems;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        foodItems = new Fridge();
    }

    public McGymmy() {
    }

    /**
     * Creates an McGymmy using the Foods in the {@code toBeCopied}
     */
    public McGymmy(ReadOnlyMcGymmy toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    // list overwrite operations

    /**
     * Replaces the contents of the Food list with {@code Foods}.
     * {@code Foods} must not contain duplicate Foods.
     */
    public void setFoodItems(List<Food> foodItems) {
        this.foodItems.setFoods(foodItems);
    }

    /**
     * Resets the existing data of this {@code McGymmy} with {@code newData}.
     */
    public void resetData(ReadOnlyMcGymmy newData) {
        requireNonNull(newData);
        for (Food food : newData.getFoodList()) {
            for (Food comp : newData.getFoodList()) {
                if (food.hashCode() == comp.hashCode()) {
                    continue;
                }
                if (food.equals(comp)) {
                    throw new DuplicateFoodException();
                }
            }
        }
        setFoodItems(newData.getFoodList());
    }

    // Food-level operations

    /**
     * Returns true if a Food with the same identity as {@code Food} exists in mcgymmy.
     */
    public boolean hasFood(Food food) {
        requireNonNull(food);
        return foodItems.contains(food);
    }

    /**
     * Adds a Food to mcgymmy.
     * The Food must not already exist in mcgymmy.
     */
    public void addFood(Food food) {
        foodItems.add(food);
    }

    /**
     * Replaces the given Food {@code target} in the list with {@code editedFood}.
     * {@code target} must exist in mcgymmy.
     * The Food identity of {@code editedFood} must not be the same as another existing Food in mcgymmy.
     */
    public void setFood(Food target, Food editedFood) {
        requireNonNull(editedFood);

        foodItems.setFood(target, editedFood);
    }

    /**
     * Removes {@code key} from this {@code McGymmy}.
     * {@code key} must exist in mcgymmy.
     */
    public void removeFood(Food key) {
        foodItems.remove(key);
    }

    // util methods

    @Override
    public String toString() {
        return foodItems.asUnmodifiableObservableList().size() + " Foods";
        // TODO: refine later
    }

    @Override
    public ObservableList<Food> getFoodList() {
        return foodItems.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof McGymmy // instanceof handles nulls
                && foodItems.equals(((McGymmy) other).foodItems));
    }

    @Override
    public int hashCode() {
        return foodItems.hashCode();
    }
}

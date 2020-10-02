package chopchop.model;

import static java.util.Objects.requireNonNull;
import java.util.List;
import chopchop.model.recipe.Recipe;
import chopchop.model.ingredient.Ingredient;
import javafx.collections.ObservableList;

public abstract class FoodEntryBook implements ReadOnlyFoodEntryBook {

    protected final UniqueFoodEntryList entries;

    public FoodEntryBook() {
        entries = new UniqueFoodEntryList();
    }

    /**
     * Creates an AddressBook using the Ingredients in the {@code toBeCopied}
     */
    public FoodEntryBook(ReadOnlyFoodEntryBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setFoodEntries(List<FoodEntry> entries) {
        this.entries.setFoodEntries(entries);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyFoodEntryBook newData) {
        requireNonNull(newData);

        setFoodEntries(newData.getFoodEntryList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasIngredient(FoodEntry entry) {
        requireNonNull(entry);
        return entries.contains(entry);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasRecipe(FoodEntry entry) {
        requireNonNull(entry);
        return entries.contains(entry);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addIngredient(Ingredient p) {
        entries.add(p);
    }

    /**
     * Adds a recipe to the recipe book.
     * The recipe must not already exist in the recipe book.
     */
    public void addRecipe(Recipe r) {
        entries.add(r);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedIngredient}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedIngredient} must not be the same as another existing person in the
     * address book.
     */
    public void setIngredient(Ingredient target, Ingredient editedIngredient) {
        requireNonNull(editedIngredient);

        entries.setEntry(target, editedIngredient);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setRecipe(Recipe target, Recipe editedRecipe) {
        requireNonNull(editedRecipe);

        entries.setEntry(target, editedRecipe);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeIngredient(Ingredient key) {
        entries.remove(key);
    }

    /**
     * Removes {@code key} from this {@code RecipeBook}.
     * {@code key} must exist in the recipe book.
     */
    public void removeRecipe(Recipe key) {
        entries.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return entries.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<FoodEntry> getFoodEntryList() {
        return entries.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FoodEntryBook // instanceof handles nulls
            && entries.equals(((FoodEntryBook) other).entries));
    }

    @Override
    public int hashCode() {
        return entries.hashCode();
    }
}

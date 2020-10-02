package chopchop.model.ingredient;

import static java.util.Objects.requireNonNull;
import java.util.List;
import javafx.collections.ObservableList;

public class IngredientBook implements ReadOnlyIngredientBook {

    private final UniqueIngredientList entries;

    public IngredientBook() {
        entries = new UniqueIngredientList();
    }

    /**
     * Creates an AddressBook using the Ingredients in the {@code toBeCopied}
     */
    public IngredientBook(ReadOnlyIngredientBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setFoodEntries(List<Ingredient> entries) {
        this.entries.setIngredientEntries(entries);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyIngredientBook newData) {
        requireNonNull(newData);

        setFoodEntries(newData.getFoodEntryList());
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasIngredient(Ingredient entry) {
        requireNonNull(entry);
        return entries.contains(entry);
    }

    /**
     * Adds a recipe to the recipe book.
     * The recipe must not already exist in the recipe book.
     */
    public void addIngredient(Ingredient r) {
        entries.add(r);
    }


    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setIngredient(Ingredient target, Ingredient editedIngredient) {
        requireNonNull(editedIngredient);

        entries.setIngredient(target, editedIngredient);
    }

    /**
     * Removes {@code key} from this {@code IngredientBook}.
     * {@code key} must exist in the recipe book.
     */
    public void removeIngredient(Ingredient key) {
        entries.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return entries.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    /**
     * Returns an unmodifiable view of the ingredient list.
     * This list will not contain any duplicate ingredients.
     */
    @Override
    public ObservableList<Ingredient> getFoodEntryList() {
        return entries.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof IngredientBook // instanceof handles nulls
            && entries.equals(((IngredientBook) other).entries));
    }

    @Override
    public int hashCode() {
        return entries.hashCode();
    }



}

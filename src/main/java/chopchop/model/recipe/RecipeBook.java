package chopchop.model.recipe;

import static java.util.Objects.requireNonNull;
import java.util.List;
import javafx.collections.ObservableList;

public class RecipeBook implements ReadOnlyRecipeBook {

    private final UniqueRecipeList entries;

    public RecipeBook() {
        entries = new UniqueRecipeList();
    }

    /**
     * Creates an AddressBook using the Ingredients in the {@code toBeCopied}
     */
    public RecipeBook(ReadOnlyRecipeBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setFoodEntries(List<Recipe> entries) {
        this.entries.setRecipeEntries(entries);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyRecipeBook newData) {
        requireNonNull(newData);

        setFoodEntries(newData.getFoodEntryList());
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasRecipe(Recipe entry) {
        requireNonNull(entry);
        return entries.contains(entry);
    }

    /**
     * Adds a recipe to the recipe book.
     * The recipe must not already exist in the recipe book.
     */
    public void addRecipe(Recipe r) {
        entries.add(r);
    }


    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setRecipe(Recipe target, Recipe editedRecipe) {
        requireNonNull(editedRecipe);

        entries.setRecipe(target, editedRecipe);
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
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof RecipeBook // instanceof handles nulls
            && entries.equals(((RecipeBook) other).entries));
    }

    @Override
    public int hashCode() {
        return entries.hashCode();
    }

    /**
     * Returns an unmodifiable view of the recipes list.
     * This list will not contain any duplicate recipes.
     */
    @Override
    public ObservableList<Recipe> getFoodEntryList() {
        return entries.asUnmodifiableObservableList();
    }
}

package chopchop.model;

import static java.util.Objects.requireNonNull;
import java.util.List;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.ingredient.UniqueIngredientList;
import javafx.collections.ObservableList;

public class IngredientBook implements ReadOnlyIngredientBook {

    private final UniqueIngredientList ingredients;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        ingredients = new UniqueIngredientList();
    }

    public IngredientBook() {}

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
    public void setIngredients(List<Ingredient> persons) {
        this.ingredients.setIngredients(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyIngredientBook newData) {
        requireNonNull(newData);

        setIngredients(newData.getIngredientList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasIngredient(Ingredient person) {
        requireNonNull(person);
        return ingredients.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addIngredient(Ingredient p) {
        ingredients.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedIngredient}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedIngredient} must not be the same as another existing person in the
     * address book.
     */
    public void setIngredient(Ingredient target, Ingredient editedIngredient) {
        requireNonNull(editedIngredient);

        ingredients.setIngredient(target, editedIngredient);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeIngredient(Ingredient key) {
        ingredients.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return ingredients.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Ingredient> getIngredientList() {
        return ingredients.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof IngredientBook // instanceof handles nulls
            && ingredients.equals(((IngredientBook) other).ingredients));
    }

    @Override
    public int hashCode() {
        return ingredients.hashCode();
    }
}

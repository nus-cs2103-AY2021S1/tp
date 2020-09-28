package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class McGymmy implements ReadOnlyMcGymmy {

    private final UniquePersonList foods;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        foods = new UniquePersonList();
    }

    public McGymmy() {}

    /**
     * Creates an McGymmy using the Persons in the {@code toBeCopied}
     */
    public McGymmy(ReadOnlyMcGymmy toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setFoods(List<Person> foods) {
        this.foods.setPersons(foods);
    }

    /**
     * Resets the existing data of this {@code McGymmy} with {@code newData}.
     */
    public void resetData(ReadOnlyMcGymmy newData) {
        requireNonNull(newData);

        setFoods(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasFood(Person person) {
        requireNonNull(person);
        return foods.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addFood(Person p) {
        foods.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setFood(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        foods.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code McGymmy}.
     * {@code key} must exist in the address book.
     */
    public void removeFood(Person key) {
        foods.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return foods.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return foods.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof McGymmy // instanceof handles nulls
                && foods.equals(((McGymmy) other).foods));
    }

    @Override
    public int hashCode() {
        return foods.hashCode();
    }
}

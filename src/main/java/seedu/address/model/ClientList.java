package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.policy.PolicyList;

/**
 * Wraps all data at the client-list level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ClientList implements ReadOnlyClientList {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public ClientList() {}

    /**
     * Creates an ClientList using the Persons in the {@code toBeCopied}
     */
    public ClientList(ReadOnlyClientList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code ClientList} with {@code newData}.
     */
    public void resetData(ReadOnlyClientList newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the client list.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the client list.
     * The person must not already exist in the client list.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the client list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the client list.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code ClientList}.
     * {@code key} must exist in the client list.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientList // instanceof handles nulls
                && persons.equals(((ClientList) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

    /**
     * Updates Clientlist by checking if Persons in the UniquePersonList has Policys that are
     * already in the policy list.
     * If the Person's policy does not match the Policy in the PolicyList,
     * The field is set to null.
     */
    public void updateClientListWithPolicyList(PolicyList policyList) {
        persons.updateUniquePersonListWithPolicyList(policyList);
    }

    /**
     * Clear all policy fields in persons.
     */
    public void clearPolicy() {
        persons.clearPolicy();
    }
}

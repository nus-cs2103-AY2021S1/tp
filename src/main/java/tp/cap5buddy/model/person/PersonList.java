package tp.cap5buddy.model.person;
import tp.cap5buddy.model.person.exceptions.DuplicatePersonException;
import tp.cap5buddy.model.person.exceptions.PersonNotFoundException;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class PersonList {

    private final List<Person> persons;

    public PersonList(List<Person> persons) {
        this.persons = persons;
    }

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return persons.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            // throw new DuplicatePersonException();
        }
        persons.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void editPerson(Person target, Person editedPerson) throws PersonNotFoundException, DuplicatePersonException {
        // requireAllNonNull(target, editedPerson);

        int index = persons.indexOf(target);
        if (index == -1) {
            String error = "Invalid Person!";
            throw new PersonNotFoundException(error);
        }

        if (!target.isSamePerson(editedPerson) && contains(editedPerson)) {
            String error = "The operation could not be completed as a similar person is already in the contact list!";
            throw new DuplicatePersonException(error);
        }

        persons.set(index, editedPerson);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Person toRemove) throws PersonNotFoundException {
        requireNonNull(toRemove);
        if (!persons.remove(toRemove)) {
            String error = "The contact does not exist!";
            throw new PersonNotFoundException(error);
        }
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Person> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSamePerson(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

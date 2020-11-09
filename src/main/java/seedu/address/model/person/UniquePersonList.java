package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList implements Iterable<Person> {
    private final ObservableList<Person> internalList = FXCollections.observableArrayList();

    private final ObservableList<Person> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPerson);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Replaces the specified {@code target} with {@code editedTag} for all contacts.
     */
    public void setContactTag(Tag target, Tag editedTag) {
        requireAllNonNull(target, editedTag);
        int count = internalList.size();
        // Iterate through all contacts and update their tags.
        for (int i = 0; i < count; i++) {
            Person original = internalList.get(i);
            Set<Tag> tags = new HashSet<>(original.getTags());
            if (tags.contains(target)) {
                tags.remove(target);
                tags.add(editedTag);
                Person p = new Person(original.getId(),
                        original.getName(),
                        original.getPhone(),
                        original.getEmail(),
                        original.getAddress(),
                        tags,
                        original.getRemark(),
                        original.isArchived());
                internalList.set(i, p);
            }
        }
    }

    /**
     * Removes the specified tag from all contacts.
     */
    public void removeContactTag(Tag toRemove) {
        requireNonNull(toRemove);
        int count = internalList.size();
        for (int i = 0; i < count; i++) {
            Person original = internalList.get(i);
            Set<Tag> tags = new HashSet<>(original.getTags());
            if (tags.contains(toRemove)) {
                tags.remove(toRemove);
                Person p = new Person(original.getId(),
                        original.getName(),
                        original.getPhone(),
                        original.getEmail(),
                        original.getAddress(),
                        tags,
                        original.getRemark(),
                        original.isArchived());
                internalList.set(i, p);
            }
        }
    }

    /**
     * Returns true if the {@code target} tag has no occurrences in StonksBook.
     */
    public boolean hasZeroOccurrences(Tag target) {
        requireNonNull(target);
        for (Person p : internalList) {
            if (p.getTags().contains(target)) {
                return false;
            }
        }
        return true;
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(persons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
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

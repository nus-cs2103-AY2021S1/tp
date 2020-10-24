package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Wraps all data at the projact level
 * Duplicates are not allowed (by .isSamePerson comparison and by .isSameTag comparison)
 */
public class Projact implements ReadOnlyProjact {

    private final UniquePersonList persons;
    private final UniqueTagList tags;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tags = new UniqueTagList();
    }

    public Projact() {}

    /**
     * Creates an Projact using the Persons in the {@code toBeCopied}
     */
    public Projact(ReadOnlyProjact toBeCopied) {
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
     * Replaces the contents of the tag list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     */
    public void setTags(List<Tag> tags) {
        this.tags.setTags(tags);
    }

    /**
     * Resets the existing data of this {@code Projact} with {@code newData}.
     */
    public void resetData(ReadOnlyProjact newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setTags(newData.getTagList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in Projact.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to Projact.
     * The person must not already exist in Projact.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in Projact.
     * The person identity of {@code editedPerson} must not be the same as another existing person in Projact.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code Projact}.
     * {@code key} must exist in Projact.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// tag-level operations

    /**
     * Returns true if a tag with the same name as {@code tag} exists in Projact.
     */
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return tags.contains(tag);
    }

    /**
     * Adds a tag to Projact.
     * The tag must not already exist in Projact.
     */
    public void addTag(Tag tag) {
        tags.add(tag);
    }

    /**
     * Replaces the given tag {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in Projact.
     * The tag name of {@code editedTag} must not be the same as another existing tag in Projact.
     */
    public void setTag(Tag target, Tag editedTag) {
        requireNonNull(editedTag);

        tags.setTag(target, editedTag);
        persons.setTag(target, editedTag);
    }

    /**
     * Removes {@code key} from this {@code Projact}.
     * {@code key} must exist in Projact.
     */
    public void removeTag(Tag key) {
        tags.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons\t"
                + tags.asUnmodifiableObservableList().size() + " tags";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tag> getTagList() {
        return tags.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Projact // instanceof handles nulls
                && persons.equals(((Projact) other).persons)
                && tags.equals(((Projact) other).tags));
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, tags);
    }
}

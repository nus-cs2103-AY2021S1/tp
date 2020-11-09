package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Tag> PREDICATE_SHOW_ALL_TAGS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' projact file path.
     */
    Path getProjactFilePath();

    /**
     * Sets the user prefs' projact file path.
     */
    void setProjactFilePath(Path projactFilePath);

    /**
     * Replaces projact data with the data in {@code projact}.
     */
    void setProjact(ReadOnlyProjact projact);

    /** Returns the Projact */
    ReadOnlyProjact getProjact();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the projact.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the projact.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the projact.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the projact.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the projact.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns true if a tag with the same identity as {@code tag} exists in the projact.
     */
    boolean hasTag(Tag tag);

    /**
     * Deletes the given tag.
     * The tag must exist in the projact.
     */
    void deleteTag(Tag target);

    /**
     * Adds the given tag.
     * {@code tag} must not already exist in the projact.
     */
    void addTag(Tag tag);


    void addTags(Set<Tag> tags);

    /**
     * Replaces the given tag {@code target} with {@code editedTag}.
     * {@code target} must exist in the projact.
     * The tag identity of {@code editedTag} must not be the same as another existing tag in the projact.
     */
    void setTag(Tag target, Tag editedTag);

    /**
     * Returns the list of contacts who have the {@code target} tag.
     */
    List<Person> findContactsByTag(Tag target);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /** Returns an unmodifiable view of the filtered tag list */
    ObservableList<Tag> getFilteredTagList();

    /**
     * Updates the filter of the filtered tag list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTagList(Predicate<Tag> predicate);

    /** Returns an unmodifiable view of the filtered tag list */
    ObservableList<Tag> getSortedTagList();

    /**
     * Updates the sorted tag list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateSortedTagList(Comparator<Tag> comparator);

    /** Returns an unmodifiable view of the sorted person list */
    ObservableList<Person> getSortedPersonList();

    /**
     * Updates the sorted person list to sort by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedPersonList(Comparator<Person> comparator);

}

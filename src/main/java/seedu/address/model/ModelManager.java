package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the Projact data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Projact projact;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Tag> filteredTags;
    private final SortedList<Person> sortedPersons;
    private final SortedList<Tag> sortedTags;

    /**
     * Initializes a ModelManager with the given projact and userPrefs.
     */
    public ModelManager(ReadOnlyProjact projact, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(projact, userPrefs);

        logger.fine("Initializing with Projact: " + projact + " and user prefs " + userPrefs);

        this.projact = new Projact(projact);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPersons = new FilteredList<>(this.projact.getPersonList());
        this.filteredTags = new FilteredList<>(this.projact.getTagList());
        this.sortedPersons = new SortedList<>(this.projact.getPersonList());
        this.sortedTags = new SortedList<>(this.projact.getTagList());
    }

    public ModelManager() {
        this(new Projact(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getProjactFilePath() {
        return userPrefs.getProjactFilePath();
    }

    @Override
    public void setProjactFilePath(Path projactFilePath) {
        requireNonNull(projactFilePath);
        userPrefs.setProjactFilePath(projactFilePath);
    }

    //=========== Projact ================================================================================

    @Override
    public void setProjact(ReadOnlyProjact projact) {
        this.projact.resetData(projact);
    }

    @Override
    public ReadOnlyProjact getProjact() {
        return projact;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return projact.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        projact.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        projact.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        projact.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return projact.hasTag(tag);
    }

    @Override
    public void deleteTag(Tag target) {
        requireNonNull(target);
        projact.removeTag(target);
    }

    @Override
    public void addTag(Tag tag) {
        requireNonNull(tag);
        projact.addTag(tag);
        updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
    }

    @Override
    public void addTags(Set<Tag> tags) {
        requireNonNull(tags);
        for (Tag tag : tags) {
            if (!hasTag(tag)) {
                addTag(tag);
            }
        }
        updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
    }

    @Override
    public void setTag(Tag target, Tag editedTag) {
        requireAllNonNull(target, editedTag);
        projact.setTag(target, editedTag);
    }

    @Override
    public List<Person> findContactsByTag(Tag target) {
        requireNonNull(target);
        return projact.findContactsByTag(target);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedProjact}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Tag List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Tag} backed by the internal list of
     * {@code versionedProjact}
     */
    @Override
    public ObservableList<Tag> getFilteredTagList() {
        return filteredTags;
    }

    @Override
    public void updateFilteredTagList(Predicate<Tag> predicate) {
        requireNonNull(predicate);
        filteredTags.setPredicate(predicate);
    }

    //=========== Sorted Tag List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Tag} backed by the internal list of
     * {@code versionedProjact}
     */
    @Override
    public ObservableList<Tag> getSortedTagList() {
        return sortedTags;
    }

    @Override
    public void updateSortedTagList(Comparator<Tag> comparator) {
        requireNonNull(comparator);
        sortedTags.setComparator(comparator);
    }

    //=========== Sorted Person List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedProjact}
     */
    @Override
    public ObservableList<Person> getSortedPersonList() {
        return sortedPersons;
    }

    @Override
    public void updateSortedPersonList(Comparator<Person> comparator) {
        requireNonNull(comparator);
        sortedPersons.setComparator(comparator);
    }

    //=========== Miscellaneous =============================================================

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return projact.equals(other.projact)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredTags.equals(other.filteredTags)
                && sortedTags.equals(other.sortedTags)
                && sortedPersons.equals(other.sortedPersons);
    }
}

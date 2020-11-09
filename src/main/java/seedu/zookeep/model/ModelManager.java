package seedu.zookeep.model;

import static java.util.Objects.requireNonNull;
import static seedu.zookeep.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.zookeep.commons.core.GuiSettings;
import seedu.zookeep.commons.core.LogsCenter;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.model.animal.AnimalComparator;
import seedu.zookeep.model.animal.Id;

/**
 * Represents the in-memory model of the ZooKeepBook data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ZooKeepBook zooKeepBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Animal> filteredAnimals;

    /**
     * Initializes a ModelManager with the given zooKeepBook and userPrefs.
     */
    public ModelManager(ReadOnlyZooKeepBook zooKeepBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(zooKeepBook, userPrefs);

        logger.fine("Initializing with ZooKeepBook: " + zooKeepBook + " and user prefs " + userPrefs);

        this.zooKeepBook = new ZooKeepBook(zooKeepBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredAnimals = new FilteredList<>(this.zooKeepBook.getAnimalList());
    }

    public ModelManager() {
        this(new ZooKeepBook(), new UserPrefs());
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
    public Path getZooKeepBookFilePath() {
        return userPrefs.getZooKeepBookFilePath();
    }

    @Override
    public void setZooKeepBookFilePath(Path zooKeepBookFilePath) {
        requireNonNull(zooKeepBookFilePath);
        userPrefs.setZooKeepBookFilePath(zooKeepBookFilePath);
    }

    //=========== ZooKeepBook ================================================================================

    @Override
    public void setZooKeepBook(ReadOnlyZooKeepBook zooKeepBook) {
        this.zooKeepBook.resetData(zooKeepBook);
    }

    @Override
    public ReadOnlyZooKeepBook getZooKeepBook() {
        return zooKeepBook;
    }

    @Override
    public boolean hasAnimal(Animal animal) {
        requireNonNull(animal);
        return zooKeepBook.hasAnimal(animal);
    }

    @Override
    public Optional<Animal> getAnimal(Id id) {
        updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);
        Animal animalFound = null;
        for (Animal animal : filteredAnimals) {
            if (animal.getId().equals(id)) {
                animalFound = animal;
            }
        }
        return Optional.ofNullable(animalFound);
    }

    @Override
    public void deleteAnimal(Animal target) {
        zooKeepBook.removeAnimal(target);
    }

    @Override
    public void addAnimal(Animal animal) {
        zooKeepBook.addAnimal(animal);
        updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);
    }

    @Override
    public void setAnimal(Animal target, Animal editedAnimal) {
        requireAllNonNull(target, editedAnimal);

        zooKeepBook.setAnimal(target, editedAnimal);
    }

    @Override
    public void sortAnimals(AnimalComparator animalComparator) {
        zooKeepBook.sortAnimals(animalComparator);
        updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);
    }

    //=========== Filtered Animal List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Animal} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Animal> getFilteredAnimalList() {
        return filteredAnimals;
    }

    @Override
    public void updateFilteredAnimalList(Predicate<Animal> predicate) {
        requireNonNull(predicate);
        filteredAnimals.setPredicate(predicate);
    }

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
        return zooKeepBook.equals(other.zooKeepBook)
                && userPrefs.equals(other.userPrefs)
                && filteredAnimals.equals(other.filteredAnimals);
    }

}

package seedu.zookeep.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.zookeep.commons.core.GuiSettings;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.model.animal.AnimalComparator;
import seedu.zookeep.model.animal.Id;


/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Animal> PREDICATE_SHOW_ALL_ANIMALS = unused -> true;

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
     * Returns the user prefs' zookeep book file path.
     */
    Path getZooKeepBookFilePath();

    /**
     * Sets the user prefs' zookeep book file path.
     */
    void setZooKeepBookFilePath(Path zooKeepBookFilePath);

    /**
     * Replaces zookeep book data with the data in {@code ZooKeepBook}.
     */
    void setZooKeepBook(ReadOnlyZooKeepBook zooKeepBook);

    /** Returns the ZooKeepBook */
    ReadOnlyZooKeepBook getZooKeepBook();

    /**
     * Returns true if an animal with the same identity as {@code animal} exists in the zookeep book.
     */
    boolean hasAnimal(Animal animal);

    /**
     * Returns optional of animal with the same ID as the given ID
     */
    Optional<Animal> getAnimal(Id id);

    /**
     * Deletes the given animal.
     * The animal must exist in the zookeep book.
     */
    void deleteAnimal(Animal target);

    /**
     * Adds the given animal.
     * {@code animal} must not already exist in the zookeep book.
     */
    void addAnimal(Animal animal);

    /**
     * Replaces the given animal {@code target} with {@code editedAnimal}.
     * {@code target} must exist in the zookeep book.
     * The animal identity of {@code editedAnimal} must not be the same as another existing animal in the zookeep book.
     */
    void setAnimal(Animal target, Animal editedAnimal);

    /**
     * Sorts the animals using the given animal comparator.
     * @param animalComparator Contains the specific comparator required for sorting.
     */
    void sortAnimals(AnimalComparator animalComparator);

    /** Returns an unmodifiable view of the filtered animal list */
    ObservableList<Animal> getFilteredAnimalList();

    /**
     * Updates the filter of the filtered animal list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAnimalList(Predicate<Animal> predicate);
}

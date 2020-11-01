package seedu.zookeep.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.model.animal.AnimalComparator;
import seedu.zookeep.model.animal.UniqueAnimalList;

/**
 * Wraps all data at the zookeep-book level
 * Duplicates are not allowed (by .isSameAnimal comparison)
 */
public class ZooKeepBook implements ReadOnlyZooKeepBook {

    private final UniqueAnimalList animals;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        animals = new UniqueAnimalList();
    }

    public ZooKeepBook() {}

    /**
     * Creates a ZooKeepBook using the Animals in the {@code toBeCopied}
     */
    public ZooKeepBook(ReadOnlyZooKeepBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the animal list with {@code animals}.
     * {@code animals} must not contain duplicate animals.
     */
    public void setAnimals(List<Animal> animals) {
        this.animals.setAnimals(animals);
    }

    /**
     * Resets the existing data of this {@code ZooKeepBook} with {@code newData}.
     */
    public void resetData(ReadOnlyZooKeepBook newData) {
        requireNonNull(newData);

        setAnimals(newData.getAnimalList());
    }

    //// animal-level operations

    /**
     * Returns true if an animal with the same identity as {@code animal} exists in the ZooKeepBook.
     */
    public boolean hasAnimal(Animal animal) {
        requireNonNull(animal);
        return animals.contains(animal);
    }

    /**
     * Adds an animal to the ZooKeepBook.
     * The animal must not already exist in the ZooKeepBook.
     */
    public void addAnimal(Animal p) {
        animals.add(p);
    }

    /**
     * Replaces the given animal {@code target} in the list with {@code editedAnimal}.
     * {@code target} must exist in the ZooKeepBook.
     * The animal identity of {@code editedAnimal} must not be the same as another existing animal in the ZooKeepBook.
     */
    public void setAnimal(Animal target, Animal editedAnimal) {
        requireNonNull(editedAnimal);

        animals.setAnimal(target, editedAnimal);
    }

    /**
     * Removes {@code key} from this {@code ZooKeepBook}.
     * {@code key} must exist in the ZooKeepBook.
     */
    public void removeAnimal(Animal key) {
        animals.remove(key);
    }

    /**
     * Sorts the animals using the given animal comparator.
     * @param animalComparator Contains the specific comparator required for sorting.
     */
    public void sortAnimals(AnimalComparator animalComparator) {
        animals.sortAnimals(animalComparator);
    }

    //// util methods
    @Override
    public String toString() {
        return animals.asUnmodifiableObservableList().size() + " animals";
        // TODO: refine later
    }

    @Override
    public ObservableList<Animal> getAnimalList() {
        return animals.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ZooKeepBook // instanceof handles nulls
                && animals.equals(((ZooKeepBook) other).animals));
    }

    @Override
    public int hashCode() {
        return animals.hashCode();
    }
}

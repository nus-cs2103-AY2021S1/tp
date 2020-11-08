package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Template;
import seedu.address.model.exercise.TemplateList;
import seedu.address.model.exercise.UniqueExerciseList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ExerciseBook implements ReadOnlyExerciseBook {

    private final UniqueExerciseList exercises;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        exercises = new UniqueExerciseList();
    }

    public ExerciseBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public ExerciseBook(ReadOnlyExerciseBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setExercises(List<Exercise> exercises) {
        this.exercises.setExercises(exercises);
    }

    /**
     * Resets the existing data of this {@code ExerciseBook} with {@code newData}.
     */
    public void resetData(ReadOnlyExerciseBook newData) {
        requireNonNull(newData);

        setExercises(newData.getExerciseList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return exercises.contains(exercise);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addExercise(Exercise p) {
        exercises.add(p);
    }

    /**
     * Return true if the new exercise will cause Integer Overflow.
     * An overflow occurs when the calories burnt from all the exercises done for the
     * day is more than INTEGER.MAX_VALUE.
     * @param e The new exercise to be added.
     */
    public boolean checkOverflow(Exercise e) {
        Integer oldCalories = exercises.getCaloriesForDay(e.getDate().value);
        Integer valueToAdd = Integer.parseInt(e.getCalories().value);

        oldCalories = oldCalories == null ? 0 : oldCalories;

        //Since valueToAdd is always > 0, the given case is true only when an Integer Overflow occurs.
        if (oldCalories + valueToAdd < oldCalories) {
            return true;
        }

        return false;
    }

    /**
     * Return true if the new exercise will cause Integer Overflow.
     * An overflow occurs when the calories burnt from all the exercises done for the
     * day is more than INTEGER.MAX_VALUE.
     * @param oldE The old Exercise to be removed.
     * @param newE The new Exercise to be added.
     */
    public boolean checkOverflow(Exercise oldE, Exercise newE) {
        if (oldE.getDate().value.equals(newE.getDate().value)) {
            Integer currentCalories = exercises.getCaloriesForDay(oldE.getDate().value);
            Integer valueToMinus = Integer.parseInt(oldE.getCalories().value);
            Integer valueToAdd = Integer.parseInt(newE.getCalories().value);
            if (currentCalories - valueToMinus + valueToAdd < 0) {
                return true;
            }
            return false;
        }

        Integer currentCaloriesForNewE = exercises.getCaloriesForDay(newE.getDate().value);
        currentCaloriesForNewE = currentCaloriesForNewE == null ? 0 : currentCaloriesForNewE;
        Integer valueToAdd = Integer.parseInt(newE.getCalories().value);
        if (currentCaloriesForNewE + valueToAdd < 0) {
            return true;
        }
        return false;

    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireNonNull(editedExercise);
        exercises.updateExercise(target, editedExercise);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeExercise(Exercise key) {
        exercises.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return exercises.asUnmodifiableObservableList().size() + " exercises";
        // TODO: refine later
    }

    @Override
    public HashMap<String, Integer> getCaloriesByDay() {
        return exercises.getCaloriesByDay();
    }

    @Override
    public ObservableList<Exercise> getExerciseList() {
        return exercises.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Template> getTemplateList() {
        return TemplateList.getObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExerciseBook // instanceof handles nulls
                && exercises.equals(((ExerciseBook) other).exercises));
    }

    @Override
    public int hashCode() {
        return exercises.hashCode();
    }
}

package seedu.address.model.calorie;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.calorie.exceptions.DailyCalorieNotFoundException;
import seedu.address.model.calorie.exceptions.DuplicateDailyCalorieException;

/**
 * A list of daily calorie entries that enforces uniqueness between its elements and does not allow nulls.
 * A daily calorie entry is considered unique by comparing using {@code DailyCalorie#isSameDailyCalorie(DailyCalorie)}.
 * As such, adding and updating of
 * daily calorie entries uses DailyCalorie#isSameDailyCalorie(DailyCalorie) for equality
 * so as to ensure that the entry being added or updated is unique in terms of identity in the CalorieLog.
 * However, the removal of an entry uses DailyCalorie#equals(Object) so
 * as to ensure that the entry with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see DailyCalorie#isSameDailyCalorie(DailyCalorie)
 */
public class CalorieLog implements Iterable<DailyCalorie> {

    private final ObservableList<DailyCalorie> internalList = FXCollections.observableArrayList();
    private final ObservableList<DailyCalorie> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent DailyCalorie as the given argument.
     */
    public boolean contains(DailyCalorie toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDailyCalorie);
    }

    /**
     * Adds a daily calorie entry to the log.
     * The DailyCalorie must not already exist in the list.
     */
    public void add(DailyCalorie toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDailyCalorieException();
        }
        internalList.add(toAdd);
        Collections.sort(internalList);
    }

    /**
     * Adds a calories to an entry in the log.
     * If DailyCalorie does not already exist in the list,
     * then it must be a different day.
     */
    public void addCalories(Calorie calorie) {
        boolean isContained = false;
        for (DailyCalorie entry : internalList) {
            if (entry.getDate().equals(LocalDate.now())) {
                int index = internalList.indexOf(entry);
                entry.addCalories(calorie.getCalorie());
                internalList.set(index, entry);
                isContained = true;
                break;
            }
        }

        if (!isContained) {

            if (checkSize() >= 7) {
                internalList.remove(0);
            }
            DailyCalorie newDay = new DailyCalorie(LocalDate.now());
            newDay.addCalories(calorie.getCalorie());
            internalList.add(newDay);
            Collections.sort(internalList);
        }
    }

    /**
     * Adds a calories to an entry in the log.
     * If DailyCalorie does not already exist in the list,
     * then it must be a different day.
     */
    public void minusCalories(Calorie calorie) {
        boolean isContained = false;
        for (DailyCalorie entry : internalList) {
            if (entry.getDate().equals(LocalDate.now())) {
                int index = internalList.indexOf(entry);
                entry.minusCalories(calorie.getCalorie());
                internalList.set(index, entry);
                isContained = true;
                break;
            }
        }

        if (!isContained) {
            throw new DailyCalorieNotFoundException();
        }
    }

    /**
     * Replaces the dailyCalorie {@code target} in the list with {@code editedDailyCalorie}.
     * {@code target} must exist in the list.
     * The DailyCalorie identity of {@code editedDailyCalorie} must not be the same as another existing dailyCalorie
     * in the list.
     */
    public void setDailyCalorie(DailyCalorie target, DailyCalorie editedDailyCalorie) {
        requireAllNonNull(target, editedDailyCalorie);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DailyCalorieNotFoundException();
        }

        if (!target.isSameDailyCalorie(editedDailyCalorie) && contains(editedDailyCalorie)) {
            throw new DuplicateDailyCalorieException();
        }

        internalList.set(index, editedDailyCalorie);
    }

    /**
     * Replaces the existing DailyCalorie with DailyCalorie from a replacement CalorieLog.
     * @param replacement CalorieLog to replace this existing one.
     */
    public void setDailyCalories(CalorieLog replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code dailyCalories}.
     * {@code dailyCalories} must not contain duplicate DailyCalorie entries.
     */
    public void setDailyCalories(List<DailyCalorie> dailyCalories) {
        requireAllNonNull(dailyCalories);
        if (!entriesAreUnique(dailyCalories)) {
            throw new DuplicateDailyCalorieException();
        }
        internalList.setAll(dailyCalories);
    }

    /**
     * Returns true if {@code dailyCalories} contains only unique Daily Calorie.
     */
    private boolean entriesAreUnique(List<DailyCalorie> dailyCalories) {
        for (int i = 0; i < dailyCalories.size() - 1; i++) {
            for (int j = i + 1; j < dailyCalories.size(); j++) {
                if (dailyCalories.get(i).isSameDailyCalorie(dailyCalories.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Removes the equivalent DailyCalorie from the list.
     * The DailyCalorie must exist in the list.
     */
    public void remove(DailyCalorie toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DailyCalorieNotFoundException();
        }
        internalList.remove(toRemove);
    }

    /**
     * Returns the size of the CalorieLog.
     *
     * @return Integer of the size of the CalorieLog.
     */
    public int checkSize() {
        return this.internalList.size();
    }

    /**
     * Replaces the contents of this list with {@code calorieLog}.
     * {@code calorieLog} must not contain duplicate dailyCalories.
     */
    public void setCalorieLog(List<DailyCalorie> calorieLog) {
        requireAllNonNull(calorieLog);
        if (!calorieLogsAreUnique(calorieLog)) {
            throw new DuplicateDailyCalorieException();
        }
        internalList.setAll(calorieLog);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<DailyCalorie> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<DailyCalorie> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalorieLog // instanceof handles nulls
                && internalList.equals(((CalorieLog) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code entries} contains only unique daily calorie entries.
     */
    private boolean calorieLogsAreUnique(List<DailyCalorie> calorieLogs) {
        for (int i = 0; i < calorieLogs.size() - 1; i++) {
            for (int j = i + 1; j < calorieLogs.size(); j++) {
                if (calorieLogs.get(i).isSameDailyCalorie(calorieLogs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Retrieves the calories for today.
     * @return The calorie count for today.
     */
    public int getCalories() {
        for (DailyCalorie entry : internalList) {
            if (entry.getDate().equals(LocalDate.now())) {
                return entry.getCalories();
            }
        }
        return 0;
    }

}

package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.day.Day;
import seedu.address.model.person.Person;
import seedu.address.model.person.Profile;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Day> PREDICATE_SHOW_ALL_DAYS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns the user prefs' myFitnessBuddy file path.
     */
    Path getMyFitnessBuddyFilePath();

    /**
     * Sets the user prefs' myFitnessBuddy file path.
     */
    void setMyFitnessBuddyFilePath(Path myFitnessBuddyFilePath);

    /**
     * Replaces myFitnessBuddy data records with the data in {@code myFitnessBuddy}.
     */
    void setMyFitnessBuddy(ReadOnlyMyFitnessBuddy myFitnessBuddy);

    /** Returns MyFitnessBuddy */
    ReadOnlyMyFitnessBuddy getMyFitnessBuddy();

    /**
     * Returns true if a day with the same values as {@code day} exists in the days.
     */
    boolean hasDay(Day day);

    /**
     * Returns true if a day with the same date as {@code date} exists in the days.
     */
    boolean hasDay(LocalDate date);

    /**
     * Returns the day with given {@code date}.
     */
    Day getDay(LocalDate date);

    /**
     * Deletes the given day.
     * The day must exist in the list of days in my fitness buddy.
     */
    void deleteDay(Day target);

    /**
     * Adds the given day.
     * {@code day} must not already exist in the list of days in my fitness buddy.
     */
    void addDay(Day day);

    /**
     * Replaces the given day {@code target} with {@code editedDay}.
     * {@code target} must exist in the days.
     * The day identity of {@code editedPerson} must not be the same as another existing day in the list of days.
     */
    void setDay(Day target, Day editedDay);

    /** Returns an unmodifiable view of the filtered day list */
    ObservableList<Day> getFilteredDayList();

    /** Returns an unmodifiable view of the filtered day list */
    ObservableList<Person> getFilteredPersonList();


    /**
     * Updates the filter of the filtered day list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDayList(Predicate<Day> predicate);

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);


    /**
     * Updates the profile of a person in MyFitnessBuddy.
     */
    void setProfile(Profile profile);

    /**
     * Checks if the current data {@code MyFitnessBuddy} has a profile.
     */
    boolean isDefaultProfile();

    /**
     * Sets the currentPerson pointer to toSet
     */
    void setCurrentPerson(Person toSet);

    /**
     * Checks if a person is in the list
     */
    boolean hasPerson(Person toCheck);

    /**
     * Update day
     */
    void updateDay();

    /**
     * Add new person
     */
    void addPerson(Person toAdd);

    /**
     * Add new person
     */
    void resetPersons();
}

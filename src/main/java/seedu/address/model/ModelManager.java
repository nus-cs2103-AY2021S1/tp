package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.day.Day;
import seedu.address.model.person.Person;
import seedu.address.model.person.Profile;


/**
 * Represents the in-memory model of My Fitness Buddy data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final MyFitnessBuddy myFitnessBuddy;
    private final UserPrefs userPrefs;
    private final FilteredList<Day> filteredDays;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given myFitnessBuddy and userPrefs.
     */
    public ModelManager(ReadOnlyMyFitnessBuddy readOnlyMyFitnessBuddy, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(readOnlyMyFitnessBuddy, userPrefs);

        logger.fine("Initializing with MyFitnessBuddy: " + readOnlyMyFitnessBuddy + " and user prefs " + userPrefs);

        this.myFitnessBuddy = new MyFitnessBuddy(readOnlyMyFitnessBuddy);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredDays = new FilteredList<>(this.myFitnessBuddy.getDayList());
        filteredPersons = new FilteredList<>(this.myFitnessBuddy.getPersonList());
    }

    public ModelManager() {
        this(new MyFitnessBuddy(), new UserPrefs());
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
    public Path getMyFitnessBuddyFilePath() {
        return userPrefs.getMyFitnessBuddyFilePath();
    }

    /**
     * Sets the user prefs' My Fitness Buddy file path.
     *
     * @param myFitnessBuddyFilePath
     */
    @Override
    public void setMyFitnessBuddyFilePath(Path myFitnessBuddyFilePath) {
        requireNonNull(myFitnessBuddyFilePath);
        userPrefs.setMyFitnessBuddyFilePath(myFitnessBuddyFilePath);
    }

    //=========== My Fitness Buddy ================================================================================

    @Override
    public void setMyFitnessBuddy(ReadOnlyMyFitnessBuddy myFitnessBuddy) {
        this.myFitnessBuddy.resetData(myFitnessBuddy);
    }

    /**
     * Returns my fitness buddy.
     */
    @Override
    public ReadOnlyMyFitnessBuddy getMyFitnessBuddy() {
        return myFitnessBuddy;
    }

    @Override
    public boolean hasDay(Day day) {
        requireNonNull(day);
        return myFitnessBuddy.hasDay(day);
    }

    @Override
    public boolean hasDay(LocalDate date) {
        return myFitnessBuddy.hasDay(date);
    }

    @Override
    public Day getDay(LocalDate date) {
        return myFitnessBuddy.getDay(date);
    }

    @Override
    public void deleteDay(Day target) {
        myFitnessBuddy.removeDay(target);
    }

    @Override
    public void addDay(Day day) {
        myFitnessBuddy.addDay(day);
        updateFilteredDayList(PREDICATE_SHOW_ALL_DAYS);
    }

    @Override
    public void addPerson(Person toAdd) {
        myFitnessBuddy.addPerson(toAdd);
    }

    @Override
    public void setCurrentPerson(Person toSet) {
        myFitnessBuddy.setCurrentPerson(toSet);
    }
    @Override
    public void setDay(Day target, Day editedDay) {
        requireAllNonNull(target, editedDay);
        myFitnessBuddy.setDay(target, editedDay);
    }

    //=========== Filtered Day List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Day} backed by the internal list of
     * {@code versionedMyFitnessBuddy}
     */
    @Override
    public ObservableList<Day> getFilteredDayList() {
        return filteredDays;
    }

    @Override
    public void updateFilteredDayList(Predicate<Day> predicate) {
        requireNonNull(predicate);
        filteredDays.setPredicate(predicate);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Day} backed by the internal list of
     * {@code versionedMyFitnessBuddy}
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


    @Override
    public void setProfile(Profile profile) {
        myFitnessBuddy.setProfile(profile);
    }

    @Override
    public void resetPersons() {
        myFitnessBuddy.resetPersons();
    }

    public boolean isDefaultProfile() {
        return myFitnessBuddy.isDefaultProfile();
    }

    @Override
    public boolean hasPerson(Person toCheck) {
        return myFitnessBuddy.hasPerson(toCheck);
    }

    @Override
    public void updateDay() {
        myFitnessBuddy.updateDay();
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
        return myFitnessBuddy.equals(other.myFitnessBuddy)
                && userPrefs.equals(other.userPrefs)
                && filteredDays.equals(other.filteredDays);
    }

}

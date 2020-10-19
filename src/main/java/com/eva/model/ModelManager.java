package com.eva.model;

import static com.eva.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import com.eva.commons.core.GuiSettings;
import com.eva.commons.core.LogsCenter;
import com.eva.model.person.Person;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final EvaDatabase<Person> personDatabase;
    private final EvaDatabase<Staff> staffDatabase;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Staff> filteredStaffs;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyEvaDatabase<Person> personDatabase,
            ReadOnlyEvaDatabase<Staff> staffDatabase, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(personDatabase, staffDatabase, userPrefs);

        logger.fine("Initializing with person database: " + personDatabase
                + " and staff database: " + staffDatabase
                + " and user prefs " + userPrefs);

        this.personDatabase = new EvaDatabase<>(personDatabase);
        this.staffDatabase = new EvaDatabase<>(staffDatabase);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.personDatabase.getPersonList());
        filteredStaffs = new FilteredList<>(this.staffDatabase.getPersonList());
    }

    public ModelManager() {
        this(new EvaDatabase<>(), new EvaDatabase<>(), new UserPrefs());
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
    public Path getEvaDatabaseFilePath() {
        return userPrefs.getPersonDatabaseFilePath();
    }

    @Override
    public Path getStaffDatabaseFilePath() {
        return userPrefs.getStaffDatabaseFilePath();
    }

    @Override
    public void setEvaDatabaseFilePath(Path evaDatabaseFilePath) {
        requireNonNull(evaDatabaseFilePath);
        userPrefs.setPersonDatabaseFilePath(evaDatabaseFilePath);
    }

    @Override
    public void setStaffDatabaseFilePath(Path staffDatabaseFilePath) {
        requireNonNull(staffDatabaseFilePath);
        userPrefs.setStaffDatabaseFilePath(staffDatabaseFilePath);
    }

    //=========== person database ================================================================================

    @Override
    public void setPersonDatabase(ReadOnlyEvaDatabase<Person> personDatabase) {
        this.personDatabase.resetData(personDatabase);
    }

    @Override
    public ReadOnlyEvaDatabase<Person> getPersonDatabase() {
        return personDatabase;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return personDatabase.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        personDatabase.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        personDatabase.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addStaffLeave(Staff target, Leave leave) {
        target.getLeaves().add(leave);
        target.getLeaveBalance().deductLeaveBalance(leave.getLeaveLength());
    }

    @Override
    public void deleteStaffLeave(Staff target, Leave leave) {
        target.getLeaves().remove(leave);
        target.getLeaveBalance().addLeaveBalance(leave.getLeaveLength());
    }

    @Override
    public boolean hasStaffLeave(Staff target, Leave leave) {
        return target.getLeaves().contains(leave);
    }

    @Override
    public Optional<Leave> hasLeaveDate(Staff target, LocalDate date) {
        List<Leave> staffLeaves = new ArrayList<>(target.getLeaves());
        for (Leave leave : staffLeaves) {
            boolean dateOnStart = leave.getStartDate().isEqual(date);
            boolean dateOnEnd = leave.getEndDate().isEqual(date);
            boolean dateBetween = leave.getStartDate().isBefore(date) && leave.getEndDate().isAfter(date);
            if (dateOnStart || dateOnEnd || dateBetween) {
                return Optional.of(leave);
            }
        }
        return Optional.empty();
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        personDatabase.setPerson(target, editedPerson);
    }

    //=========== staff database ================================================================================

    @Override
    public void setStaffDatabase(ReadOnlyEvaDatabase<Staff> staffDatabase) {
        this.staffDatabase.resetData(staffDatabase);
    }

    @Override
    public ReadOnlyEvaDatabase<Staff> getStaffDatabase() {
        return staffDatabase;
    }

    @Override
    public boolean hasStaff(Staff staff) {
        requireNonNull(staff);
        return staffDatabase.hasPerson(staff);
    }

    @Override
    public void deleteStaff(Staff target) {
        staffDatabase.removePerson(target);
    }

    @Override
    public void addStaff(Staff person) {
        staffDatabase.addPerson(person);
        updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);
    }

    @Override
    public void setStaff(Staff target, Staff editedPerson) {
        requireAllNonNull(target, editedPerson);
        staffDatabase.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
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

    //=========== Filtered Staff List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Staff> getFilteredStaffList() {
        return filteredStaffs;
    }

    @Override
    public void updateFilteredStaffList(Predicate<Staff> predicate) {
        requireNonNull(predicate);
        filteredStaffs.setPredicate(predicate);
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
        return personDatabase.equals(other.personDatabase)
                && staffDatabase.equals(other.staffDatabase)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}

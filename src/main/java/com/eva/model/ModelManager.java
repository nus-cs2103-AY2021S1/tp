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
import com.eva.commons.core.PanelState;
import com.eva.commons.util.DateUtil;
import com.eva.model.current.view.CurrentViewApplicant;
import com.eva.model.current.view.CurrentViewStaff;
import com.eva.model.current.view.ReadOnlyCurrentViewApplicant;
import com.eva.model.current.view.ReadOnlyCurrentViewStaff;
import com.eva.model.person.Person;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.applicant.application.Application;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the eva database data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final EvaDatabase<Person> personDatabase;
    private final EvaDatabase<Staff> staffDatabase;
    private final EvaDatabase<Applicant> applicantDatabase;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Staff> filteredStaffs;
    private final FilteredList<Applicant> filteredApplicants;
    private ReadOnlyCurrentViewStaff currentViewStaff;
    private ReadOnlyCurrentViewApplicant currentViewApplicant;


    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyEvaDatabase<Person> personDatabase, ReadOnlyEvaDatabase<Staff> staffDatabase,
                        ReadOnlyEvaDatabase<Applicant> applicantDatabase, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(personDatabase, staffDatabase, applicantDatabase, userPrefs);

        logger.fine("Initializing with person database: " + personDatabase
                + " and staff database: " + staffDatabase
                + " and applicant database: " + applicantDatabase
                + " and user prefs " + userPrefs);

        this.personDatabase = new EvaDatabase<>(personDatabase);
        this.staffDatabase = new EvaDatabase<>(staffDatabase);
        this.applicantDatabase = new EvaDatabase<>(applicantDatabase);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.personDatabase.getPersonList());
        filteredStaffs = new FilteredList<>(this.staffDatabase.getPersonList());
        filteredApplicants = new FilteredList<>(this.applicantDatabase.getPersonList());

        this.currentViewStaff = new CurrentViewStaff();
        this.currentViewApplicant = new CurrentViewApplicant();
    }

    public ModelManager() {
        this(new EvaDatabase<>(), new EvaDatabase<>(), new EvaDatabase<>(), new UserPrefs());
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
    public PanelState getPanelState() {
        return userPrefs.getPanelState();
    }

    @Override
    public void setPanelState(PanelState panelState) {
        userPrefs.setPanelState(panelState);
    }

    @Override
    public void setCurrentViewStaff(CurrentViewStaff currentViewStaff) {
        if (currentViewStaff.getCurrentView().isPresent()) {
            this.currentViewStaff = currentViewStaff;
        } else {
            throw new IllegalArgumentException(); // placeholder exception
        }
    }

    @Override
    public void setCurrentViewApplicant(CurrentViewApplicant currentViewApplicant) {
        if (currentViewApplicant.getCurrentView().isPresent()) {
            this.currentViewApplicant = currentViewApplicant;
        } else {
            throw new IllegalArgumentException(); // placeholder exception
        }
    }

    @Override
    public Path getPersonDatabaseFilePath() {
        return userPrefs.getPersonDatabaseFilePath();
    }

    @Override
    public Path getStaffDatabaseFilePath() {
        return userPrefs.getStaffDatabaseFilePath();
    }

    @Override
    public Path getApplicantDatabaseFilePath() {
        return userPrefs.getApplicantDatabaseFilePath();
    }

    @Override
    public void setPersonDatabaseFilePath(Path evaDatabaseFilePath) {
        requireNonNull(evaDatabaseFilePath);
        userPrefs.setPersonDatabaseFilePath(evaDatabaseFilePath);
    }

    @Override
    public void setStaffDatabaseFilePath(Path staffDatabaseFilePath) {
        requireNonNull(staffDatabaseFilePath);
        userPrefs.setStaffDatabaseFilePath(staffDatabaseFilePath);
    }

    @Override
    public void setApplicantDatabaseFilePath(Path applicantDatabaseFilePath) {
        requireNonNull(applicantDatabaseFilePath);
        userPrefs.setStaffDatabaseFilePath(applicantDatabaseFilePath);
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
    public boolean hasLeavePeriod(Staff target, Leave toCheck) {
        List<LocalDate> leaveDates = DateUtil.getDatesBetween(toCheck.getStartDate(), toCheck.getEndDate());
        for (LocalDate date : leaveDates) {
            if (hasLeaveDate(target, date).isPresent()) {
                return true;
            }
        }
        return false;
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
    public void addStaff(Staff staff) {
        staffDatabase.addPerson(staff);
        updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);
    }

    @Override
    public void setStaff(Staff target, Staff editedStaff) {
        requireAllNonNull(target, editedStaff);
        staffDatabase.setPerson(target, editedStaff);
    }

    //=========== applicant database ================================================================================

    @Override
    public void setApplicantDatabase(ReadOnlyEvaDatabase<Applicant> applicantDatabase) {
        this.applicantDatabase.resetData(applicantDatabase);
    }

    @Override
    public ReadOnlyEvaDatabase<Applicant> getApplicantDatabase() {
        return applicantDatabase;
    }

    @Override
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicantDatabase.hasPerson(applicant);
    }

    @Override
    public void deleteApplicant(Applicant target) {
        applicantDatabase.removePerson(target);
    }

    @Override
    public void addApplicant(Applicant applicant) {
        applicantDatabase.addPerson(applicant);
        updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
    }

    @Override
    public void addApplicantApplication(Applicant target, Application toAdd) {
        target.setApplication(toAdd);
    }

    @Override
    public void setApplicant(Applicant target, Applicant editedPerson) {
        requireAllNonNull(target, editedPerson);
        applicantDatabase.setPerson(target, editedPerson);
    }

    @Override
    public void setApplicationStatus(Applicant applicant, ApplicationStatus status) {
        applicant.setApplicationStatus(status);
    }

    @Override
    public void deleteApplication(Applicant target, Application toSet) {
        target.setApplication(toSet);
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
    public ReadOnlyCurrentViewStaff getCurrentViewStaff() {
        return currentViewStaff;
    }

    @Override
    public void updateFilteredStaffList(Predicate<Staff> predicate) {
        requireNonNull(predicate);
        filteredStaffs.setPredicate(predicate);
    }

    //=========== Filtered Applicant List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Applicant> getFilteredApplicantList() {
        return filteredApplicants;
    }

    @Override
    public ReadOnlyCurrentViewApplicant getCurrentViewApplicant() {
        return currentViewApplicant;
    }

    @Override
    public void updateFilteredApplicantList(Predicate<Applicant> predicate) {
        requireNonNull(predicate);
        filteredApplicants.setPredicate(predicate);
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
                && applicantDatabase.equals(other.applicantDatabase)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}

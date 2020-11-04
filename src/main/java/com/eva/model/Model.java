package com.eva.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Predicate;

import com.eva.commons.core.GuiSettings;
import com.eva.commons.core.PanelState;
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

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Staff> PREDICATE_SHOW_ALL_STAFFS = unused -> true;
    Predicate<Applicant> PREDICATE_SHOW_ALL_APPLICANTS = unused -> true;

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
     * Returns the current PanelState.
     */
    PanelState getPanelState();

    /**
     * Sets the current panelState according to what the user is viewing.
     */
    void setPanelState(PanelState panelState);

    /**
     * Sets the currentViewStaff to the staff the user is currently viewing.
     */
    void setCurrentViewStaff(CurrentViewStaff currentViewStaff);

    /**
     * Sets the currentViewApplicant to the applicant the user is currently viewing.
     */
    void setCurrentViewApplicant(CurrentViewApplicant currentViewStaff);

    /**
     * Returns the current staff being viewed.
     */
    ReadOnlyCurrentViewStaff getCurrentViewStaff();

    /**
     * Returns the current applicant being viewed.
     */
    ReadOnlyCurrentViewApplicant getCurrentViewApplicant();

    /**
     * Returns the user prefs' persons data file path.
     */
    Path getPersonDatabaseFilePath();

    /**
     * Returns the user prefs' staff data file path.
     */
    Path getStaffDatabaseFilePath();

    /**
     * Returns the user prefs' applicant data file path.
     */
    Path getApplicantDatabaseFilePath();

    /**
     * Sets the user prefs' person database file path.
     */
    void setPersonDatabaseFilePath(Path evaDatabaseFilePath);

    /**
     * Sets the user prefs' staff database file path.
     */
    void setStaffDatabaseFilePath(Path staffDatabaseFilePath);

    /**
     * Sets the user prefs' applicant database file path.
     */
    void setApplicantDatabaseFilePath(Path applicantDatabaseFilePath);

    /**
     * Replaces eva database data with the data in {@code addressBook}.
     */
    void setPersonDatabase(ReadOnlyEvaDatabase<Person> personDatabase);

    /** Returns the EvaDatabase */
    ReadOnlyEvaDatabase<Person> getPersonDatabase();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the eva database.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the eva database.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the eva database.
     */
    void addPerson(Person person);

    /**
     * Adds the given leave to the given staff.
     * {@code leave} must not already exist in the staff's {@code leaves}.
     */
    void addStaffLeave(Staff target, Leave leave);

    /**
     * Deletes the given leave from the given staff.
     * {@code leave} must already exist in the staff's {@code leaves}.
     */
    void deleteStaffLeave(Staff target, Leave leave);

    /**
     * Returns true if a staff with the same identity as {@code staff} has the same leave as {@code leave}.
     */
    boolean hasStaffLeave(Staff target, Leave leave);

    /**
     * Returns the conflicting leave if a staff with the same identity as {@code staff}
     * has the same leave period as {@code leave}.
     */
    Optional<Leave> hasLeaveDate(Staff target, LocalDate date);

    /**
     * Returns true if a staff with the same identity as {@code staff}
     * has the same leave period as {@code leave}.
     */
    boolean hasLeavePeriod(Staff target, Leave leave);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the eva database.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the eva database.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Replaces eva database data with the data in {@code addressBook}.
     */
    void setStaffDatabase(ReadOnlyEvaDatabase<Staff> personDatabase);

    /**
     * Returns the EvaDatabase.
     */
    ReadOnlyEvaDatabase<Staff> getStaffDatabase();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the eva database.
     */
    boolean hasStaff(Staff person);

    /**
     * Deletes the given staff.
     * The staff must exist in the eva database.
     */
    void deleteStaff(Staff target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the eva database.
     */
    void addStaff(Staff person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the eva database.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the eva database.
     */
    void setStaff(Staff target, Staff editedPerson);

    /**
     * Returns an unmodifiable view of the filtered person list.
     */
    ObservableList<Staff> getFilteredStaffList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStaffList(Predicate<Staff> predicate);

    /**
     * Replaces eva database data with the data in {@code addressBook}.
     */
    void setApplicantDatabase(ReadOnlyEvaDatabase<Applicant> personDatabase);

    /** Returns the EvaDatabase */
    ReadOnlyEvaDatabase<Applicant> getApplicantDatabase();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the eva database.
     */
    boolean hasApplicant(Applicant person);

    /**
     * Deletes the given person.
     * The person must exist in the eva database.
     */
    void deleteApplicant(Applicant target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the eva database.
     */
    void addApplicant(Applicant person);

    /**
     * Adds the given leave to the given staff.
     * {@code leave} must not already exist in the staff's {@code leaves}.
     */
    void addApplicantApplication(Applicant target, Application toAdd);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the eva database.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the eva database.
     */
    void setApplicant(Applicant target, Applicant editedPerson);

    void setApplicationStatus(Applicant applicant, ApplicationStatus status);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Applicant> getFilteredApplicantList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredApplicantList(Predicate<Applicant> predicate);

    void deleteApplication(Applicant target, Application toSet);
}

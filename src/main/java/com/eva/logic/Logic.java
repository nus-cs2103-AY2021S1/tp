package com.eva.logic;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import com.eva.commons.core.GuiSettings;
import com.eva.commons.core.PanelState;
import com.eva.logic.commands.CommandResult;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.current.view.CurrentViewApplicant;
import com.eva.model.current.view.CurrentViewStaff;
import com.eva.model.current.view.ReadOnlyCurrentViewApplicant;
import com.eva.model.current.view.ReadOnlyCurrentViewStaff;
import com.eva.model.person.Person;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.staff.Staff;

import javafx.collections.ObservableList;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException, FileNotFoundException;

    /**
     * Returns the EvaDatabase.
     *
     * @see com.eva.model.Model#getEvaDatabase()
     */
    ReadOnlyEvaDatabase<Person> getEvaDatabase();

    /**
     * Returns the StaffDatabase.
     *
     * @see com.eva.model.Model#getStaffDataBase()
     */
    ReadOnlyEvaDatabase<Staff> getStaffDataBase();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of staff */
    ObservableList<Staff> getFilteredStaffList();

    /** Returns an unmodifiable view of the filtered list of applicants */
    ObservableList<Applicant> getFilteredApplicantList();

    /**
     * Returns the user prefs' persons data file path.
     */
    Path getEvaDatabaseFilePath();
    /**
     * Returns the user prefs' staff data file path.
     */
    Path getStaffDatabaseFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Returns the current PanelState.
     */
    PanelState getPanelState();

    /**
     * Returns the current staff being viewed.
     */
    ReadOnlyCurrentViewStaff getCurrentViewStaff();

    /**
     * Returns the current staff being viewed.
     */
    ReadOnlyCurrentViewApplicant getCurrentViewApplicant();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}

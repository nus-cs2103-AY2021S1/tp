package com.eva.logic;

import java.nio.file.Path;

import com.eva.commons.core.GuiSettings;
import com.eva.logic.commands.CommandResult;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.person.Person;
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
    CommandResult execute(String commandText) throws CommandException, ParseException;

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

    ObservableList<Staff> getFilteredStaffList();

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
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}

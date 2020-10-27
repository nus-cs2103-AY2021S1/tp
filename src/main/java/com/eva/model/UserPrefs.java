package com.eva.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import com.eva.commons.core.GuiSettings;
import com.eva.commons.core.PanelState;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path personDatabaseFilePath = Paths.get("data" , "personDatabase.json");
    private Path staffDatabaseFilePath = Paths.get("data" , "staffDatabase.json");
    private Path applicantDatabaseFilePath = Paths.get("data" , "applicantDatabase.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setPersonDatabaseFilePath(newUserPrefs.getPersonDatabaseFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public PanelState getPanelState() {
        return guiSettings.getPanelState();
    }

    public void setPanelState(PanelState panelState) {
        guiSettings.setPanelState(panelState);
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getPersonDatabaseFilePath() {
        return personDatabaseFilePath;
    }

    public void setPersonDatabaseFilePath(Path personDatabaseFilePath) {
        requireNonNull(personDatabaseFilePath);
        this.personDatabaseFilePath = personDatabaseFilePath;
    }

    public Path getStaffDatabaseFilePath() {
        return staffDatabaseFilePath;
    }

    public void setStaffDatabaseFilePath(Path staffDatabaseFilePath) {
        requireNonNull(staffDatabaseFilePath);
        this.staffDatabaseFilePath = personDatabaseFilePath;
    }

    public Path getApplicantDatabaseFilePath() {
        return applicantDatabaseFilePath;
    }

    public void setApplicantDatabaseFilePath(Path applicantDatabaseFilePath) {
        requireNonNull(applicantDatabaseFilePath);
        this.applicantDatabaseFilePath = applicantDatabaseFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && personDatabaseFilePath.equals(o.personDatabaseFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, personDatabaseFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + personDatabaseFilePath);
        return sb.toString();
    }
}

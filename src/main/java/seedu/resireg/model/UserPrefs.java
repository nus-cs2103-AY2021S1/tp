package seedu.resireg.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.resireg.commons.core.GuiSettings;
import seedu.resireg.model.alias.CommandWordAlias;
import seedu.resireg.model.alias.exceptions.DuplicateCommandWordAliasException;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private List<CommandWordAlias> commandWordAliases = new ArrayList<>();
    private Path resiRegFilePath = Paths.get("data" , "resireg.json");

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
        setCommandAliases(newUserPrefs.getCommandWordAliases());
        setResiRegFilePath(newUserPrefs.getResiRegFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public List<CommandWordAlias> getCommandWordAliases() {
        return commandWordAliases;
    }

    /**
     * Resets the existing commandAliases of this {@code UserPrefs} with new {@code commandAliases}.
     */
    public void setCommandAliases(List<CommandWordAlias> commandWordAliases) {
        requireNonNull(commandWordAliases);
        if (!aliasesAreUnique(commandWordAliases)) {
            throw new DuplicateCommandWordAliasException();
        }
        this.commandWordAliases = new ArrayList<>(commandWordAliases);
    }

    /**
     * Checks whether the existing commandAliases of this {@code UserPrefs} contains the target object.
     */
    public boolean hasAlias(CommandWordAlias target) {
        requireNonNull(target);
        return commandWordAliases.stream().anyMatch(commandWordAlias ->
            commandWordAlias.getAlias().equals(target.getAlias()));
    }

    public void deleteAlias(CommandWordAlias target) {
        commandWordAliases.remove(target);
    }

    /**
     * Adds a new command word alias to the  existing commandAliases list of this {@code UserPrefs}
     */
    public void addAlias(CommandWordAlias source) {
        requireNonNull(source);
        commandWordAliases.add(source);
    }


    public Path getResiRegFilePath() {
        return resiRegFilePath;
    }

    public void setResiRegFilePath(Path resiRegFilePath) {
        requireNonNull(resiRegFilePath);
        this.resiRegFilePath = resiRegFilePath;
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
                && commandWordAliases.equals(o.commandWordAliases)
                && resiRegFilePath.equals(o.resiRegFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, commandWordAliases, resiRegFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("Command Aliases : " + commandWordAliases);
        sb.append("\nLocal data file location : " + resiRegFilePath);
        return sb.toString();
    }

    public String getCommandWordAliasesAsString() {
        String res = "";
        for (CommandWordAlias commandWordAlias : commandWordAliases) {
            res = res + commandWordAlias.toString() + "\n";
        }
        return res;
    }

    /**
     * Returns true if {@code commandAliases} contains only unique command word aliases.
     */
    private boolean aliasesAreUnique(List<CommandWordAlias> commandWordAliases) {
        for (int i = 0; i < commandWordAliases.size() - 1; i++) {
            for (int j = i + 1; j < commandWordAliases.size(); j++) {
                if (commandWordAliases.get(i).equals(commandWordAliases.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

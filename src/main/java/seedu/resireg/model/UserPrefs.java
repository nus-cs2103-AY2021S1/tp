package seedu.resireg.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import seedu.resireg.commons.core.GuiSettings;
import seedu.resireg.model.alias.CommandWordAlias;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private List<CommandWordAlias> commandAliases = new ArrayList<>();
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");

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
        setCommandAliases(newUserPrefs.getCommandAliases());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public List<CommandWordAlias> getCommandAliases() {
        return commandAliases;
    }

    public Map<String, String> getAliasWordMap() {
        final Map<String, String> map = new HashMap<>();
        for(CommandWordAlias commandWordAlias : getCommandAliases()) {
            final String commandWord = commandWordAlias.getCommandWord().toString();
            final String alias = commandWordAlias.getAlias().toString();
            map.put(commandWord, alias);
        }
        return map;
    }

    public void setCommandAliases(List<CommandWordAlias> commandAliases) {
        requireNonNull(commandAliases);
        this.commandAliases = commandAliases;
    }

    public boolean hasAlias(CommandWordAlias target) {
        return commandAliases.contains(target);
    }

    public void deleteAlias(CommandWordAlias target) {
        commandAliases.remove(target);
    }

    public void addAlias(CommandWordAlias source) {
        requireNonNull(source);
        commandAliases.add(source);
    }


    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
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
                && commandAliases.equals(o.commandAliases)
                && addressBookFilePath.equals(o.addressBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, commandAliases, addressBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("Command Aliases : " + commandAliases);
        sb.append("\nLocal data file location : " + addressBookFilePath);
        return sb.toString();
    }

}
